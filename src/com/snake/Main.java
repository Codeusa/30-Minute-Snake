package com.snake;

import javax.swing.JFrame;

import com.snake.ui.GameWindow;

public class Main {

	public static void main(final String[] args) {
		GameWindow game = new GameWindow();
		game.setTitle("Twitch Snake");
		game.setSize(300, 300);
		game.setVisible(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
