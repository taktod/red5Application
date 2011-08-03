package com.ttProject.application.model.jmx;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class JMXFactory {
	public static void testFunc() {
		System.out.println("testFunc is called!!!");
		try {
			MBeanServer mBeanServer;
			JMXConnectorServer connector;
			try {
				mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
			}
			catch(Exception e) {
				mBeanServer = ManagementFactory.getPlatformMBeanServer();
			}
			Registry r = null;
			try {
				r = LocateRegistry.getRegistry(9999);
				for(String regName : r.list()) {
					System.out.println(regName);
				}
			}
			catch (Exception e) {
				System.out.println("createAnotherRegistry");
				r = LocateRegistry.createRegistry(9999);
			}
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi://0.0.0.0/jndi/rmi://0.0.0.0:9999/red5");
			connector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
			System.out.println("start rmi");
			connector.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
