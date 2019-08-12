package me.devdenis_reclicker;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
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
import javax.swing.border.TitledBorder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import me.devdenis_reclicker.logger.ReLogger;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ReClicker {
	
	public static ReClicker instance = new ReClicker();
	
	public JFrame frmReclicker;
	private JTextField textFieldAc;
	private JTextField textFieldAs;
	private JTextField textFieldAsMs;
	private JComboBox<String> cBac = new JComboBox<String>();
	private JComboBox<String> cbAs = new JComboBox<String>();
	private JCheckBox chckbxAc = new JCheckBox("Rightclick?");	
	private JButton btnAcStart = new JButton("Start [F6]");
	private JButton btnAcStop = new JButton("Stop [F7]");
	private JButton btnAsStart = new JButton("Start [F8]");
	private JButton btnAsStop = new JButton("Stop [F9]");
	private JTextArea textAreaLog = new JTextArea();
	private String version = "v0.0.5-earlyalpha";
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
			frmReclicker.getContentPane().setLayout(null);
			
			
			JPanel aCPanel = new JPanel();
			aCPanel.setBorder(new TitledBorder(null, "AutoClicker", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			aCPanel.setBounds(10, 11, 253, 110);
			frmReclicker.getContentPane().add(aCPanel);
			aCPanel.setLayout(null);
			
			textFieldAc = new JTextField();
			textFieldAc.setToolTipText("Clickspeed in MS");
			textFieldAc.setBounds(10, 23, 48, 20);
			textFieldAc.setText("1");
			aCPanel.add(textFieldAc);
			textFieldAc.setColumns(10);
			
			JLabel lblOr = new JLabel("or");
			lblOr.setBounds(97, 26, 46, 14);
			aCPanel.add(lblOr);
			
			JLabel lblMs = new JLabel("ms");
			lblMs.setBounds(59, 26, 46, 14);
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
			aCPanel.add(chckbxAc);
			
			btnAcStart.setBounds(10, 76, 89, 23);
			aCPanel.add(btnAcStart);
			
			btnAcStop.setBounds(103, 76, 89, 23);
			aCPanel.add(btnAcStop);
			btnAcStop.setEnabled(false);
			
			JPanel aSPanel = new JPanel();
			aSPanel.setBorder(new TitledBorder(null, "AutoSpammer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			aSPanel.setBounds(10, 132, 253, 110);
			frmReclicker.getContentPane().add(aSPanel);
			aSPanel.setLayout(null);
			
			textFieldAs = new JTextField();
			textFieldAs.setText("Your Text goes here!");
			textFieldAs.setToolTipText("Your Text goes here!");
			textFieldAs.setBounds(48, 20, 195, 20);
			textFieldAs.setText("1");
			aSPanel.add(textFieldAs);
			textFieldAs.setColumns(10);
			
			JLabel lblText = new JLabel("Text:");
			lblText.setBounds(10, 23, 46, 14);
			aSPanel.add(lblText);
			
			textFieldAsMs = new JTextField();
			textFieldAsMs.setToolTipText("Clickspeed in MS");
			textFieldAsMs.setColumns(10);
			textFieldAsMs.setBounds(10, 51, 48, 20);
			aSPanel.add(textFieldAsMs);
			
			JLabel label = new JLabel("ms");
			label.setBounds(59, 54, 46, 14);
			aSPanel.add(label);
			
			JLabel label_1 = new JLabel("or");
			label_1.setBounds(97, 54, 46, 14);
			aSPanel.add(label_1);
			
			cbAs.setModel(new DefaultComboBoxModel<String>(new String[] {"1 ms", "2 ms", "3 ms", "4 ms", "5 ms", "6 ms", "7 ms", "8 ms", "9 ms", "10 ms", "15 ms ", "20 ms", "30 ms", "40 ms", "50 ms", "60 ms", "70 ms", "80 ms", "90 ms", "100 ms", "110 ms", "120 ms", "130 ms", "140 ms", "150 ms", "160 ms", "170 ms", "180 ms", "190 ms", "200 ms", "300 ms", "400 ms", "500 ms", "600 ms", "700 ms", "800 ms", "900 ms", "1000 ms", "2000 ms", "3000 ms", "4000 ms", "5000 ms", "6000 ms", "7000 ms", "8000 ms", "9000 ms", "10000 ms"}));
			cbAs.setBounds(132, 51, 60, 20);
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
				System.out.println("DEBUG SPAM START");
			}
		});
		
		btnAsStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DEBUG SPAM STOP");
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
		textAreaLog.append("[" + level + "] " + msg + " !Exception! = " + e);
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

	public JButton getBtnAcStart() {
		return btnAcStart;
	}

	public JButton getBtnAcStop() {
		return btnAcStop;
	}

	public JButton getBtnAsStart() {
		return btnAsStart;
	}

	public JButton getBtnAsStop() {
		return btnAsStop;
	}
	
	
	
	
}
