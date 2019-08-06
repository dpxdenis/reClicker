package me.devdenis_reclicker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import me.devdenis_reclicker.logger.LogTypes;
import me.devdenis_reclicker.logger.Logger;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ReClicker {
	
	public static ReClicker instance = new ReClicker();
	
	private JFrame frmReclicker;
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
	private String version = "v0.0.2-earlyalpha";
	private static Logger logger = new Logger();


	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					instance.frmReclicker.setVisible(true);
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
		try {
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
			aCPanel.add(cBac);
			
			chckbxAc.setBounds(10, 50, 97, 23);
			aCPanel.add(chckbxAc);
			
			btnAcStart.setBounds(10, 76, 89, 23);
			aCPanel.add(btnAcStart);
			
			btnAcStop.setBounds(103, 76, 89, 23);
			aCPanel.add(btnAcStop);
			
			JPanel aSPanel = new JPanel();
			aSPanel.setBorder(new TitledBorder(null, "AutoSpammer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			aSPanel.setBounds(10, 132, 253, 110);
			frmReclicker.getContentPane().add(aSPanel);
			aSPanel.setLayout(null);
			
			textFieldAs = new JTextField();
			textFieldAs.setText("Your Text goes here!");
			textFieldAs.setToolTipText("Your Text goes here!");
			textFieldAs.setBounds(48, 20, 195, 20);
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
			aSPanel.add(cbAs);
			
			btnAsStart.setBounds(10, 76, 89, 23);
			aSPanel.add(btnAsStart);
			
			btnAsStop.setBounds(103, 76, 89, 23);
			aSPanel.add(btnAsStop);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(273, 11, 226, 239);
			frmReclicker.getContentPane().add(panel_2);
			panel_2.setLayout(null);
			
			textAreaLog.setBounds(0, 0, 226, 239);
			panel_2.add(textAreaLog);
			
			/*
			 * reClicker Area
			 */
			frmReclicker.setTitle("reClicker | " + version);
			
		} catch (Exception ev) {
			frmReclicker.setTitle("reClicker | Error while start!");
			String s = "";
			for (int i = 0; i <= 1; i++) {
				s += ev.getStackTrace()[i] + "\n";
			}
			ev.printStackTrace();
			JOptionPane.showMessageDialog(frmReclicker, "Error while starting reClicker " + version + "! \n Please report this error at Github! \nError: " + s);
		}
		
	}
	
	private void init() {
		logger.log(LogTypes.INFO, "reClicker " + version + " started!");
		logger.log(LogTypes.SYS, "Get ready to spam!");
	}
	
	public static ReClicker getInstance() {
		return instance;
	}

	public JTextArea getTextAreaLog() {
		return textAreaLog;
	}


	public Logger getLogger() {
		return logger;
	}
	
	
	
	
}
