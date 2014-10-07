package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
public class testScreen implements Screen, InputProcessor {
	Label fps;
	Label newFPS;

	String[] said = {
		"你好啊",
		"需要你去杀一只猪来做晚餐",
		"任务完成了啊，真棒"
	};
	private OrderDialog orderDialog;
	private ScreenMap screenMap;
	private Hero hero;
	private Rocker rocker;
	private Stage stage;
	private Npc npc;
	private Monster monster;
	Texture texture;
	ImageButton Btn_A_OK;
	ImageButton Btn_B_Cancel;
	ImageButton Btn_SHOW;
	BitmapFont font;
	Window dialogWindow;

	@Override
	public void render(float v) {
		// 因为render方法在不停执行，那么我们就在这里实时的更新系统帧率的数据
		// 获取fps，然后修改它的显示为获取的系统帧率值
		newFPS = (Label) stage.getRoot().findActor("fps");
		newFPS.setText("FPS:" + Gdx.graphics.getFramesPerSecond());
		// npc.cutDownHp(1);
		if (!npc.isAlive()) {
			if (npc.isVisible()) {
				npc.remove();
			}
		}
		if (npc.isVisible()) {
			npc.setScreenPosition(screenMap.getX_in_wordmap(),
					screenMap.getY_in_wordmap());
		}
		if (!orderDialog.isAlive()) {
			orderDialog.remove();
		}
		monster.autoWalk();
		monster.setScreenPosition(screenMap.getX_in_wordmap(),
					screenMap.getY_in_wordmap());
		stage.act();
		stage.draw();

		// screenMap.Draw();
	}

	@Override
	public void resize(int i, int i2) {

	}

	@Override
	public void show() {
		// 黑色显示
		Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(),
				Color.BLACK);
		fps = new Label("FPS:", labelStyle);
		fps.setName("fps");
		// 显示在左上角位置,减去显示字体的高度，要不然会跑到屏幕外面，根本看不到
		fps.setY(Gdx.graphics.getHeight() - fps.getHeight());
		fps.setX(0);
		stage = new Stage();
		screenMap = ScreenMap.getInstance();
		hero = Hero.getHero();
		orderDialog = new OrderDialog();
		orderDialog.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				orderDialog.cutdownTime();
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		npc = new Npc();
		npc.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				orderDialog.setSaid(said);
				stage.addActor(orderDialog);
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		monster = new Monster(2600, 1024);
		rocker = new Rocker();
		font = new BitmapFont(Gdx.files.internal("data/potato.fnt"),
				Gdx.files.internal("data/potato.png"), false);
		stage.addActor(screenMap);
		stage.addActor(hero);
		stage.addActor(rocker);
		stage.addActor(npc);
		stage.addActor(fps);
		stage.addActor(monster);
		setButton();
		setWindow();
		setBtnListener();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
	}

	public void setButton() {
		texture = new Texture(Gdx.files.internal("data/control.png"));

		TextureRegion[][] spilt = TextureRegion.split(texture, 64, 64);

		TextureRegion[] regionBtn = new TextureRegion[6];
		// ��ʾ
		regionBtn[0] = spilt[0][0];
		regionBtn[1] = spilt[0][1];
		// ȷ��
		regionBtn[2] = spilt[0][2];
		regionBtn[3] = spilt[0][3];
		// ȡ��
		regionBtn[4] = spilt[1][0];
		regionBtn[5] = spilt[1][1];

		TextureRegionDrawable Btn_SHOW_UP = new TextureRegionDrawable(
				regionBtn[0]);
		TextureRegionDrawable Btn_SHOW_DOWN = new TextureRegionDrawable(
				regionBtn[1]);

		TextureRegionDrawable Btn_A_UP = new TextureRegionDrawable(regionBtn[2]);
		TextureRegionDrawable Btn_A_DOWN = new TextureRegionDrawable(
				regionBtn[3]);

		TextureRegionDrawable Btn_B_UP = new TextureRegionDrawable(regionBtn[4]);
		TextureRegionDrawable Btn_B_DOWN = new TextureRegionDrawable(
				regionBtn[5]);

		Btn_SHOW = new ImageButton(Btn_SHOW_UP, Btn_SHOW_DOWN);

		Btn_A_OK = new ImageButton(Btn_A_UP, Btn_A_DOWN);

		Btn_B_Cancel = new ImageButton(Btn_B_UP, Btn_B_DOWN);

	}

	public void setWindow() {
		TextureRegionDrawable WindowDrable = new TextureRegionDrawable(
				new TextureRegion(new Texture(
						Gdx.files.internal("data/dialog.png"))));

		WindowStyle style = new WindowStyle(font, Color.RED, WindowDrable);

		dialogWindow = new Window("Game", style);

		dialogWindow.setWidth(Gdx.graphics.getWidth() / 1.5f);
		dialogWindow.setHeight(Gdx.graphics.getHeight() / 1.5f);

		dialogWindow.setPosition(100, 80);

		dialogWindow.setMovable(true);

		Btn_A_OK.setPosition(Gdx.graphics.getWidth() / 10, 0);

		Btn_B_Cancel.setPosition(Gdx.graphics.getWidth() / 3, 0);

		dialogWindow.addActor(Btn_A_OK);

		dialogWindow.addActor(Btn_B_Cancel);

	}

	public void setBtnListener() {
		Btn_SHOW.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				stage.addActor(dialogWindow);

				return true;
			}

		});

		Btn_A_OK.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				Gdx.app.exit();

				return true;
			}

		});

		Btn_B_Cancel.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				dialogWindow.remove();

				return super.touchDown(event, x, y, pointer, button);
			}

		});
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

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.BACK) {
			stage.addActor(dialogWindow);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
