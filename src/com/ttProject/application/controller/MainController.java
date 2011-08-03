package com.ttProject.application.controller;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ttProject.application.model.BootstrapModel;
import com.ttProject.application.model.ConfigModel;
import com.ttProject.application.model.ServerModel;
import com.ttProject.application.model.SkinModel;
import com.ttProject.application.model.data.ServerData;
import com.ttProject.application.view.GuiEntryFrame;

/*
 * あとすることは
 * ・スキンデータを保存することができるようにする。(完了)
 * ・設定データを保存できるようにする。(完了)
 * ・Shutdownができるようにしておく。(完了:ただしすべてのデータが解放されないので修正が必要。)

 * ・簡易版のRed5を準備して簡単ダウンロードできるようにする。
 * ・サーバーの処理イベントはほぼすべてMainControllerにもってくる。(設定データの書き換え等すらも。)
 * ・設定を後づけで変更できるようにしておく。(ポート変更のテスト完了)
 * ・いろいろな情報のプロファイリングができるようにしておく。
 * ・Applet化
 * ・UDPHoleパンチングで通信ができるようにしておく。
 * ・CUI化
 * ・読み込みの実行前にrmiの接続を先に生成してやって
 */
/**
 * Red5のサーバーをコントロールするクラス
 * @author taktod
 */
public class MainController {
	private GuiEntryFrame parent = null;
	public MainController(GuiEntryFrame parent) {
		this.parent = parent;
	}
	public void initialize() {
		ConfigModel configModel = new ConfigModel();
		configModel.initialize();
	}
	/**
	 * 初期化時にスキン選択ComboBoxをつくるところ
	 * @param comboBox
	 */
	public void setupSkinComboBox(JComboBox comboBox) {
		SkinModel skinModel = new SkinModel();
		skinModel.setSkinListOnComboBox(comboBox, parent);
	}
	/**
	 * 初期化完了時
	 */
	public void start() {
		SkinModel skinModel = new SkinModel();
		skinModel.setSkin(parent.getSkinComboBox().getSelectedItem().toString(), parent);
		ConfigModel configModel = new ConfigModel();
		parent.getRed5DirectoryField().setText(configModel.getRed5Path());
		parent.getRed5Field().setText((configModel.getRed5Path() == null ? "" : configModel.getRed5Path() + "/red5.jar"));
	}
	/**
	 * Red5のディレクトリを選択する。
	 */
	public void getRed5Directory() {
		JFileChooser dir_dlg = new JFileChooser();
		dir_dlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dir_dlg.setCurrentDirectory(new File(parent.getRed5DirectoryField().getText()));
		int res = dir_dlg.showOpenDialog(parent);
		if(res == JFileChooser.APPROVE_OPTION) {
			File f = dir_dlg.getSelectedFile();
			File f2 = new File(f.getPath() + "/red5.jar");
			if(f2.exists()) {
				// 存在する場合
				parent.getRed5Field().setText(f2.getPath());
			}
			else {
				// 存在しない場合
				// とりあえず警告しておく。
				JOptionPane.showMessageDialog(parent, "Red5起動用のプログラムが見つかりませんでした。。", "解析エラー", JOptionPane.OK_OPTION);
				parent.getRed5Field().setText("現在のディレクトリにはred5.jarがありません。");
			}
			// 選択したディレクトリにred5.jarが存在するか確認しておく。
			parent.getRed5DirectoryField().setText(f.getPath());
		}
	}
	/**
	 * skinのコンボボックス選択時
	 * @param name 選択名
	 */
	public void setSkin(String name) {
		SkinModel skinModel = new SkinModel();
		skinModel.setSkin(name, parent);
	}
	public void clickStartButton() {
		System.out.println("startボタンが押されました。");
		ServerModel serverModel = new ServerModel(parent);
		ServerData serverData = serverModel.getServerData();
		if(serverData.isRunning()) {
			// 停止
			serverModel.stopServer();
		}
		else {
			// 起動
			serverModel.startServer();
		}
	}
	public void saveConfig() {
		ConfigModel configModel = new ConfigModel();
		configModel.setRed5Path(parent.getRed5DirectoryField().getText());
		configModel.save();
	}
	public void clickTest() {
		System.out.println("clickTestがクリックされたよ。");
		ServerModel serverModel = new ServerModel(parent);
		if(serverModel.getServerData().isRunning()) {
			ClassLoader loader = BootstrapModel.getClassLoader();
			try {
				System.out.println(loader);
				System.out.println(Thread.currentThread().getContextClassLoader());
				Class<?> loaderBase_class = Class.forName("org.red5.server.LoaderBase", false, loader);
				Method getApplicationContext_method = loaderBase_class.getMethod("getApplicationContext");
				Object appContext = getApplicationContext_method.invoke(null);
				System.out.println(appContext);
				Class<?> beanFactory_interface = appContext.getClass();
				Method getBean_method = beanFactory_interface.getMethod("getBean", String.class);
				Object red5Core = getBean_method.invoke(appContext, "red5.core");
				System.out.println(red5Core);
				Object rtmpTransport = getBean_method.invoke(red5Core, "rtmpTransport");
				System.out.println(rtmpTransport);
				
				Class<?> rtmpMinaTransport_class = rtmpTransport.getClass();
				Field acceptor_field = rtmpMinaTransport_class.getDeclaredField("acceptor");
				boolean access = acceptor_field.isAccessible();
				acceptor_field.setAccessible(true);
				Object acceptor = acceptor_field.get(rtmpTransport);
				acceptor_field.setAccessible(access);
				Method bind_method = acceptor.getClass().getMethod("bind", SocketAddress.class);
				bind_method.invoke(acceptor, new InetSocketAddress(11935));
				Method unbind_method = acceptor.getClass().getMethod("unbind", SocketAddress.class);
				unbind_method.invoke(acceptor, new InetSocketAddress(1935));
				
/*				Method start_method = rtmpMinaTransport_class.getMethod("start");
				Method stop_method = rtmpMinaTransport_class.getMethod("stop");
				System.out.println("stop rtmpTransport");
				stop_method.invoke(rtmpTransport);
				System.out.println("clear address map");
				Field addresses_field = rtmpMinaTransport_class.getDeclaredField("addresses");
				boolean access = addresses_field.isAccessible();
				addresses_field.setAccessible(true);
				((Set<?>)addresses_field.get(rtmpTransport)).clear();
				addresses_field.setAccessible(access);
				System.out.println("add another address");
				Method setConnector_method = rtmpMinaTransport_class.getMethod("setConnector", InetSocketAddress.class);
				setConnector_method.invoke(rtmpTransport, new InetSocketAddress(11935));
				start_method.invoke(rtmpTransport);// */
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 軽量版のRed5をダウンロードする。
}
