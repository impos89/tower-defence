package com.impos.ui;

import com.impos.helpers.Artist;
import com.impos.helpers.MouseHelper;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pldmacikowski
 */
public class UI {
	private Map<String, Button> buttons;

	public UI() {
		buttons = new HashMap<>();
	}

	public void addButton(String name, Texture texture, int x, int y) {
		buttons.put(name, new Button(name, texture, x, y));
	}

	public void draw() {
		for (Button button : buttons.values()) {
			Artist.drawQuadTexture(button.getTexture(), button.toRectangle());
		}
	}

	public boolean isButtonClicked(String buttonName) {
		Button b = getButton(buttonName);
		return MouseHelper.inRangeOfRectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
	}

	public Button getButton(String buttonName) {
		return buttons.get(buttonName);
	}
}
