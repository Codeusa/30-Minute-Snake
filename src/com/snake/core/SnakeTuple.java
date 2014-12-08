package com.snake.core;

public class SnakeTuple {
	public int x;
	public int y;
	public int offx;
	public int offy;

	public SnakeTuple(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public void changeTupleData(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getOffX() {
		return this.offx;
	}

	public int getOffY() {
		return this.offy;
	}
}
