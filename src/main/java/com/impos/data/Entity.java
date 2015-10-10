package com.impos.data;

/**
 * Main entity interface describing any drawable object in the game.
 *
 * @author pldmacikowski
 */
public interface Entity {

	/**
	 * Get X position of the entity
	 */
	float getX();

	/**
	 * Set X position of the entity
	 */
	void setX(float x);

	/**
	 * Get Y position of the entity
	 */
	float getY();

	/**
	 * Set Y position of the entity
	 */
	void setY(float y);

	/**
	 * Get width of the entity
	 */
	int getWidth();

	/**
	 * Set width of the entity
	 */
	void setWidth(int width);

	/**
	 * Get height of the entity
	 */
	int getHeight();

	/**
	 * Set height of the entity
	 */
	void setHeight(int height);

	/**
	 * Update entity state. This method is intended to be use per each tick of the time in the game
	 */
	void update();

	/**
	 * Draw an object on the canvas
	 */
	void draw();
}
