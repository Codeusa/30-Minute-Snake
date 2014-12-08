package com.snake.ui.panels;

import java.awt.Color;

import javax.swing.JPanel;

public class GameSquare extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 5722566426261003063L;

	public GameSquare(final Color color) {
		this.setBackground(color);
	}

	public void changeColor(final Color color) {
		this.setBackground(color);
		this.repaint();	
	}
	
}
