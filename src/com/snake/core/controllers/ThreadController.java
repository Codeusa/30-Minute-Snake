package com.snake.core.controllers;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.snake.core.SnakeTuple;
import com.snake.core.SquareData;
import com.snake.core.player.PlayerStats;
import com.snake.ui.GameWindow;

public class ThreadController extends Thread {

	ArrayList<ArrayList<SquareData>> squares = new ArrayList<ArrayList<SquareData>>();
	SnakeTuple snakeHeadPostion;
	int sizeOfSnake = 3;
	long speed = 50;
	public static int snakeDirection;
	ArrayList<SnakeTuple> postions = new ArrayList<SnakeTuple>();
	SnakeTuple foodPostion;
	PlayerStats player = new PlayerStats();

	public ThreadController(final SnakeTuple departPostion) {
		this.squares = GameWindow.grid;
		this.snakeHeadPostion = new SnakeTuple(departPostion.x, departPostion.y);
		snakeDirection = 1;

		final SnakeTuple headPostion = new SnakeTuple(
				this.snakeHeadPostion.getX(), this.snakeHeadPostion.getY());
		this.postions.add(headPostion);

		this.foodPostion = new SnakeTuple(GameWindow.height - 1,
				GameWindow.width - 1);
		spawnFood(this.foodPostion);

	}

	@Override
	public void run() {
		while (true) {
			moveInterne(ThreadController.snakeDirection);
			checkForCollision();
			moveExterne();
			deleteSnakeTail();
			pause();
		}
	}

	private void moveInterne(final int direction) {
		switch (direction) {
		case 4:
			this.snakeHeadPostion.changeTupleData(this.snakeHeadPostion.x,
					(this.snakeHeadPostion.y + 1) % 20);
			this.postions.add(new SnakeTuple(this.snakeHeadPostion.x,
					this.snakeHeadPostion.y));
			break;

		case 3:
			if ((this.snakeHeadPostion.y - 1) < 0) {
				this.snakeHeadPostion.changeTupleData(this.snakeHeadPostion.x,
						19);

			} else {
				this.snakeHeadPostion.changeTupleData(this.snakeHeadPostion.x,
						Math.abs(this.snakeHeadPostion.y - 1) % 20);
			}
			this.postions.add(new SnakeTuple(this.snakeHeadPostion.x,
					this.snakeHeadPostion.y));
			break;

		case 2:
			if ((this.snakeHeadPostion.x - 1) < 0) {
				this.snakeHeadPostion.changeTupleData(19,
						this.snakeHeadPostion.y);
			} else {
				this.snakeHeadPostion.changeTupleData(
						Math.abs(this.snakeHeadPostion.x - 1) % 20,
						this.snakeHeadPostion.y);
			}
			this.postions.add(new SnakeTuple(this.snakeHeadPostion.x,
					this.snakeHeadPostion.y));
			break;
		case 1:
			this.snakeHeadPostion.changeTupleData(
					Math.abs(this.snakeHeadPostion.x + 1) % 20,
					this.snakeHeadPostion.y);
			this.postions.add(new SnakeTuple(this.snakeHeadPostion.x,
					this.snakeHeadPostion.y));
			break;
		}
	}

	private void moveExterne() {
		for (final SnakeTuple tuple : this.postions) {
			final int y = tuple.getX();
			final int x = tuple.getY();
			this.squares.get(x).get(y).highlightSquare(0);
		}
	}

	private SnakeTuple areaIsNotOccupied() {
		SnakeTuple tuple;
		int randomX = 0 + (int) (Math.random() * 19);
		int randomY = 0 + (int) (Math.random() * 19);
		tuple = new SnakeTuple(randomX, randomY);
		for (int i = 0; i <= (this.postions.size() - 1); ++i) {
			if ((tuple.getY() == this.postions.get(i).getX())
					&& (tuple.getX() == this.postions.get(i).getY())) {
				randomX = 0 + (int) (Math.random() * 19);
				randomY = 0 + (int) (Math.random() * 19);
				tuple = new SnakeTuple(randomX, randomY);
				i = 0;
			}
		}
		return tuple;
	}

	private void pause() {
		try {
			sleep(this.speed);
		} catch (final InterruptedException e) {

		}
	}

	private void spawnFood(final SnakeTuple foodPostion) {
		this.squares.get(foodPostion.x).get(foodPostion.y).highlightSquare(1);
	}

	private void checkForCollision() {
		final SnakeTuple postionCritique = this.postions.get(this.postions
				.size() - 1);
		for (int i = 0; i <= (this.postions.size() - 2); ++i) {
			final boolean selfCollision = (postionCritique.getX() == this.postions
					.get(i).getX())
					&& (postionCritique.getY() == this.postions.get(i).getY());
			if (selfCollision) {
				endGame();
			}
		}

		final boolean isEatingFood = (postionCritique.getX() == this.foodPostion.y)
				&& (postionCritique.getY() == this.foodPostion.x);
		if (isEatingFood) {
	
			player.setPoints(player.getPoints() + 1);
			System.out.println(player.getPoints());
			this.sizeOfSnake++;
			foodPostion = areaIsNotOccupied();
			spawnFood(foodPostion);
		}
	}

	private void endGame() {
		while (true) {
			pause();
		}
	}

	private void deleteSnakeTail() {
		int comp = this.sizeOfSnake;
		for (int i = this.postions.size() - 1; i >= 0; --i) {
			if (comp == 0) {
				final SnakeTuple tuple = this.postions.get(i);
				this.squares.get(tuple.y).get(tuple.x).highlightSquare(2);

			} else {
				--comp;
			}
		}
		comp = this.sizeOfSnake;
		for (int i = this.postions.size() - 1; i >= 0; --i) {
			if (comp == 0) {
				this.postions.remove(i);
			} else {
				--comp;
			}
		}
	}

}
