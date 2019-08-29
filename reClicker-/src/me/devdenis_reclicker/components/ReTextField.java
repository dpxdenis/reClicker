package me.devdenis_reclicker.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class ReTextField extends JTextField{
	
	private static final long serialVersionUID = 1L;

	public ReTextField() {
		setBackground(new Color(32,32,32));
		setForeground(new Color(200,200,200));
		setBorder(new MatteBorder(0,0,1,0, new Color(150,150,150)));
		
		addMouseListener(new MouseAdapter() {
			
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBorder(new MatteBorder(0,0,1,0, new Color(200,200,200)));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	setBorder(new MatteBorder(0,0,1,0, new Color(150,150,150)));
		    }
		});
		
	}

}
