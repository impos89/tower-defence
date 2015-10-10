package com.impos.data.map;

import com.impos.data.game.Game;
import com.impos.helpers.Artist;
import com.impos.helpers.shapes.Rectangle;
import com.impos.helpers.shapes.Rectangular;
import org.newdawn.slick.opengl.Texture;

import static com.impos.helpers.Artist.TILE_SIZE;
import static com.impos.helpers.Artist.drawQuadTexture;
import static com.impos.helpers.Artist.loadTextureByName;

/**
 * @author pldmacikowski
 */
public class Tile implements Rectangular {

	private float x;
	private float y;
	private int width;
	private int height;
	private TileType tileType;

	private Texture texture;

	public Tile(float x, float y, int width, int height, TileType tileType) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tileType = tileType;
		this.texture = loadTextureByName(tileType.getTextureName());
	}

	public void draw() {
		drawQuadTexture(texture, toRectangle());
	}

	public int getXPlace() {
		return (int) x / TILE_SIZE;
	}

	public int getYPlace() {
		return (int) y / TILE_SIZE;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TileType getType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public Rectangle toRectangle() {
		return new Rectangle(x, y, (int) width, (int) height);
	}
}
