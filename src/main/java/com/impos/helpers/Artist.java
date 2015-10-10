package com.impos.helpers;

import com.impos.helpers.shapes.Rectangle;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author pldmacikowski
 */
public class Artist {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 960;
	public static final int TILE_SIZE = 64;

	public static void beginSession() {
		Display.setTitle("Tower Defence");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1); // Camera
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);

		//enable blending to allow transparent bits on an image
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static boolean checkCollision(Rectangle rectangular1, Rectangle rectangular2) {

		float r1x = rectangular1.getX();
		float r1y = rectangular1.getY();
		float r2x = rectangular2.getX();
		float r2y = rectangular2.getY();

		float r1Width = rectangular1.getWidth();
		float r1Height = rectangular1.getHeight();
		float r2Width = rectangular2.getWidth();
		float r2Height = rectangular2.getHeight();

		return r1x + r1Width > r2x
			&& r1x < r2x + r2Width
			&& r1y + r1Height > r2y
			&& r1y < r2y + r2Height;

	}

	public static void drawQuad(Rectangle rectangle) {
		glBegin(GL_QUADS);
		glVertex2f(rectangle.getX(), rectangle.getY());
		glVertex2f(rectangle.getX() + rectangle.getWidth(), rectangle.getY());
		glVertex2f(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight());
		glVertex2f(rectangle.getX(), rectangle.getY() + rectangle.getHeight());
		glEnd();
	}

	public static void drawQuadTexture(Texture texture, Rectangle rectangle) {

		texture.bind();
		glTranslatef(rectangle.getX(), rectangle.getY(), 0);
		glBegin(GL_QUADS);

		glTexCoord2f(0, 0);
		glVertex2f(0, 0);

		glTexCoord2f(1, 0);
		glVertex2f(rectangle.getWidth(), 0);

		glTexCoord2f(1, 1);
		glVertex2f(rectangle.getWidth(), rectangle.getHeight());

		glTexCoord2f(0, 1);
		glVertex2f(0, rectangle.getHeight());

		glEnd();
		glLoadIdentity();
	}

	public static void drawQuadTextureRotate(Texture texture, Rectangle rectangle, float angle) {
		texture.bind();
		glTranslatef(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-rectangle.getWidth() / 2, -rectangle.getHeight() / 2, 0);
		glBegin(GL_QUADS);

		glTexCoord2f(0, 0);
		glVertex2f(0, 0);

		glTexCoord2f(1, 0);
		glVertex2f(rectangle.getWidth(), 0);

		glTexCoord2f(1, 1);
		glVertex2f(rectangle.getWidth(), rectangle.getHeight());

		glTexCoord2f(0, 1);
		glVertex2f(0, rectangle.getHeight());

		glEnd();
		glLoadIdentity();
	}

	public static void drawLine(float startX, float startY, float endX, float endY) {
		glBegin(GL_LINES);
		glVertex2f(startX, startY);
		glVertex2f(endX, endY);
		glEnd();
	}

	public static Texture loadTexture(String path, String fileType) {
		Texture tex = null;
		try {
			InputStream in = ResourceLoader.getResourceAsStream(path);
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}

	public static Texture loadTextureByName(String name) {
		return loadTexture("res/" + name + ".png", "PNG");
	}


}
