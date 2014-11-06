package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blackvine.constant.Constants;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "testdemo";
		cfg.useGL20 = true;
		cfg.width = Constants.SCREEN_WIDTH;
		cfg.height = Constants.SCREEN_HEIGHT;

		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
