package me.devdenis_reclicker;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;

public class ClickerManager {
	
	private boolean isClicking = false;
	private Thread clickingThread;
	private Robot robot;
	
	
	public void startClick(boolean rightClick, int speed) {
		isClicking = true;
		clickingThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					robot = new Robot();
				} catch (Exception e) {
					ReClicker.getInstance().log(Level.SEVERE, "Roboto dontos wantos workos", e);
				}
				while(true) {
					if(isClicking) {
						click(rightClick,speed);
					}
				}
			}
		});
		clickingThread.start();
	}
	
	public void stopClick() {
		isClicking = false;
	}
	
	public void click(boolean rightClick, int speed) {
		if(!rightClick) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} else {
			robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
		}
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			ReClicker.getInstance().log(Level.WARNING, "Click sleep exception", e);
		}
	}
}
