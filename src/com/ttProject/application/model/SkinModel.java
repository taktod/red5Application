package com.ttProject.application.model;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.ttProject.application.model.data.SkinData;
import com.ttProject.application.view.GuiEntryFrame;

/**
 * スキンを操作するモデル
 */
public class SkinModel {
	/**
	 * 現在のスキン状態をコンボボックスにセットして、初期データを現在のスキンデータにあわせておく。
	 * @param comboBox
	 */
	public void setSkinListOnComboBox(JComboBox comboBox, GuiEntryFrame parent) {
		for(LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()) {
			comboBox.addItem(lafInfo.getName());
		}
		SkinData skinData = new SkinData();
		skinData.loadSkin();
		if(skinData.getSkin() == null) {
			comboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
		}
		else {
			comboBox.setSelectedItem(skinData.getSkin());
		}
	}
	public void setSkin(String name, GuiEntryFrame parent) {
		SkinData skinData = new SkinData();
		skinData.setSkin(name);
		skinData.saveSkin();

		for(LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()) {
			if(name.equals(lafInfo.getName())) {
				try {
					UIManager.setLookAndFeel(lafInfo.getClassName());
					SwingUtilities.updateComponentTreeUI(parent);
				}
				catch (Exception e) {
					System.out.println("Failed to set skin...");
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
