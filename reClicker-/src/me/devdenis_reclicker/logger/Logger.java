package me.devdenis_reclicker.logger;

import me.devdenis_reclicker.ReClicker;

public class Logger {
	
	public void log(LogTypes type, String msg) {
		ReClicker.getInstance().getTextAreaLog().append("[" + type +"]" + " " + msg);
	}

}
