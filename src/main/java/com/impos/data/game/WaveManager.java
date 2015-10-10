package com.impos.data.game;

import com.impos.data.enemy.Enemy;

/**
 * @author pldmacikowski
 */
public class WaveManager {

	private float timeSinceLastWave;

	private int waveNumber;
	private int enemiesPerWave;
	private float spawnCooldown;
	private Enemy enemyType;
	private Wave currentWave;



	public WaveManager(Enemy enemyType, float spawnCooldown, int enemiesPerWave) {
		this.waveNumber = 0;
		this.timeSinceLastWave = 0;
		this.enemiesPerWave = enemiesPerWave;
		this.enemyType = enemyType;
		this.spawnCooldown = spawnCooldown;
		this.currentWave = null;

		newWave();
	}

	public void update() {
		if (!currentWave.isCompleted()) {
			currentWave.update();
		} else {
			newWave();
		}
	}

	public void newWave() {
		currentWave = new Wave(enemyType, spawnCooldown, enemiesPerWave);
		waveNumber++;
		System.out.println(String.format("Wave %s", waveNumber));
	}

	public Wave getCurrentWave() {
		return currentWave;
	}


}
