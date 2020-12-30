package de.linus.advancedGui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class AdvFrame extends JFrame{
	private static final long serialVersionUID = -3947488983759302769L;
	public static final int WIDTH = 800; 
	public static final int HEIGHT = 640;
	
	public AdvFrame() {
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.init();
		this.setVisible(true);	
	}
	
	public void setTransparent(boolean transparent) {
		this.setUndecorated(transparent);
		this.setBackground(transparent ? new Color(0,0,0,0) : Color.WHITE);
	}
	
	protected void init() {
	}
}
