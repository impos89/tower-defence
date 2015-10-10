package com.impos.data.player;

import com.impos.data.enemy.Enemy;
import com.impos.data.map.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pldmacikowski
 */
public class TowerCannonBlue extends Tower {

	private final int range;
	List<Enemy> enemies = new ArrayList<>();

	public TowerCannonBlue(TowerType towerType, Tile startTile, List<Enemy> enemies, int range) {
		super(towerType, startTile);
		this.enemies = enemies;
		this.range = range;
	}

	@Override
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
}
