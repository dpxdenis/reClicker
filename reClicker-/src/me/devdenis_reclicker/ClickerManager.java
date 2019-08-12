package me.devdenis_reclicker;

public class ClickerManager {
	
	private boolean isClicking = false;
	private Thread clickingThread;
	
	
	
	public void startClick(boolean doubleClick, int speed) {
		isClicking = true;
		clickingThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					if(isClicking) {
						click(doubleClick,speed);
					}
				}
			}
		});
	}
	
	public void stopClick() {
		isClicking = false;
	}
	
	public void click(boolean doubleClick, int speed) {
		
	}
}
