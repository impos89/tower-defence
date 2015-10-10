package com.impos.data.player;

import com.impos.data.enemy.Enemy;
import com.impos.data.map.TileGrid;
import com.impos.data.game.WaveManager;
import com.impos.helpers.Artist;
import com.impos.helpers.StateManager;
import com.impos.helpers.shapes.Rectangle;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pldmacikowski
 */
public class Player {

	private TileGrid grid;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<>();
	}

	public void update() {
		towerList.forEach(p -> {
			p.setEnemies(waveManager.getCurrentWave().getEnemies());
			p.update();
		});


		if (StateManager.getCurrentState() != StateManager.GameState.GAME) {
			return;
		}
		while (Keyboard.next()) {

			List<Enemy> enemies = waveManager.getCurrentWave().getEnemies();
			if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {
				towerList.add(new TowerCannon(TowerType.CANNON_RED, grid.getTileMouseOver(), enemies, 200));
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_Y && Keyboard.getEventKeyState()) {
				towerList.add(new TowerCannonBlue(TowerType.CANNON_BLUE, grid.getTileMouseOver(), enemies, 200));
			}
		}

	}


}

// Szrot na potem

