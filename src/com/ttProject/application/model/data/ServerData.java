package com.ttProject.application.model.data;

/**
 * サーバーのデータを管理するデータクラス
 * @author taktod
 */
public class ServerData {
	/** サーバーの状態 true:起動中 false:停止中 */
	private boolean state;
	public ServerData() {
		state = false;
	}
	/**
	 * 起動中に変更
	 */
	public void setRunning() {
		state = true;
	}
	/**
	 * 停止中に変更
	 */
	public void setStop() {
		state = false;
	}
	/**
	 * 状態を確認する。
	 * @return true:起動中 false:停止中
	 */
	public boolean isRunning() {
		return state;
	}
}
