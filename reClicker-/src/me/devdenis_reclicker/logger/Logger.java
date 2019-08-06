package me.devdenis_reclicker.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

import me.devdenis_reclicker.ReClicker;

public class Logger {
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	JTextArea logArea = ReClicker.getInstance().getTextAreaLog();
	
	
	public void log(LogTypes type, String msg) {
		logArea.append("[" + type + "]" + " " + msg + "\n");
		System.out.println(date.format(new Date()) + " [" + type + "]" + " " + msg);
	}

}
