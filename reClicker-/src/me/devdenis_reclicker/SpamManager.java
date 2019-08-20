package me.devdenis_reclicker;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

public class SpamManager {

	private boolean isSpamming = false;
	private Thread spammingThread;
	private Robot robot;
	
	
	public void startSpam(String content, int speed) {
		isSpamming = true;
		spammingThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					robot = new Robot();
				} catch (Exception e) {
					ReClicker.getInstance().log(Level.SEVERE, "Roboto dontos wantos workos", e);
				}
				while(true) {
					if(isSpamming) {
						spam(content, speed);
					}
				}
			}
		});
		spammingThread.start();
	}
	
	public void stopSpam() {
		isSpamming = false;
	}
	
	public void spam(String content, int speed) {
		for (int i = 0; i < content.toCharArray().length; i++) {
			try {
				robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(content.toCharArray()[i]));
				robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(content.toCharArray()[i]));
				if(i == content.toCharArray().length - 1) {
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
				}
			} catch (Exception e) {
				ReClicker.getInstance().log(Level.SEVERE, "Spamming failed... Something is wrong with your text.", e);
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				ReClicker.getInstance().log(Level.WARNING, "Spam content creation sleep exception", e);
			}
		}
		
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			ReClicker.getInstance().log(Level.WARNING, "Spam sleep exception", e);
		}
	}
	
}
