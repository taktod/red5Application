package com.ttProject.application.model.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * コンフィグデータ
 * @author taktod
 */
public class ConfigData {
	private static String filePath = System.getProperty("user.dir") + "/.rtmpApplication.properties";
	private static Properties configProp = new Properties();
	public void loadConfig() {
		try {
			configProp.load(new FileInputStream(filePath));
		}
		catch (FileNotFoundException e) {
			;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveConfig() {
		try {
			configProp.store(new FileOutputStream(filePath), "swing look and feel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getData(String key) {
		return (String)(configProp.get(key));
	}
	public void setData(String key, String data) {
		configProp.put(key, data);
	}
}
