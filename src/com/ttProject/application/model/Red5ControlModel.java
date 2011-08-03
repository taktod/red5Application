package com.ttProject.application.model;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;

/**
 * Red5のプログラムをコントロールするコントローラー
 * @author taktod
 */
public class Red5ControlModel {
	public Red5ControlModel() {
		
	}
	// とりあえずred5の起動時の基底階層Beansを取得しておきたい。
	public void getRed5BaseContext() {
		try {
			MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
			IContextLoader proxy;
			ObjectName oName = new ObjectName("org.red5.server:type=ContextLoader");
			proxy = JMX.newMBeanProxy(mbeanServer, oName, IContextLoader.class);
			//System.out.println(proxy.getParentContext().getClass().getClassLoader().loadClass("org.apache.naming.java.javaURLContextFactory"));
//			Object MainContext = proxy.getParentContext().getClass().getClassLoader().loadClass("");
			ClassLoader loader = proxy.getParentContext().getClass().getClassLoader();
			Class<?> loaderBaseClass = loader.loadClass("org.red5.server.LoaderBase");
			Method getApplicationContextMethod = loaderBaseClass.getMethod("getApplicationContext");
			Object baseContext = getApplicationContextMethod.invoke(null);
			Class<?> applicationContextClass = loader.loadClass("org.springframework.context.ApplicationContext");
			Method getBean = applicationContextClass.getMethod("getBean", String.class);
			Object obj = getBean.invoke(baseContext, "red5.common");
			System.out.println(obj);
			Object jmxAgent = getBean.invoke(obj, "jmxAgent");
			System.out.println(jmxAgent);
			Field cs = jmxAgent.getClass().getDeclaredField("cs");
			System.out.println(cs);
			boolean access = cs.isAccessible();
			cs.setAccessible(true);
			JMXConnectorServer connectorServer = (JMXConnectorServer)cs.get(null);
			System.out.println(connectorServer);
			cs.setAccessible(access);
			connectorServer.removeNotificationListener((NotificationListener) jmxAgent);
			connectorServer.stop();
//			Method shutdown = jmxAgent.getClass().getMethod("shutdown");
//			shutdown.invoke(null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private interface IContextLoader {
		public Object getParentContext();
	}
}
