package com.impos.data.player;

import com.impos.data.Entity;
import com.impos.data.enemy.Enemy;
import com.impos.data.map.Tile;
import com.impos.helpers.Clock;
import com.impos.helpers.shapes.Rectangle;
import com.impos.helpers.shapes.Rectangular;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static com.impos.helpers.Artist.loadTextureByName;

/**
 * @author pldmacikowski
 */
public class TowerCannon extends Tower implements Entity, Rectangular {

	private float angle;
	private int damage;
	private int range;
	private int health;
	private boolean alive;
	private Texture cannonTexture;
	private Tile startTile;
	private float firingSpeed;
	private float timeSinceLastShot;
	private List<Projectile> projectiles = new ArrayList<>();
	private List<Enemy> enemies;
	private Enemy target;
	private boolean targeted = false;

	public TowerCannon(TowerType towerType, Tile startTile, List<Enemy> enemies, int range) {
		super(towerType, startTile);
		this.startTile = startTile;
		this.range = range;
		this.damage = towerType.getDamage();
		this.firingSpeed = 1;
		this.timeSinceLastShot = 0;
		this.enemies = enemies;
		this.target = acquireTarget();
		this.angle = calculateAngle();

		cannonTexture = loadTextureByName("cannon");

	}

	@Override
	public void update() {
		if (!targeted) {
			target = acquireTarget();
		}

		if (target == null || !target.isAlive()) {
			targeted = false;
		}

		timeSinceLastShot += Clock.delta();
		if (timeSinceLastShot > firingSpeed) {
			shoot();
		}

		projectiles.forEach(Projectile::update);
		angle = calculateAngle();

		draw();
	}

	@Override
	public void draw() {
		//TODO
		//		drawQuadTexture(baseTexture, toRectangle());
		//		drawQuadTextureRotate(cannonTexture, toRectangle(), angle);
	}

	private Enemy acquireTarget() {
		Enemy closest = null;

		float closestDistance = 10000;

		for (Enemy e : enemies) {
			if (isInRange(e) && findDistance(e) < closestDistance) {
				closestDistance = findDistance(e);
				closest = e;
			}
			if (closest != null) {
				targeted = true;
			}
		}
		return closest;
	}

	private boolean isInRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance < range && yDistance < range;
	}

	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	private float calculateAngle() {
		//TODO learn atan2
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);

		return (float) Math.toDegrees(angleTemp) - 180; // a cannon is drawn on the left. -180 drawn opposite ;)
	}

	private void shoot() {
		timeSinceLastShot = 0;
		if (target.isAlive()) {
			projectiles.add(new Projectile(loadTextureByName("bullet"), target, x, y, 600, 100));
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
	public Rectangle toRectangle() {
		return new Rectangle(x, y, width, height);
	}


	public float getFiringSpeed() {
		return firingSpeed;
	}

	public void setFiringSpeed(float firingSpeed) {
		this.firingSpeed = firingSpeed;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	@Override
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
