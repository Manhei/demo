package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blackvine.actor.Hero;
import com.blackvine.actor.Rocker;
import com.blackvine.constant.Constants;
import com.blackvine.drawmap.ScreenMap;

/**
 * Created by IceChen on 14-9-13.
 */

public class GameScreen implements Screen {
	Label fps;
	Label newFPS;

	public static final int ScreenWidth = Constants.SCREEN_WIDTH;
	public static final int ScreenHeight = Constants.SCREEN_HEIGHT;
	private SpriteBatch bath;

	private ScreenMap screenMap;
	private Hero hero;
	private Rocker rocker;
	private Stage stage;
	private BitmapFont font;
	private UserControlScreen ucs;

	@Override
	public void render(float v) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// 因为render方法在不停执行，那么我们就在这里实时的更新系统帧率的数据
		// 获取fps，然后修改它的显示为获取的系统帧率值
		newFPS = (Label) stage.getRoot().findActor("fps");
		newFPS.setText("FPS:" + Gdx.graphics.getFramesPerSecond());

		hero.go();
		stage.act();
		stage.draw();
		bath.begin();
		hero.draw(bath);
		ucs.draw(bath);
		bath.end();
	}

	@Override
	public void resize(int i, int i2) {

	}

	@Override
	public void show() {
		stage = new Stage(ScreenWidth, ScreenHeight, true);
		bath = new SpriteBatch();
		ucs = UserControlScreen.getInstance();
		// 黑色显示
		Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(
				Gdx.files.internal("font/d.fnt")), Color.BLACK);

		fps = new Label("FPS:", labelStyle);
		fps.setName("fps");
		// 显示在左上角位置,减去显示字体的高度，要不然会跑到屏幕外面，根本看不到
		fps.setY(Constants.SCREEN_HEIGHT * 2 / 3);
		fps.setX(Constants.SCREEN_WIDTH / 2);

		screenMap = ScreenMap.getInstance();
		hero = Hero.getHero();
		rocker = new Rocker();
		font = new BitmapFont(Gdx.files.internal("data/potato.fnt"),
				Gdx.files.internal("data/potato.png"), false);

		stage.addActor(screenMap);
		stage.addActor(rocker);
		stage.addActor(fps);
		ucs.addToStage(stage);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);

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

	public void dispose() {
		bath.dispose();
	}

}
