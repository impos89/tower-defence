package com.impos.data.player;

import com.impos.data.Entity;
import com.impos.data.enemy.Enemy;
import com.impos.data.map.Tile;
import com.impos.helpers.shapes.Rectangle;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

import static com.impos.helpers.Artist.drawQuadTexture;

/**
 * @author pldmacikowski
 */
public abstract class Tower implements Entity {

	protected float x;
	protected float y;
	protected int width;
	protected int height;
	private int damage;
	private Enemy Target;
	private Texture[] textures;
	private List<Enemy> enemies;


	public Tower(TowerType towerType, Tile startTile) {
		this.textures = towerType.getTowerTextures();
		this.damage = towerType.getDamage();
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
	}



	@Override
	public float getX() {
		return x;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void update() {
		draw();
	}

	@Override
	public void draw() {
		Rectangle rectangle = new Rectangle(x, y, width, height);
		for (Texture texture : textures) {
			drawQuadTexture(texture, rectangle);
		}
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
}
