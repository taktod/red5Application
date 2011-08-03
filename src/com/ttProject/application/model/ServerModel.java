package com.ttProject.application.model;

import javax.swing.JButton;

import com.ttProject.application.model.data.ServerData;
import com.ttProject.application.view.GuiEntryFrame;

/**
 * サーバーに対する操作を実行するモデル
 * @author taktod
 */
public class ServerModel {
	private static ServerData serverData = new ServerData();
	private GuiEntryFrame parent;
	public ServerModel(GuiEntryFrame parent) {
		this.parent = parent;
	}
	public ServerData getServerData() {
		return serverData;
	}
	/**
	 * サーバーを起動する。
	 */
	public void startServer() {
		// GUIの状態を変更する処理は別のところにもっていく。
		// スタートボタンを押したときの処理
		parent.getRed5StateField().setText("起動中...");
		JButton btn = parent.getStartButton();
		btn.setText("起動中");
		btn.setEnabled(false);
		// できれば別スレッドで動作させたい。
		serverData.setRunning();
		Thread red5 = new Thread(new Runnable() {
			@Override
			public void run() {
				// boot.jarを探して動作開始する。存在しない場合はエラー
				System.setProperty("red5.root", parent.getRed5DirectoryField().getText());
				BootstrapModel.bootstrap();
				JButton btn = parent.getStartButton();
				btn.setText("停止する");
				btn.setEnabled(true);
				parent.getRed5StateField().setText("動作中");
			}
		});
		red5.start();
	}
	/**
	 * サーバーを停止する。
	 */
	public void stopServer() {
		parent.getRed5StateField().setText("停止中...");
		JButton btn = parent.getStartButton();
		serverData.setStop();

		BootstrapModel.shutdown();
		btn.setText("起動する");
		parent.getRed5StateField().setText("停止中");
	}
}
