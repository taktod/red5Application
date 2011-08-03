package com.ttProject.application.model;

import java.security.Permission;

/**
 * セキュリティーマネージャーを自作することでSystem.exitを実行したときにプログラムを終了しないようにしておく。
 */
public class SecurityManagerModel {
	private static SecurityManager originalManager;
	public static void unableSystemExit() {
		originalManager = System.getSecurityManager();
		System.setSecurityManager(new SecurityManager(){
			@Override
			public void checkExit(int paramInt) {
				Exception e = new Exception();
				e.printStackTrace();
				throw new SecurityException();
			}
			@Override
			public void checkPermission(Permission paramPermission) {
			}
			@Override
			public void checkPermission(Permission paramPermission,
					Object paramObject) {
			}
		}); // */
	}
	public static void enableSystemExit() {
		System.setSecurityManager(originalManager);
	}
}
