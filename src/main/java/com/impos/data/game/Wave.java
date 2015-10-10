package com.impos.data.game;

import com.impos.data.enemy.Enemy;
import com.impos.helpers.Clock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pldmacikowski
 */
public class Wave {
	private float timeSinceLastSpawn = 0;
	private float spawnTime;
	private Enemy enemyType;
	private int maxEnemiesCount;
	private boolean waveCompleted;

	private List<Enemy> enemies = new ArrayList<>();

	public Wave(Enemy enemyType, float spawnTime, int enemiesCount) {
		this.spawnTime = spawnTime;
		this.enemyType = enemyType;
		this.maxEnemiesCount = enemiesCount;
		this.waveCompleted = false;

		spawn();
	}

	public void update() {
		if (enemies.size() < maxEnemiesCount) {
			timeSinceLastSpawn += Clock.delta();
			if (timeSinceLastSpawn > spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}

		boolean isAllEnemiesDead = true;
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				isAllEnemiesDead = false;
				e.update();
				e.draw();
			}
		}
		if (isAllEnemiesDead) {
			waveCompleted = true;
		}
	}

	public boolean isCompleted() {
		return waveCompleted;
	}

	private void spawn() {
		enemies.add(new Enemy(enemyType));
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
}

