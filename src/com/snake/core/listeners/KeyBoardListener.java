package com.snake.core.listeners;

import com.snake.core.controllers.ThreadController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoardListener extends KeyAdapter {
	@Override
	public void keyPressed(final KeyEvent event) {
		switch (event.getKeyCode()) {

		case 39: // right
			if (ThreadController.snakeDirection != 2)
					ThreadController.snakeDirection = 1;
			break;
		case 38: // up
				if (ThreadController.snakeDirection != 4)
						ThreadController.snakeDirection = 3;
			break;
		case 37: // left
			if (ThreadController.snakeDirection != 1) 
				ThreadController.snakeDirection = 2;
			break;
		case 40: // down
			if (ThreadController.snakeDirection != 3)
				ThreadController.snakeDirection = 4;
			break;
		default:
			break;
		}
	}
}
