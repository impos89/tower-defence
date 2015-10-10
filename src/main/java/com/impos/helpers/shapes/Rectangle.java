package com.impos.helpers.shapes;

/**
 * @author pldmacikowski
 */
public final class Rectangle {
	private final float x;
	private final float y;
	private final int width;
	private final int height;

	public Rectangle(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
