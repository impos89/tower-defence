package com.impos.helpers;

import com.impos.data.game.Editor;
import com.impos.data.game.Game;
import com.impos.data.game.MainMenu;

/**
 * @author pldmacikowski
 */
public class StateManager {
	private static GameState currentState = GameState.MAIN_MENU;
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;

	public enum GameState {
		MAIN_MENU, GAME, EDITOR
	}

	//TODO temporary map
	private static int[][] map = {
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
	};

	public static void update() {
		switch (currentState) {
			case MAIN_MENU:
				if(mainMenu == null) {
					mainMenu = new MainMenu();
				}
				mainMenu.update();
				break;
			case GAME:
				if(game == null) {
					game = new Game(map);
				}
				game.update();
				break;
			case EDITOR:
				if(editor == null) {
					editor = new Editor();
				}
				editor.update();
				break;

		}
	}

	public static void setState(GameState newState) {
		currentState = newState;
	}

	public static GameState getCurrentState() {
		return currentState;
	}
}
