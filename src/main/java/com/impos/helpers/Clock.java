package com.impos.helpers;

import org.lwjgl.Sys;

/**
 * @author pldmacikowski
 */
public class Clock {

	public static long lastFrame;
	public static long totalTime;
	public static float d = 0;
	public static float multiplier = 1;
	public static boolean paused = false;

	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	public static float delta() {
		if (paused) {
			return 0;
		} else {
			return d * multiplier;
		}
	}

	public static float TotalTime() {
		return totalTime;
	}

	public static float getMultiplier() {
		return multiplier;
	}

	public static void changeMultiplier(float change) {
		if (multiplier + change > -1 && multiplier + change < 7) {
			multiplier += change;
		}
	}

	public static void setPause(boolean isPause) {
		paused = isPause;
	}

	public static void update() {
		d = getDelta();
		totalTime += d;
	}

	/**
	 * Get Time elapsed from last frame
	 */
	private static float getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		//Windows OS issue hotfix - moving a windows does freezes
		if(delta * 0.001f > 0.05f) {
			return 0.05f;
		}
		return delta * 0.001f;
	}

}
