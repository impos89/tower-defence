package com.impos.data.enemy;

import com.impos.data.map.Checkpoint;
import com.impos.data.Entity;
import com.impos.data.map.Tile;
import com.impos.data.map.TileGrid;
import com.impos.helpers.Clock;
import com.impos.helpers.shapes.Rectangle;
import com.impos.helpers.shapes.Rectangular;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static com.impos.helpers.Artist.drawQuadTexture;

/**
 * @author pldmacikowski
 */
public class Enemy implements Entity, Rectangular {

	int[] directions;
	private float x;
	private float y;
	private int width;
	private int height;
	private int health;
	private boolean alive;
	private int currentCheckpoint;
	private Texture texture;
	private Tile startTile;
	private TileGrid grid;

	private float speed;

	// Temp variables  - should be changed after refactor
	private boolean first = true;

	private List<Checkpoint> checkpoints = new ArrayList<>();

	public Enemy(Texture texture, Tile startTile, int width, int height, TileGrid grid, float speed, int health) {
		this.setAlive(true);
		this.setHealth(health);
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.texture = texture;
		this.grid = grid;

		//TODO Directions should be replaced by a class with friendly methods
		directions = new int[2];
		// X direction
		directions[0] = 0;
		// Y direction
		directions[1] = 0;
		directions = findNextDirection(startTile);

		currentCheckpoint = 0;
		populateCheckpointList();
	}

	public Enemy(Enemy e) {
		this.setAlive(true);
		this.setHealth(e.getHealth());
		this.startTile = e.getStartTile();
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = e.getWidth();
		this.height = e.getHeight();
		this.speed = e.getSpeed();
		this.texture = e.getTexture();
		this.grid = e.getGrid();

		directions = new int[2];
		// X direction
		directions[0] = 0;
		// Y direction
		directions[1] = 0;
		directions = findNextDirection(startTile);
		currentCheckpoint = 0;
		populateCheckpointList();
	}

	@Override
	public void draw() {

		drawQuadTexture(texture, new Rectangle(x, y, width, height));
	}

	@Override
	public void update() {
		if (first) { //TODO change this workaround
			first = false;
		} else {
			if (checkpointReached()) {
				if (currentCheckpoint + 1 == checkpoints.size()) {
					System.out.println("Enemy reached end of maze");
					die();
				} else {
					currentCheckpoint++;
				}
			} else {
				x += Clock.delta() * checkpoints.get(currentCheckpoint).getXDirection() * speed;
				y += Clock.delta() * checkpoints.get(currentCheckpoint).getYDirection() * speed;
			}
		}
	}

	@Override
	public Rectangle toRectangle() {
		return new Rectangle(x, y, width, height);
	}

	public void damage(int amount) {
		int health = getHealth() - amount;
		setHealth(health);
		if (health < 0) {
			System.out.println("killed");
			die();
		}
	}

	//#####################################################################################
	//# GEO, PATH FINDING - Should be replaced by Geodata and Pathfinding implementations #
	//#####################################################################################
	private void populateCheckpointList() {
		checkpoints.add(findNextCheckpoint(startTile, directions));

		int counter = 0;
		boolean cont = true;
		while (cont) {
			int[] currentDirection = findNextDirection(checkpoints.get(counter).getTile());
			//TODO counter = 20 is temporary - now Produces array out of bound exception!
			if (currentDirection[0] == 2 || counter == 20) {
				cont = false;
			} else {

				checkpoints.add(findNextCheckpoint(
						checkpoints.get(counter).getTile(),
						directions = findNextDirection(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
	}

	public Checkpoint findNextCheckpoint(Tile s, int[] directions) {
		Tile next = null;
		Checkpoint checkpoint = null;

		// decide if next checkpoint is found
		boolean found = false;

		// how many tiles to next checkpoint
		int counter = 1;

		while (!found) {


			int nextXCoord = s.getXPlace() + directions[0] * counter;
			int nextYCoord = s.getYPlace() + directions[1] * counter;

			if (s.getXPlace() == grid.getTilesWide() ||
					s.getYPlace() == grid.getTilesHigh() ||
					s.getType() != grid.getTile(nextXCoord, nextYCoord).getType()) {

				found = true;
				// tile with previous counter is a checkpoint - there's no way in same direction
				counter -= 1;
				int previousXCoord = s.getXPlace() + directions[0] * counter;
				int previousYCoord = s.getYPlace() + directions[1] * counter;
				next = grid.getTile(previousXCoord, previousYCoord);
			}

			counter++;
			checkpoint = new Checkpoint(next, directions[0], directions[1]);
		}
		return checkpoint;
	}

	private int[] findNextDirection(Tile s) {
		int[] dir = new int[2];

		Tile up = grid.getTile(s.getXPlace(), s.getYPlace() - 1);
		Tile down = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
		Tile right = grid.getTile(s.getXPlace() + 1, s.getYPlace());
		Tile left = grid.getTile(s.getXPlace() - 1, s.getYPlace());

		if (s.getType() == up.getType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == right.getType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == down.getType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == left.getType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
			//System.out.println("NO DIRECTION");
		}
		return dir;
	}

	private boolean checkpointReached() {
		boolean reached = false;

		Tile t = checkpoints.get(currentCheckpoint).getTile();
		// Check if position reach tile within variance of 3 (arbitrary)
		// 3 is the control pixel to move an enemy when inside the checkpoint tile
		if (x > t.getX() - 3 &&
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3) {

			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}

	//#####################################################################################
	//# GETTERS / SETTERS                                                                 #
	//#####################################################################################

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

	public int getCurrentCheckpoint() {
		return currentCheckpoint;
	}

	public void setCurrentCheckpoint(int currentCheckpoint) {
		this.currentCheckpoint = currentCheckpoint;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public TileGrid getGrid() {
		return grid;
	}

	public void setGrid(TileGrid grid) {
		this.grid = grid;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void die() {
		this.setAlive(false);
	}
}
