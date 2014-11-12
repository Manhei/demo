package com.me.mygdxgame;

import android.os.Bundle;
import android.view.Display;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.blackvine.constant.Constants;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display mDisplay = getWindowManager().getDefaultDisplay();
		Constants.SCREEN_WIDTH = mDisplay.getWidth();
		Constants.SCREEN_HEIGHT = mDisplay.getHeight();

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;
		initialize(new MyGdxGame(), cfg);
	}

	@Override
	public void onBackPressed() {
	}

}