package me.devdenis_reclicker;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {

	/*
	 * 64 = F6
	 * 65 = F7
	 * 66 = F8
	 * 67 = F9
	 */
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if(e.getKeyCode() == 64) {
			ReClicker.getInstance().getBtnAcStart().doClick();
		} else if (e.getKeyCode() == 65) {
			ReClicker.getInstance().getBtnAcStop().doClick();
		} else if (e.getKeyCode() == 66) {
			ReClicker.getInstance().getBtnAsStart().doClick();
		} else if (e.getKeyCode() == 67) {
			ReClicker.getInstance().getBtnAsStop().doClick();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}
