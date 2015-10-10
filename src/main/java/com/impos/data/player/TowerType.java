package com.impos.data.player;

import com.impos.helpers.Artist;
import org.newdawn.slick.opengl.Texture;

/**
 * @author pldmacikowski
 */
public enum TowerType {

	CANNON_RED(new Texture[] {Artist.loadTextureByName("tower_red"), Artist.loadTextureByName("cannon_red")}, 10),
	CANNON_BLUE(new Texture[] {Artist.loadTextureByName("tower_blue"), Artist.loadTextureByName("tower_blue")}, 15);

	private int damage;
	private Texture[] textures;

	TowerType(Texture[] textures, int damage) {

		this.textures = textures;
		this.damage = damage;
	}

	public Texture[] getTowerTextures() {
		return textures;
	}

	public int getDamage() {
		return damage;
	}
}
