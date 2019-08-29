package me.devdenis_reclicker.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ReButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public ReButton(String text) {
		setBackground(new Color(64,64,64));
		setForeground(Color.WHITE);
		setBorder(new LineBorder(new Color(200,200,200)));
		setText(text);
		
		addMouseListener(new MouseAdapter() {
			
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(42,42,42));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	setBackground(new Color(64,64,64));
		    }
		});
	}

}
