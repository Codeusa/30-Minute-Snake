package com.snake.core;

import java.awt.Color;
import java.util.ArrayList;

import com.snake.ui.panels.GameSquare;

public class SquareData {

	ArrayList<Color> colors = new ArrayList<Color>();
	int color;
	public GameSquare gameSquare;

	public SquareData(final int color) {
		this.colors.add(Color.DARK_GRAY);
		this.colors.add(Color.BLUE);
		this.colors.add(Color.WHITE);
		this.color = color;
		this.gameSquare = new GameSquare(this.colors.get(this.color));
	}

	public void highlightSquare(final int color) {
		this.gameSquare.changeColor(this.colors.get(color));
	}

}
