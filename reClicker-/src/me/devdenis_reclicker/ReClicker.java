package me.devdenis_reclicker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import me.devdenis_reclicker.components.ReButton;
import me.devdenis_reclicker.components.ReTextField;
import me.devdenis_reclicker.logger.ReLogger;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class ReClicker {
	
	public static ReClicker instance = new ReClicker();
	
	public JFrame frmReclicker;
	private ReTextField textFieldAc;
	private ReTextField textFieldAs;
	private ReTextField textFieldAsMs;
	private JComboBox<String> cBac = new JComboBox<String>();
	private JComboBox<String> cbAs = new JComboBox<String>();
	private JCheckBox chckbxAc = new JCheckBox("Rightclick?");	
	private ReButton btnAcStart = new ReButton("Start [F6]");
	private ReButton btnAcStop = new ReButton("Stop [F7]");
	private ReButton btnAsStart = new ReButton("Start [F8]");
	private ReButton btnAsStop = new ReButton("Stop [F9]");
	private JTextArea textAreaLog = new JTextArea();
	private String version = "v0.6.1-beta";
	private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	private ReLogger loggerInstance;
	private ClickerManager clickerManager = new ClickerManager();
	private SpamManager spamManager = new SpamManager();


	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					instance.frmReclicker.setVisible(true);
					try {
						Path path = Paths.get(System.getProperty("user.home") + File.separator + "reClicker");
						if(!Files.exists(path)) {
						//Main Dir
						Files.createDirectories(path);
						//Log Dir
						Files.createDirectories(Paths.get(System.getProperty("user.home") + File.separator + "reClicker" + File.separator + "logs"));
						}
					} catch (IOException e) {
						String stackTrace = "";
						for (int i = 0; i <= 1; i++) {
							stackTrace += e.getStackTrace()[i] + "\n";
						}
						JOptionPane.showMessageDialog(instance.frmReclicker, "Cannot create reClicker directorys! \n" + "Please report this error on GitHub!\n" + "Error: " + stackTrace);
					}
					instance.loggerInstance = new ReLogger();
					instance.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReClicker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			frmReclicker = new JFrame();
			frmReclicker.setTitle("reClicker");
			frmReclicker.setBounds(100, 100, 525, 300);
			frmReclicker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmReclicker.getContentPane().setBackground(new Color(32,32,32));
			frmReclicker.getContentPane().setLayout(null);
			frmReclicker.setAlwaysOnTop(true);
			frmReclicker.setResizable(false);
			frmReclicker.setLocationRelativeTo(null);
			
			JPanel aCPanel = new JPanel();
			aCPanel.setBorder(new TitledBorder(new LineBorder(new Color(100,100,100)), "AutoClicker", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(150,150,150)));
			aCPanel.setBounds(10, 11, 253, 110);
			frmReclicker.getContentPane().add(aCPanel);
			aCPanel.setLayout(null);
			aCPanel.setBackground(new Color(32,32,32));
			
			textFieldAc = new ReTextField();
			textFieldAc.setToolTipText("Clickspeed in MS");
			textFieldAc.setBounds(10, 23, 48, 20);
			textFieldAc.setText("1");
			aCPanel.add(textFieldAc);
			textFieldAc.setColumns(10);
			
			JLabel lblOr = new JLabel("or");
			lblOr.setBounds(97, 26, 46, 14);
			lblOr.setForeground(new Color(255,255,255));
			aCPanel.add(lblOr);
			
			JLabel lblMs = new JLabel("ms");
			lblMs.setBounds(59, 26, 46, 14);
			lblMs.setForeground(new Color(255,255,255));
			aCPanel.add(lblMs);
			
			cBac.setModel(new DefaultComboBoxModel<String>(new String[] {"1 ms", "2 ms", "3 ms", "4 ms", "5 ms", "6 ms", "7 ms", "8 ms", "9 ms", "10 ms", "15 ms ", "20 ms", "30 ms", "40 ms", "50 ms", "60 ms", "70 ms", "80 ms", "90 ms", "100 ms", "110 ms", "120 ms", "130 ms", "140 ms", "150 ms", "160 ms", "170 ms", "180 ms", "190 ms", "200 ms", "300 ms", "400 ms", "500 ms", "600 ms", "700 ms", "800 ms", "900 ms", "1000 ms", "2000 ms", "3000 ms", "4000 ms", "5000 ms", "6000 ms", "7000 ms", "8000 ms", "9000 ms", "10000 ms"}));
			cBac.setBounds(132, 23, 60, 20);
			cBac.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					String s = e.getItem().toString().split(" ")[0];
					textFieldAc.setText(s);
				}
			});
			aCPanel.add(cBac);
			
			chckbxAc.setBounds(10, 50, 97, 23);
			chckbxAc.setBackground(new Color(32,32,32));
			chckbxAc.setForeground(new Color(200,200,200));
			chckbxAc.setFocusPainted(false);
			aCPanel.add(chckbxAc);
			
			btnAcStart.setBounds(10, 76, 89, 23);
			aCPanel.add(btnAcStart);
			
			btnAcStop.setBounds(103, 76, 89, 23);
			aCPanel.add(btnAcStop);
			btnAcStop.setEnabled(false);
			
			JPanel aSPanel = new JPanel();
			aSPanel.setBorder(new TitledBorder(new LineBorder(new Color(100,100,100)), "AutoSpammer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(150,150,150)));
			aSPanel.setBounds(10, 132, 253, 110);
			frmReclicker.getContentPane().add(aSPanel);
			aSPanel.setLayout(null);
			aSPanel.setBackground(new Color(32,32,32));
			
			textFieldAs = new ReTextField();
			textFieldAs.setText("Your Text goes here!");
			textFieldAs.setToolTipText("Your Text goes here!");
			textFieldAs.setBounds(48, 20, 195, 20);
			aSPanel.add(textFieldAs);
			textFieldAs.setColumns(10);
			
			JLabel lblText = new JLabel("Text:");
			lblText.setBounds(10, 23, 46, 14);
			lblText.setForeground(new Color(255,255,255));
			aSPanel.add(lblText);
			
			textFieldAsMs = new ReTextField();
			textFieldAsMs.setToolTipText("Clickspeed in MS");
			textFieldAsMs.setColumns(10);
			textFieldAsMs.setBounds(10, 51, 48, 20);
			textFieldAsMs.setText("1");
			aSPanel.add(textFieldAsMs);
			
			JLabel label = new JLabel("ms");
			label.setBounds(59, 54, 46, 14);
			label.setForeground(new Color(255,255,255));
			aSPanel.add(label);
			
			JLabel label_1 = new JLabel("or");
			label_1.setBounds(97, 54, 46, 14);
			label_1.setForeground(new Color(255,255,255));
			aSPanel.add(label_1);
			
			cbAs.setModel(new DefaultComboBoxModel<String>(new String[] {"1 ms", "2 ms", "3 ms", "4 ms", "5 ms", "6 ms", "7 ms", "8 ms", "9 ms", "10 ms", "15 ms ", "20 ms", "30 ms", "40 ms", "50 ms", "60 ms", "70 ms", "80 ms", "90 ms", "100 ms", "110 ms", "120 ms", "130 ms", "140 ms", "150 ms", "160 ms", "170 ms", "180 ms", "190 ms", "200 ms", "300 ms", "400 ms", "500 ms", "600 ms", "700 ms", "800 ms", "900 ms", "1000 ms", "2000 ms", "3000 ms", "4000 ms", "5000 ms", "6000 ms", "7000 ms", "8000 ms", "9000 ms", "10000 ms"}));
			cbAs.setBounds(132, 51, 60, 20);
			cbAs.setBackground(new Color(32,32,32));
			cbAs.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					String s = e.getItem().toString().split(" ")[0];
					textFieldAsMs.setText(s);
				}
			});
			aSPanel.add(cbAs);
			
			btnAsStart.setBounds(10, 76, 89, 23);
			aSPanel.add(btnAsStart);
			
			btnAsStop.setBounds(103, 76, 89, 23);
			aSPanel.add(btnAsStop);
			btnAsStop.setEnabled(false);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(273, 11, 226, 239);
			panel_2.setLayout(null);
			
			JScrollPane sPane = new JScrollPane(textAreaLog);
			panel_2.add(sPane);

			frmReclicker.getContentPane().add(panel_2);
			
			sPane.setBounds(0, 0, 226, 239);
			textAreaLog.setBounds(0, 0, 226, 239);
			textAreaLog.setEditable(false);
			textAreaLog.setBackground(new Color(32,32,32));
			textAreaLog.setForeground(new Color(255,255,255));
			DefaultCaret caret = (DefaultCaret)textAreaLog.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			
			/*
			 * reClicker Area
			 */
			frmReclicker.setTitle("reClicker | " + version);
	}
	
	private void init() {
		
			try {
				Path path = Paths.get(System.getProperty("user.home") + File.separator + "reClicker");
				if(!Files.exists(path)) {
				//Main Dir
				Files.createDirectories(path);
				//Log Dir
				Files.createDirectories(Paths.get(System.getProperty("user.home") + File.separator + "reClicker" + File.separator + "logs"));
				
				log(Level.FINE, "Created reClicker directorys!");
				} else {
					log(Level.INFO, "reClicker directory is already created!");
				}
			} catch (IOException e) {
				log(Level.SEVERE, "Cannot create reClicker directory!");
			}
		
		log(Level.INFO, "reClicker " + version + " started!");
		
		try {
			GlobalScreen.registerNativeHook();
			log(Level.FINE, "Registered Native Hook!");
			GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
			log(Level.FINE, "Global KeyListener started!");
		} catch (NativeHookException ev) {
			log(Level.SEVERE, "Cant register nativ hook. No spamming today.", ev);
		}
		log(Level.INFO, "Get ready to spam!");
		
		setButtonActions();
	}
	
	public void setButtonActions() {
		btnAcStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int speed;
				try {
					speed = Integer.parseInt(textFieldAc.getText());
					clickerManager.startClick(chckbxAc.isSelected(), speed);
					btnAcStart.setEnabled(false);
					btnAcStop.setEnabled(true);
					log(Level.INFO, "Starting clicking senpai! \n Rightclick: " + chckbxAc.isSelected() + " \n Speed: " + speed + "ms");
				} catch(Exception ev) {
					JOptionPane.showMessageDialog(frmReclicker, "Only numbers allowed as Clickspeed.");
					log(Level.WARNING, "In TextField for Ac are not only numbers.", ev);
				}
			}
		});
		
		btnAcStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clickerManager.stopClick();
				btnAcStart.setEnabled(true);
				btnAcStop.setEnabled(false);
				log(Level.INFO, "Stopped clicking master!");
			}
		});
		
		btnAsStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String content;
				int speed;
				try {
					content = textFieldAs.getText();
					speed = Integer.parseInt(textFieldAsMs.getText());
					spamManager.startSpam(content, speed);
					btnAsStart.setEnabled(false);
					btnAsStop.setEnabled(true);
					log(Level.INFO, "Starting spamming senpai! \n Content: " + content + " \n Speed: " + speed + "ms");
				} catch(Exception ev) {
					JOptionPane.showMessageDialog(frmReclicker, "Only numbers allowed as Spamspeed.");
					log(Level.WARNING, "In TextField for As are not only numbers.", ev);
				}
			}
		});
		
		btnAsStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spamManager.stopSpam();
				btnAsStart.setEnabled(true);
				btnAsStop.setEnabled(false);
				log(Level.INFO, "Stopped spamming master!");
			}
		});
	}
	
	public static ReClicker getInstance() {
		return instance;
	}
	

	public JTextArea getTextAreaLog() {
		return textAreaLog;
	}


	public void log(Level level, String msg) {
		loggerInstance.getLogger().log(level, msg);
		textAreaLog.append("[" + level + "] " + msg + "\n");
	}
	
	public void log(Level level, String msg, Exception e) {
		loggerInstance.getLogger().log(level, msg, e);
		loggerInstance.getLogger().log(level, "Error: " + e);
		textAreaLog.append("[" + level + "] " + msg + " !Exception! = " + e + "\n");
	}

	public SimpleDateFormat getDate() {
		return date;
	}

	public String getVersion() {
		return version;
	}

	public ClickerManager getClickerManager() {
		return clickerManager;
	}

	public SpamManager getSpamManager() {
		return spamManager;
	}

	public ReButton getBtnAcStart() {
		return btnAcStart;
	}

	public ReButton getBtnAcStop() {
		return btnAcStop;
	}

	public ReButton getBtnAsStart() {
		return btnAsStart;
	}

	public ReButton getBtnAsStop() {
		return btnAsStop;
	}
	
	
	
	
}
