package com.ttProject.application.model;

import com.ttProject.application.model.data.ConfigData;

/**
 * 保存しているコンフィグデータにアクセスするためのモデル
 * @author taktod
 */
public class ConfigModel {
	private ConfigData configData;
	public ConfigModel() {
		configData = new ConfigData();
	}
	public void initialize() {
		configData.loadConfig();
	}
	public void save() {
		configData.saveConfig();
	}
	public String getRed5Path() {
		return configData.getData("red5Path");
	}
	public void setRed5Path(String path) {
		configData.setData("red5Path", path);
	}
}
