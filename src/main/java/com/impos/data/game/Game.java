package com.impos.data.game;

import com.impos.data.map.Tile;
import com.impos.data.map.TileGrid;
import com.impos.data.enemy.Enemy;
import com.impos.data.player.Player;
import com.impos.helpers.Artist;

import static com.impos.helpers.Artist.TILE_SIZE;

/**
 * @author pldmacikowski
 */
public class Game {

	// Temp variables
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;


	public Game(int[][] map) {
		grid = new TileGrid(map);

		Tile enemyStartTile = grid.getTile(3, 12);
		Enemy enemy = new Enemy(Artist.loadTextureByName("enemy"), enemyStartTile, TILE_SIZE, TILE_SIZE, grid, 120, 100);

		waveManager = new WaveManager(enemy, 2, 5);
		player = new Player(grid, waveManager);
	}

	public void update() {
		grid.draw();
		waveManager.update();
		player.update();

	}

}
