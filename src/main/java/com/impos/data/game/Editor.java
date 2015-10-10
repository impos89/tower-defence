package com.impos.data.game;

import com.impos.data.map.TileGrid;
import com.impos.data.map.TileType;
import com.impos.helpers.Artist;
import com.impos.helpers.Leveler;
import com.impos.helpers.MouseHelper;
import com.impos.helpers.StateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static com.impos.helpers.Artist.*;

/**
 * @author pldmacikowski
 */
public class Editor {

	TileGrid grid;
	private int index;
	private TileType[] types = new TileType[] {TileType.GRASS, TileType.WATER, TileType.DIRT};

	public Editor() {
		grid = new TileGrid();
//		grid = Leveler.loadMap("newMap1.txt");
		index = 0;
	}

	public void update() {
		grid.draw();

		// handle mouse input
		if (Mouse.isButtonDown(0)) {
			setTile();
		}

		// Handle keyboard input
		if (StateManager.getCurrentState() != StateManager.GameState.EDITOR)
			return;

		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
				Leveler.saveMap("newMap", grid);
			}
		}
	}

	private void moveIndex() {
		index++;
		if (index >= types.length) {
			index = 0;
		}
	}

	private void setTile() {
		int xCoord = (int) Math.floor(Mouse.getX() / TILE_SIZE);
		int yCoord = (int) Math.floor(MouseHelper.getY() / TILE_SIZE);
		grid.setTile(xCoord, yCoord, types[index]);
	}
}
