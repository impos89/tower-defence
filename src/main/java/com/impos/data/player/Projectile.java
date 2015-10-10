package com.impos.data.player;

import com.impos.data.Entity;
import com.impos.data.game.Game;
import com.impos.data.enemy.Enemy;
import com.impos.helpers.Artist;
import com.impos.helpers.Clock;
import com.impos.helpers.shapes.Rectangle;
import com.impos.helpers.shapes.Rectangular;
import org.newdawn.slick.opengl.Texture;

import static com.impos.helpers.Artist.*;

/**
 * @author pldmacikowski
 */
public class Projectile implements Entity, Rectangular {

	private final Enemy target;
	private Texture texture;
	private float x;
	private float y;
	private int speed;
	private int damage;
	private float xVelocity;
	private float yVelocity;
	private boolean alive = true;
	private int width = TILE_SIZE;
	private int height = TILE_SIZE;

	public Projectile(Texture texture, Enemy target, float x, float y, int speed, int damage) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();
	}

	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x);
		float yDistanceFromTarget = Math.abs(target.getY() - y);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;

		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;

		if (target.getX() < x) {
			xVelocity *= -1;
		}
		if (target.getY() < y) {
			yVelocity *= -1;
		}
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
		return TILE_SIZE;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return TILE_SIZE;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void update() {
		if (alive) {
			x += xVelocity * speed * Clock.delta();
			y += yVelocity * speed * Clock.delta();
			if (checkCollision(this.toRectangle(), target.toRectangle())) {
				target.damage(damage);
				alive = false;
			}
			draw();
		}
	}

	@Override
	public void draw() {
		drawQuadTexture(texture, new Rectangle(x, y, width, height));
	}

	@Override
	public Rectangle toRectangle() {
		// TODO refactor numbers 2px/2px size projectile is in the center of the texture.
		return new Rectangle(x + 31, y + 31, 2, 2);
	}
}
