package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blackvine.constant.Constants;
import com.blackvine.drawmap.NinePieceMap;
import com.blackvine.drawmap.ScreenMap;
import com.blackvine.drawmap.WorldMap;
import com.blackvine.manager.SingleAssetManager;

/**
 * Created by IceChen on 14/10/28.
 */
class LodingScreen implements Screen {

	private ScreenMap screenMap;
	private MyGdxGame game;
	private SingleAssetManager am;
	private Label loadingProgress;
	private Stage stage;

	LodingScreen(MyGdxGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		WorldMap.getWorldMap().updateAssetManager();

		if (!WorldMap.getWorldMap().isLoding && am.update()) {
			// NinePieceMap.getNinePieceMap().updatePiece();
			// NinePieceMap.getNinePieceMap().preload();
			game.setScreen(new GameScreen());

		}
		loadingProgress.setText("LoadingProgress:"
				+ (int) (am.getProgress() * 100) + " %");
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		screenMap = ScreenMap.getInstance();
		screenMap.load();
		am = SingleAssetManager.getSingleAssetManager();
		Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(
				Gdx.files.internal("font/d.fnt")), Color.BLACK);
		loadingProgress = new Label("loadingProgress", labelStyle);
		loadingProgress.setX(Constants.SCREEN_WIDTH / 2
				- loadingProgress.getWidth() / 2 - 25);
		loadingProgress.setY(Constants.SCREEN_HEIGHT / 2
				- loadingProgress.getHeight() / 2);
		stage = new Stage();
		stage.addActor(loadingProgress);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		this.dispose();
	}
}
