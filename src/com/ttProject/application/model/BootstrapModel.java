package com.ttProject.application.model;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Red5のブートストラップエミュレーター
 * @author taktod
 */
public class BootstrapModel {
	private static ClassLoader loader = null;
	public static ClassLoader getClassLoader() {
		return loader;
	}
	public static void shutdown() {
		if(loader == null) {
			return;
		}
		// shutdownやってみる。
		try {
			String policyFile = System.getProperty("java.security.policy");
			if(policyFile == null) {
				System.setProperty("java.security.debug", "failure");
				System.setProperty("java.security.policy", "conf/red5.policy");
			}
			MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
			
			ShutdownMXBean proxy = null;
			ObjectName tomcatObjectName = new ObjectName("org.red5.server:type=TomcatLoader");
			ObjectName jettyObjectName = new ObjectName("org.red5.server:type=JettyLoader");
			ObjectName contextLoaderObjectName = new ObjectName("org.red5.server:type=ContextLoader");
			if(mbeanServer.isRegistered(jettyObjectName)) {
				proxy = JMX.newMXBeanProxy(mbeanServer, jettyObjectName, ShutdownMXBean.class);
			}
			else if(mbeanServer.isRegistered(tomcatObjectName)) {
				proxy = JMX.newMXBeanProxy(mbeanServer, tomcatObjectName, ShutdownMXBean.class);
			}
			else if(mbeanServer.isRegistered(contextLoaderObjectName)) {
				proxy = JMX.newMXBeanProxy(mbeanServer, contextLoaderObjectName, ShutdownMXBean.class);
			}
			if(proxy != null) {
				proxy.shutdown();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class<?> bridgeHandler = loader.loadClass("org.slf4j.bridge.SLF4JBridgeHandler");
			Method uninstall = bridgeHandler.getMethod("uninstall");
			uninstall.invoke(null);
			loader = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Shutdown complete...");
	}
	/**
	 * Bootstrap動作
	 * <pre>オリジナルのbootstrapを使わないわけは、boot.jar側のデータがContextClassLoaderにはいらない状態で動作してしまうので、初期化に失敗するため。
	 * ロードしたクラスの取り扱いに少々変更をくわえてある。</pre>
	 * @param bootJar
	 */
	public static void bootstrap() {
		try {
			String root = getRed5Root();
			File bootJar = new File(root + "/boot.jar");
			getConfigurationRoot(root);
			
			System.setProperty("red5.deployment.type", "bootstrap");
			System.setProperty("sun.lang.ClassLoader.allowArraySyntax", "true");
			if (System.getProperty("logback.ContextSelector") == null) {
				//set to use our logger
				System.setProperty("logback.ContextSelector", "org.red5.logging.LoggingContextSelector");
			}
			String policyFile = System.getProperty("java.security.policy");
			if (policyFile == null) {
				System.setProperty("java.security.debug", "all");
				System.setProperty("java.security.policy", "conf/red5.policy");
			}
			//set the temp directory if we're vista or later
			String os = System.getProperty("os.name").toLowerCase();
			//String arch = System.getProperty("os.arch").toLowerCase();
			//System.out.printf("OS: %s Arch: %s\n", os, arch);
			if (os.contains("vista") || os.contains("windows 7")) {
				String dir = System.getProperty("user.home");
				//detect base drive (c:\ etc)
				if (dir.length() == 3) {
					//use default
					dir += "Users\\Default\\AppData\\Red5";
					//make sure the directory exists
					File f = new File(dir);
					if (!f.exists()) {
						f.mkdir();
					}
					f = null;
				} else {
					dir += "\\AppData\\localLow";
				}
				System.setProperty("java.io.tmpdir", dir);
				System.out.printf("Setting temp directory to %s\n", System.getProperty("java.io.tmpdir"));
			}
			//get current loader
			ClassLoader baseLoader = Thread.currentThread().getContextClassLoader();
			ClassLoader firstLoader = URLClassLoader.newInstance(new URL[]{bootJar.toURI().toURL()});
			Class<?> classLoaderBuilder = firstLoader.loadClass("org.red5.classloading.ClassLoaderBuilder");
			Method build = classLoaderBuilder.getMethod("build", new Class[]{File.class, int.class, ClassLoader.class});
			// build a ClassLoader(parent classLoaderをつけている:ここが変更点)
			loader = (ClassLoader)build.invoke(null, new Object[]{null, 2, firstLoader});
			//set new loader as the loader for this thread
			Thread.currentThread().setContextClassLoader(loader);
			// create a new instance of this class using new classloader
			Object boot = Class.forName("org.red5.server.Launcher", true, loader).newInstance();
			Method m1 = boot.getClass().getMethod("launch", (Class[]) null);
			m1.invoke(boot, (Object[]) null);
			//not that it matters, but set it back to the original loader
			Thread.currentThread().setContextClassLoader(baseLoader);
			// 完了
			System.out.println("Bootstrap complete");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to start red5!!");
		}
	}
	public static String getConfigurationRoot(String root) throws IOException {
		String conf;
		if(root == null) {
			throw new IOException("Failed to get config data.");
		}
		conf = root + "/conf";
		if(File.separatorChar != '/') {
			conf = conf.replaceAll("\\\\", "/");
		}
		System.setProperty("red5.config_root", conf);
		return conf;
	}
	/**
	 * Red5のルートディレクトリを取得している。
	 * @return
	 * @throws IOException
	 */
	public static String getRed5Root() throws IOException {
		// 基本的にApplicationに設定されているRed5のルートディレクトリを利用する。
		return System.getProperty("red5.root");
	}
	private interface ShutdownMXBean {
		public void shutdown();
	}
}
