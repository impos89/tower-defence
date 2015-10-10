package com.impos.helpers;

import org.lwjgl.input.Mouse;

/**
 * @author pldmacikowski
 */
public class MouseHelper {

	public static float getY() {
		return Artist.HEIGHT - Mouse.getY() - 1;
	}

	public static boolean inRangeOfRectangle(int x, int y, int width, int height) {
		float mouseX = Mouse.getX();
		float mouseY = getY();
		return mouseX > x
				&& mouseX < x + width
				&& mouseY > y
				&& mouseY < y + height;
	}
}
