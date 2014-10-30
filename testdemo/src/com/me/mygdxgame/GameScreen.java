package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blackvine.actor.Hero;
import com.blackvine.actor.Monster;
import com.blackvine.actor.Npc;
import com.blackvine.actor.OrderDialog;
import com.blackvine.actor.Rocker;
import com.blackvine.drawmap.ScreenMap;

/**
 * Created by IceChen on 14-9-13.
 */
<<<<<<< HEAD
public class GameScreen implements Screen {
	Label fps;
	Label newFPS;

	public static final int ScreenWidth = 800;
	public static final int ScreenHeight = 480;

	private SpriteBatch bath;
=======
public class GameScreen implements Screen{
	Label fps;
	Label newFPS;

    public static final int ScreenWidth = 800;
    public static final int ScreenHeight = 480;

    private SpriteBatch bath;
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
	private ScreenMap screenMap;
	private Hero hero;
	private Rocker rocker;
	private Stage stage;
	BitmapFont font;
<<<<<<< HEAD
	UserControlScreen ucs;
=======
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0

	@Override
	public void render(float v) {
		// 因为render方法在不停执行，那么我们就在这里实时的更新系统帧率的数据
		// 获取fps，然后修改它的显示为获取的系统帧率值
		newFPS = (Label) stage.getRoot().findActor("fps");
		newFPS.setText("FPS:" + Gdx.graphics.getFramesPerSecond());
<<<<<<< HEAD
		hero.go();
		stage.act();
		stage.draw();
		bath.begin();
		hero.draw(bath);
		ucs.draw(bath);
		bath.end();
=======
        hero.go();
		stage.act();
		stage.draw();
        bath.begin();
        hero.draw(bath);
        bath.end();
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
	}

	@Override
	public void resize(int i, int i2) {

	}

	@Override
	public void show() {
<<<<<<< HEAD
		bath = new SpriteBatch();
		ucs = UserControlScreen.getInstance();
		// 黑色显示
		Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(
				Gdx.files.internal("font/d.fnt")), Color.BLACK);
=======
        bath = new SpriteBatch();
		// 黑色显示
		Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("font/d.fnt")),
				Color.BLACK);
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
		fps = new Label("FPS:", labelStyle);
		fps.setName("fps");
		// 显示在左上角位置,减去显示字体的高度，要不然会跑到屏幕外面，根本看不到
		fps.setY(Gdx.graphics.getHeight() - fps.getHeight());
		fps.setX(0);
<<<<<<< HEAD
		stage = new Stage(ScreenWidth, ScreenHeight, true);
=======
		stage = new Stage();
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
		screenMap = ScreenMap.getInstance();
		hero = Hero.getHero();
		rocker = new Rocker();
		font = new BitmapFont(Gdx.files.internal("data/potato.fnt"),
				Gdx.files.internal("data/potato.png"), false);
<<<<<<< HEAD

		stage.addActor(screenMap);
		stage.addActor(rocker);
		stage.addActor(fps);
		stage.addActor(ucs.btnPlayerHead);
		stage.addActor(ucs.btnSkill_1);
		stage.addActor(ucs.btnSkill_2);
		stage.addActor(ucs.btnSkill_3);
		stage.addActor(ucs.btnSkill_4);
		stage.addActor(ucs.btnSkill_spec);
		stage.addActor(ucs.btnItem);
		stage.addActor(ucs.btnMiniMap);
=======
		stage.addActor(screenMap);
		stage.addActor(rocker);
		stage.addActor(fps);
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
	}

<<<<<<< HEAD
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
		bath.dispose();
	}
=======
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
        bath.dispose();
    }
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
}
