package com.ttProject.application.view;

import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.SoftBevelBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.ttProject.application.controller.MainController;
import com.ttProject.application.model.SecurityManagerModel;
import com.ttProject.application.view.component.JSystemOutTextArea;

import javax.swing.JComboBox;

public class GuiEntryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private GuiEntryFrame self;
	private JPanel jContentPane = null;
	private JTabbedPane TabbedPane = null;
	private JPanel SettingPanel = null;
	private JPanel StatusPanel = null;
	private JPanel LogPanel = null;
	private JPanel LogBodyPanel = null;
	private JPanel LogTitlePanel = null;
	private JLabel LogTitle = null;
	private JScrollPane LogScrollPane = null;
	private JTextArea LogArea = null;
	private JPanel SettingBodyPanel = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField Red5DirectoryField = null;
	private JPanel LeftSpan1 = null;
	private JPanel RightSpan1 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JPanel StatusBodyPanel = null;
	private JPanel LeftSpan2 = null;
	private JPanel RightSpan2 = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField Red5Field = null;
	private JButton jButton2 = null;
	private JLabel jLabel4 = null;
	private JTextField Red5StateField = null;
	private JButton startButton = null;
	private JButton jButton4 = null;
	private JLabel SkinSettingLabel = null;
	private JComboBox SkinComboBox = null;
	private JButton testButton = null;
	private JButton SaveButton = null;
	/**
	 * This method initializes TabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (TabbedPane == null) {
			TabbedPane = new JTabbedPane();
			TabbedPane.setTabPlacement(JTabbedPane.TOP);
			TabbedPane.addTab("状態", null, getStatusPanel(), null);
			TabbedPane.addTab("設定", null, getSettingPanel(), null);
			TabbedPane.addTab("ログ", null, getLogPanel(), null);
		}
		return TabbedPane;
	}

	/**
	 * This method initializes SettingPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSettingPanel() {
		if (SettingPanel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(1);
			SettingPanel = new JPanel();
			SettingPanel.setLayout(borderLayout);
			SettingPanel.setBackground(new Color(248, 248, 248));
			SettingPanel.setPreferredSize(new Dimension(0, 32));
			SettingPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
			SettingPanel.setName("");
			SettingPanel.add(getSettingBodyPanel(), BorderLayout.NORTH);
		}
		return SettingPanel;
	}

	/**
	 * This method initializes StatusPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStatusPanel() {
		if (StatusPanel == null) {
			StatusPanel = new JPanel();
			StatusPanel.setLayout(new BorderLayout());
			StatusPanel.setBackground(new Color(248, 248, 248));
			StatusPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
			StatusPanel.add(getStatusBodyPanel(), BorderLayout.NORTH);
		}
		return StatusPanel;
	}

	/**
	 * This method initializes LogPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLogPanel() {
		if (LogPanel == null) {
			LogPanel = new JPanel();
			LogPanel.setLayout(new BorderLayout());
			LogPanel.setBackground(new Color(248, 248, 248));
			LogPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
			LogPanel.add(getLogBodyPanel(), BorderLayout.CENTER);
		}
		return LogPanel;
	}

	/**
	 * This method initializes LogBodyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLogBodyPanel() {
		if (LogBodyPanel == null) {
			LogBodyPanel = new JPanel();
			LogBodyPanel.setLayout(new BorderLayout());
			LogBodyPanel.setPreferredSize(new Dimension(300, 300));
			LogBodyPanel.setBackground(new Color(248, 248, 248));
			LogBodyPanel.add(getLogTitlePanel(), BorderLayout.NORTH);
			LogBodyPanel.add(getLogScrollPane(), BorderLayout.CENTER);
		}
		return LogBodyPanel;
	}

	/**
	 * This method initializes LogTitlePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLogTitlePanel() {
		if (LogTitlePanel == null) {
			LogTitle = new JLabel();
			LogTitle.setText("動作ログ");
			LogTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			LogTitlePanel = new JPanel();
			LogTitlePanel.setLayout(new BorderLayout());
			LogTitlePanel.setPreferredSize(new Dimension(0, 24));
			LogTitlePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.lightGray), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
			LogTitlePanel.add(LogTitle, BorderLayout.CENTER);
		}
		return LogTitlePanel;
	}

	/**
	 * This method initializes LogScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getLogScrollPane() {
		if (LogScrollPane == null) {
			LogScrollPane = new JScrollPane();
			LogScrollPane.setViewportView(getLogArea());
		}
		return LogScrollPane;
	}

	/**
	 * This method initializes LogArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getLogArea() {
		if (LogArea == null) {
			LogArea = new JSystemOutTextArea();
			LogArea.setBackground(new Color(248, 248, 248));
			LogArea.setBounds(0, 0, 0, 0);
		}
		return LogArea;
	}

	/**
	 * This method initializes SettingBodyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSettingBodyPanel() {
		if (SettingBodyPanel == null) {
			GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
			gridBagConstraints110.gridx = 4;
			gridBagConstraints110.gridy = 5;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridx = 4;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 1;
			gridBagConstraints18.anchor = GridBagConstraints.EAST;
			gridBagConstraints18.gridy = 2;
			SkinSettingLabel = new JLabel();
			SkinSettingLabel.setText("スキン設定:");
			SkinSettingLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.gridwidth = 4;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridy = 4;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 5;
			gridBagConstraints5.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 6;
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 3;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.gridwidth = 3;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 3;
			jLabel1 = new JLabel();
			jLabel1.setText("Red5の位置:");
			jLabel1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridwidth = 7;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.ipady = 0;
			gridBagConstraints.gridy = 1;
			SettingBodyPanel = new JPanel();
			SettingBodyPanel.setLayout(new GridBagLayout());
			SettingBodyPanel.setBackground(new Color(248, 248, 248));
			SettingBodyPanel.setName("Red5Panel");
			SettingBodyPanel.add(getLeftSpan1(), gridBagConstraints3);
			SettingBodyPanel.add(getRightSpan1(), gridBagConstraints4);
			SettingBodyPanel.add(getJPanel(), gridBagConstraints);
			SettingBodyPanel.add(jLabel1, gridBagConstraints1);
			SettingBodyPanel.add(getRed5DirectoryField(), gridBagConstraints2);
			SettingBodyPanel.add(getJButton(), gridBagConstraints5);
			SettingBodyPanel.add(getJButton1(), gridBagConstraints6);
			SettingBodyPanel.add(SkinSettingLabel, gridBagConstraints18);
			SettingBodyPanel.add(getSkinComboBox(), gridBagConstraints21);
			SettingBodyPanel.add(getSaveButton(), gridBagConstraints110);
		}
		return SettingBodyPanel;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Red5");
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.setPreferredSize(new Dimension(100, 24));
			jPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.lightGray), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
			jPanel.add(jLabel, BorderLayout.CENTER);
		}
		return jPanel;
	}

	/**
	 * This method initializes Red5DirectoryField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getRed5DirectoryField() {
		if (Red5DirectoryField == null) {
			Red5DirectoryField = new JTextField();
			Red5DirectoryField.setEditable(false);
		}
		return Red5DirectoryField;
	}

	/**
	 * This method initializes LeftSpan1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftSpan1() {
		if (LeftSpan1 == null) {
			LeftSpan1 = new JPanel();
			LeftSpan1.setLayout(new GridBagLayout());
			LeftSpan1.setPreferredSize(new Dimension(10, 0));
		}
		return LeftSpan1;
	}

	/**
	 * This method initializes RightSpan1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightSpan1() {
		if (RightSpan1 == null) {
			RightSpan1 = new JPanel();
			RightSpan1.setLayout(new GridBagLayout());
			RightSpan1.setPreferredSize(new Dimension(10, 0));
		}
		return RightSpan1;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setPreferredSize(new Dimension(45, 29));
			jButton.setText("...");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					MainController mcontroller = new MainController(self);
					mcontroller.getRed5Directory();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("軽量版取得");
			jButton1.setEnabled(false);
		}
		return jButton1;
	}

	/**
	 * This method initializes StatusBodyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStatusBodyPanel() {
		if (StatusBodyPanel == null) {
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.gridx = 3;
			gridBagConstraints19.gridy = 6;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 2;
			gridBagConstraints17.gridwidth = 4;
			gridBagConstraints17.anchor = GridBagConstraints.EAST;
			gridBagConstraints17.gridy = 3;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = -1;
			gridBagConstraints16.gridy = -1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 2;
			gridBagConstraints15.gridwidth = 4;
			gridBagConstraints15.anchor = GridBagConstraints.EAST;
			gridBagConstraints15.gridy = 5;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints14.gridx = 3;
			gridBagConstraints14.gridy = 4;
			gridBagConstraints14.weightx = 1.0;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 1;
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridy = 4;
			jLabel4 = new JLabel();
			jLabel4.setText("起動状態:");
			jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel4.setHorizontalTextPosition(SwingConstants.RIGHT);
			jLabel4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridy = 2;
			gridBagConstraints12.gridwidth = 2;
			gridBagConstraints12.ipadx = 0;
			gridBagConstraints12.ipady = 0;
			gridBagConstraints12.gridx = 4;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.weightx = 1.0;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 2;
			jLabel3 = new JLabel();
			jLabel3.setText("Red5の位置:");
			jLabel3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridwidth = 7;
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.gridy = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 6;
			gridBagConstraints8.gridy = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 1;
			StatusBodyPanel = new JPanel();
			StatusBodyPanel.setLayout(new GridBagLayout());
			StatusBodyPanel.setBackground(new Color(248, 248, 248));
			StatusBodyPanel.add(getLeftSpan2(), gridBagConstraints7);
			StatusBodyPanel.add(getRightSpan2(), gridBagConstraints8);
			StatusBodyPanel.add(getJPanel1(), gridBagConstraints9);
			StatusBodyPanel.add(jLabel3, gridBagConstraints10);
			StatusBodyPanel.add(getRed5Field(), gridBagConstraints11);
			StatusBodyPanel.add(getJButton2(), gridBagConstraints12);
			StatusBodyPanel.add(jLabel4, gridBagConstraints13);
			StatusBodyPanel.add(getRed5StateField(), gridBagConstraints14);
			StatusBodyPanel.add(getStartButton(), gridBagConstraints15);
			StatusBodyPanel.add(getJButton4(), gridBagConstraints17);
			StatusBodyPanel.add(getTestButton(), gridBagConstraints19);
		}
		return StatusBodyPanel;
	}

	/**
	 * This method initializes LeftSpan2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftSpan2() {
		if (LeftSpan2 == null) {
			LeftSpan2 = new JPanel();
			LeftSpan2.setLayout(new GridBagLayout());
			LeftSpan2.setPreferredSize(new Dimension(10, 0));
		}
		return LeftSpan2;
	}

	/**
	 * This method initializes RightSpan2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightSpan2() {
		if (RightSpan2 == null) {
			RightSpan2 = new JPanel();
			RightSpan2.setLayout(new GridBagLayout());
			RightSpan2.setPreferredSize(new Dimension(10, 0));
		}
		return RightSpan2;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("サーバー状態");
			jLabel2.setHorizontalAlignment(SwingConstants.LEADING);
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BorderLayout());
			jPanel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.lightGray), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
			jPanel1.setPreferredSize(new Dimension(90, 24));
			jPanel1.add(jLabel2, BorderLayout.CENTER);
		}
		return jPanel1;
	}

	/**
	 * This method initializes Red5Field	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getRed5Field() {
		if (Red5Field == null) {
			Red5Field = new JTextField();
			Red5Field.setEditable(false);
		}
		return Red5Field;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("...");
			jButton2.setPreferredSize(new Dimension(45, 29));
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					MainController mcontroller = new MainController(self);
					mcontroller.getRed5Directory();
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes Red5StateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getRed5StateField() {
		if (Red5StateField == null) {
			Red5StateField = new JTextField();
			Red5StateField.setEditable(false);
			Red5StateField.setText("停止中");
		}
		return Red5StateField;
	}

	/**
	 * This method initializes startButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getStartButton() {
		if (startButton == null) {
			startButton = new JButton();
			startButton.setText("起動する");
			startButton.setSelected(false);
			startButton.setHorizontalTextPosition(SwingConstants.CENTER);
			startButton.setHorizontalAlignment(SwingConstants.CENTER);
			startButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					MainController mcontroller = new MainController(self);
					mcontroller.clickStartButton();
				}
			});
		}
		return startButton;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("軽量版取得");
			jButton4.setEnabled(false);
		}
		return jButton4;
	}

	/**
	 * This method initializes SkinComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getSkinComboBox() {
		if (SkinComboBox == null) {
			SkinComboBox = new JComboBox();
			SkinComboBox.setSelectedItem(UIManager.getLookAndFeel().getName());
			MainController mcontroller = new MainController(self);
			mcontroller.setupSkinComboBox(SkinComboBox);
			SkinComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					MainController mcontroller = new MainController(self);
					mcontroller.setSkin(SkinComboBox.getSelectedItem().toString());
				}
			});
		}
		return SkinComboBox;
	}

	/**
	 * This method initializes testButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTestButton() {
		if (testButton == null) {
			testButton = new JButton();
			testButton.setText("テストGo");
			testButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					MainController mcontroller = new MainController(self);
					mcontroller.clickTest();
				}
			});
		}
		return testButton;
	}

	/**
	 * This method initializes SaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (SaveButton == null) {
			SaveButton = new JButton();
			SaveButton.setText("保存する");
			SaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					MainController mcontroller = new MainController(self);
					mcontroller.saveConfig();
				}
			});
		}
		return SaveButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GuiEntryFrame thisClass = new GuiEntryFrame();
//				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public GuiEntryFrame() {
		super();
		self = this;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		MainController mcontroller = new MainController(self);
		mcontroller.initialize();
		SecurityManagerModel.unableSystemExit();
		this.setSize(600, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("RtmpApplication");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				SecurityManagerModel.enableSystemExit();
				System.out.println("windowClosing()");
				System.exit(0);
			}
		});
		mcontroller.start();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
			jContentPane.setBackground(Color.white);
			jContentPane.add(getTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

}
