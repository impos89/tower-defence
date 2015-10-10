package com.impos.data.map;

/**
 * @author pldmacikowski
 */
public class Checkpoint {

	private Tile tile;
	private int xDirection;
	private int yDirection;

	public Checkpoint(Tile tile, int xDirection, int yDirection) {
		this.tile = tile;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public Tile getTile() {
		return tile;
	}

	public int getXDirection() {
		return xDirection;
	}

	public int getYDirection() {
		return yDirection;
	}
}
