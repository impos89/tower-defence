package com.impos.data.map;

/**
 * @author pldmacikowski
 */
public enum TileType {
	GRASS("grass", true),
	DIRT("dirt", false),
	WATER("water", false),
	NULL("water", false);

	private String textureName;
	private boolean buildable;

	TileType(String textureName, boolean buildable) {

		this.textureName = textureName;
		this.buildable = buildable;
	}

	public String getTextureName() {
		return textureName;
	}

	public boolean isBuildable() {
		return buildable;
	}
}
