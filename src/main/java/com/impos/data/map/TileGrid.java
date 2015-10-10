package com.impos.data.map;

import com.impos.helpers.MouseHelper;
import org.lwjgl.input.Mouse;

import static com.impos.helpers.Artist.TILE_SIZE;

/**
 * @author pldmacikowski
 */
public class TileGrid {

	public Tile[][] map;
	private int tilesWide = 20;
	private int tilesHigh = 15;

	public TileGrid() {
		this.map = new Tile[tilesWide][tilesHigh];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.GRASS);
			}
		}
	}

	public TileGrid(int[][] newMap) {

		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;

		this.map = new Tile[tilesWide][tilesHigh];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]) {
					case 0:
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.GRASS);
						break;
					case 1:
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.DIRT);
						break;
					case 2:
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.WATER);
						break;

				}
			}
		}
	}

	public void setTile(int xPlace, int yPlace, TileType type) {
		map[xPlace][yPlace] = new Tile(xPlace * 64, yPlace * 64, 64, 64, type);
	}

	public Tile getTile(int xPlace, int yPlace) {
		if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1) {
			return map[xPlace][yPlace];
		} else {
			return new Tile(0, 0, 0, 0, TileType.NULL);
		}
	}

	public Tile getTileMouseOver() {
		int xCoord = (int) Math.floor(Mouse.getX() / TILE_SIZE);
		int yCoord = (int) Math.floor(MouseHelper.getY() / TILE_SIZE);
		return getTile(xCoord, yCoord);
	}



	public void draw() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].draw();
			}
		}
	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
}

