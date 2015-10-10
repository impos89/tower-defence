package com.impos.helpers;

import com.impos.data.map.Tile;
import com.impos.data.map.TileGrid;
import com.impos.data.map.TileType;

import java.io.*;

/**
 * @author pldmacikowski
 */
public class Leveler {

	public static TileGrid loadMap(String mapName) {

		String savePath = String.format("saves/%s", mapName);
		String data = "";
		try (FileReader reader = new FileReader(savePath)) {
			try (BufferedReader bf = new BufferedReader(reader)) {
				data = bf.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TileGrid grid = new TileGrid();
		for (int i = 0; i < grid.getTilesWide(); i++) {
			for (int j = 0; j < grid.getTilesHigh(); j++) {
				grid.setTile(i, j, getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
			}
		}
		return grid;

	}

	public static void saveMap(String mapName, TileGrid grid) {
		String mapData = "";
		for (int i = 0; i < grid.getTilesWide(); i++) {
			for (int j = 0; j < grid.getTilesHigh(); j++) {
				mapData += getTileID(grid.getTile(i, j));
			}
		}

		int count = 1;
		String saveName = String.format("saves/%s%s.txt", mapName, count);
		File file = new File(saveName);
		while (new File(saveName).exists()) {
			count++;
			saveName = String.format("saves/%s%s.txt", mapName, count);
			file = new File(saveName);
		}
		try (FileWriter fw = new FileWriter(file)) {
			fw.write(mapData);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static TileType getTileType(String id) {

		TileType result = TileType.NULL;
		Integer rawId = Integer.valueOf(id);
		for (TileType tiletype : TileType.values()) {
			if (rawId == tiletype.ordinal()) {
				result = tiletype;
			}
		}
		return result;

	}

	public static String getTileID(Tile t) {

		String id;
		int rawId = t.getType().ordinal();

		if (rawId < 0 || rawId > 3) {
			id = "E";
		} else {
			id = String.valueOf(rawId);
		}

		return id;
	}

}
