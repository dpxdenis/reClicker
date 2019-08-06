package me.devdenis_reclicker.logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import me.devdenis_reclicker.ReClicker;

public class ReLogger {
	
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public ReLogger() {
		LogManager.getLogManager().reset();
		logger.setLevel(Level.ALL);
		
		ConsoleHandler cH = new ConsoleHandler();
		cH.setLevel(Level.ALL);
		cH.setFormatter(new Formatter() {

			@Override
			public String format(LogRecord rec) {
				return ReClicker.getInstance().getDate().format(new Date()) + " ["+ rec.getSourceClassName() + "][" + rec.getSourceMethodName() +"] [" + rec.getLevel() + "] " + rec.getMessage() + "\n";
			}
			
		});
		logger.addHandler(cH);
		
		try {	
			FileHandler fH = new FileHandler(System.getProperty("user.home") + File.separator + "reClicker" + File.separator + "logs" + File.separator +
		ReClicker.getInstance().getDate().format(new Date()) + " " + ReClicker.getInstance().getVersion() + ".log");
			fH.setFormatter(new Formatter() {

				@Override
				public String format(LogRecord rec) {
					return ReClicker.getInstance().getDate().format(new Date()) + " ["+ rec.getSourceClassName() + "][" + rec.getSourceMethodName() +"] [" + rec.getLevel() + "] " + rec.getMessage() + "\n";
				}
				
			});
			fH.setLevel(Level.ALL);
			logger.addHandler(fH);
			
		} catch (IOException | SecurityException e) {
			logger.log(Level.WARNING, "Cant create Logging File!", e);
		}
	}

	public Logger getLogger() {
		return logger;
	}
	
	

}
