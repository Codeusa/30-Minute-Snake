package com.snake.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.snake.core.SnakeTuple;
import com.snake.core.SquareData;
import com.snake.core.controllers.ThreadController;
import com.snake.core.listeners.KeyBoardListener;

public class GameWindow extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -3939850414035826311L;

	public static ArrayList<ArrayList<SquareData>> grid;
	public static int width = 20;
	public static int height = 20;
	public static JFrame gameFrame;

	public GameWindow() {
	
		gameFrame = this;
		grid = new ArrayList<ArrayList<SquareData>>();
		ArrayList<SquareData> data;
		for (int i = 0; i < width; ++i) {
			data = new ArrayList<SquareData>();
			for (int j = 0; j < height; j++) {
				final SquareData squareData = new SquareData(2);
				data.add(squareData);
			}
			grid.add(data);
		}

		getContentPane().setLayout(new GridLayout(20, 20, 0, 0));
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				getContentPane().add(grid.get(i).get(j).gameSquare);
			}
		}
		
		final SnakeTuple postion = new SnakeTuple(10, 10);
		final ThreadController controller = new ThreadController(postion);
		controller.start();
		this.addKeyListener(new KeyBoardListener());

	}
	
	public static JFrame gameWindow() {
		return gameFrame;
	}

}
