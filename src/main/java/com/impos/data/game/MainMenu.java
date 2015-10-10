package com.impos.data.game;

import com.impos.helpers.Artist;
import com.impos.helpers.StateManager;
import com.impos.helpers.shapes.Rectangle;
import com.impos.ui.UI;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

/**
 * @author pldmacikowski
 */
public class MainMenu {


	private Texture background;
	private UI menuUI;

	public MainMenu() {
		background = Artist.loadTextureByName("menubackground");
		menuUI = new UI();

		menuUI.addButton("startButton", Artist.loadTextureByName("startButton"), 380, 450);
		menuUI.addButton("editorButton", Artist.loadTextureByName("editorButton"), 270, 555);
		menuUI.addButton("exitButton", Artist.loadTextureByName("exitButton"), 985, 865);
	}

	private void updateButtons() {
		if(Mouse.isButtonDown(0)) {
			if(menuUI.isButtonClicked("startButton")) {
				StateManager.setState(StateManager.GameState.GAME);
			}
			if(menuUI.isButtonClicked("editorButton")) {
				StateManager.setState(StateManager.GameState.EDITOR);
			}
			if(menuUI.isButtonClicked("exitButton")) {
				Display.destroy();
				System.exit(0);
			}
		}
	}

	public void update() {
		Artist.drawQuadTexture(background, new Rectangle(0, 0, 2048, 1024));
		menuUI.draw();
		updateButtons();
	}
}
