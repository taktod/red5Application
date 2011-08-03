package com.ttProject.application.model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * スキンデータを保存しておく。
 * @author taktod
 */
public class SkinData {
	private static String filePath = System.getProperty("user.home") + "/.java-skin.properties";
	private static Properties skinProp = new Properties();
	public void loadSkin() {
		try {
			skinProp.load(new FileInputStream(filePath));
		}
		catch (FileNotFoundException e) {
			;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveSkin() {
		try {
			skinProp.store(new FileOutputStream(filePath), "swing look and feel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getSkin() {
		return skinProp.getProperty("skin");
	}
	public void setSkin(String skin) { 
		skinProp.setProperty("skin", skin);
	}
}
