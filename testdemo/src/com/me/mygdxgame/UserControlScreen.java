package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blackvine.actor.Hero;
import com.blackvine.animation.MyAnimation;

/**
 * 用户操作层 状态显示 技能按钮 小地图等界面
 * 
 * @author songjunfeng
 * 
 */
public class UserControlScreen extends Actor {

	private static UserControlScreen Instance = null;
	// Texture headTexture;
	TextureRegion headTextureRegion, itemTexRegion, miniMapTexRegion,
			chatTexRegion, settingTexRegion, missionDetailTexRegion,
			teamTexRegion, getGoodsTexRegion, systemNoticeTexRegion;
	Pixmap HeadPix;
	SpriteBatch batch;
	Button btnPlayerHead, btnSkill_1, btnSkill_2, btnSkill_3, btnSkill_4,
			btnSkill_spec, btnItem, btnMiniMap, btnChat, btnSetting;
	TextureRegion hpBorderTexRegion, hpTexRegion, monterHpTexRegion;
	TextureRegion mpBorderTexRegion, mpTexRegion;
	TextureRegion skillTexRegion_1, skillTexRegion_2, skillTexRegion_3,
			skillTexRegion_4, skillTexRegion_spec;
	TextureRegionDrawable temp;

	MyAnimation ani;
	float stateTime = 0;
	private TextureRegion currentFrame;
	public static boolean flag = false;

	public static UserControlScreen getInstance() {
		if (Instance == null) {
			Instance = new UserControlScreen();
		}
		return Instance;
	}

	private UserControlScreen() {
		batch = new SpriteBatch();
		// headTexture = new Texture(Gdx.files.internal("data/hero.png"));
		// headTextureRegion = new TextureRegion(headTexture, 70, 124);
		temp = new TextureRegionDrawable();
		initHeroState(); // 角色状态
		monsterState(); // 怪物状态
		chat(); // 聊天
		skillState(); // 技能状态
		itemState(); // 物品栏
		miniMap(); // 小地图
		setting(); // 设置页面
		missionDetail(); // 任务描述
		teamState(); // 组队信息
		getGoods();// 物品获得信息栏 屏幕最上方
		systemNotice();// 系统公告
	}

	private void systemNotice() {
		Pixmap systemNoticePix = new Pixmap(
				(int) (GameScreen.ScreenWidth / 2.5),
				GameScreen.ScreenWidth / 35, Pixmap.Format.RGBA8888);
		systemNoticePix.setColor(Color.WHITE);
		systemNoticePix.fillRectangle(0, 0,
				(int) (GameScreen.ScreenWidth / 2.5),
				GameScreen.ScreenWidth / 35);
		systemNoticeTexRegion = new TextureRegion(new Texture(systemNoticePix));

	}

	private void getGoods() {
		Pixmap getGoodsPix = new Pixmap(GameScreen.ScreenWidth
				- miniMapTexRegion.getRegionWidth(),
				GameScreen.ScreenWidth / 50, Pixmap.Format.RGBA8888);
		getGoodsPix.setColor(Color.WHITE);
		getGoodsPix.fillRectangle(0, 0, GameScreen.ScreenWidth
				- miniMapTexRegion.getRegionWidth(),
				GameScreen.ScreenWidth / 50);
		getGoodsTexRegion = new TextureRegion(new Texture(getGoodsPix));

	}

	private void teamState() {
		Pixmap teamPix = new Pixmap(GameScreen.ScreenHeight / 4,
				(int) (GameScreen.ScreenHeight / 2.8), Pixmap.Format.RGBA8888);
		teamPix.setColor(Color.ORANGE);
		teamPix.fillRectangle(0, 0, GameScreen.ScreenHeight / 4,
				(int) (GameScreen.ScreenHeight / 2.8));
		teamTexRegion = new TextureRegion(new Texture(teamPix));

	}

	private void missionDetail() {
		Pixmap missionPix = new Pixmap(GameScreen.ScreenHeight / 4,
				(int) (GameScreen.ScreenHeight / 3.5), Pixmap.Format.RGBA8888);
		missionPix.setColor(Color.PINK);
		missionPix.fillRectangle(0, 0, GameScreen.ScreenHeight / 4,
				(int) (GameScreen.ScreenHeight / 3.5));
		missionDetailTexRegion = new TextureRegion(new Texture(missionPix));

	}

	private void setting() {
		Pixmap settingPix = new Pixmap(GameScreen.ScreenWidth / 15,
				GameScreen.ScreenWidth / 20, Pixmap.Format.RGBA8888);
		settingPix.setColor(Color.WHITE);
		settingPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 3,
				GameScreen.ScreenHeight / 8);
		settingTexRegion = new TextureRegion(new Texture(settingPix));
		temp.setRegion(settingTexRegion);
		btnSetting = new Button(temp);
		btnSetting.setPosition(
				GameScreen.ScreenWidth - settingTexRegion.getRegionWidth(),
				GameScreen.ScreenHeight - miniMapTexRegion.getRegionHeight()
						- settingTexRegion.getRegionHeight());
		btnSetting.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了设置");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

	}

	private void chat() {
		Pixmap chatPix = new Pixmap(GameScreen.ScreenWidth / 3,
				GameScreen.ScreenHeight / 8, Pixmap.Format.RGBA8888);
		chatPix.setColor(Color.WHITE);
		chatPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 3,
				GameScreen.ScreenHeight / 8);
		chatTexRegion = new TextureRegion(new Texture(chatPix));
		temp.setRegion(chatTexRegion);
		btnChat = new Button(temp);
		btnChat.setPosition(
				GameScreen.ScreenWidth / 2 - chatTexRegion.getRegionWidth() / 2,
				2);
		btnChat.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了聊天");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}

	private void initHeroState() {

		HeadPix = new Pixmap(GameScreen.ScreenWidth / 10,
				GameScreen.ScreenWidth / 10, Pixmap.Format.RGBA8888);
		HeadPix.setColor(Color.WHITE);
		HeadPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 10,
				GameScreen.ScreenWidth / 10);
		headTextureRegion = new TextureRegion(new Texture(HeadPix));
		temp.setRegion(headTextureRegion);
		btnPlayerHead = new Button(temp);
		btnPlayerHead.setPosition(GameScreen.ScreenWidth / 50,
				GameScreen.ScreenHeight - headTextureRegion.getRegionHeight()
						- GameScreen.ScreenWidth / 50);
		btnPlayerHead.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了头像");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		Pixmap hpPix = new Pixmap(GameScreen.ScreenWidth / 8,
				GameScreen.ScreenWidth / 40, Pixmap.Format.RGBA8888);
		hpPix.setColor(Color.RED);
		hpPix.fillRectangle(1, 1, GameScreen.ScreenWidth / 8,
				GameScreen.ScreenWidth / 40);
		hpTexRegion = new TextureRegion(new Texture(hpPix));

		Pixmap mpPix = new Pixmap(GameScreen.ScreenWidth / 8,
				GameScreen.ScreenWidth / 40, Pixmap.Format.RGBA8888);
		mpPix.setColor(Color.BLUE);
		mpPix.fillRectangle(1, 1, GameScreen.ScreenWidth / 8,
				GameScreen.ScreenWidth / 40);
		mpTexRegion = new TextureRegion(new Texture(mpPix));

	}

	private void monsterState() {
		Pixmap monsterHpPix = new Pixmap(GameScreen.ScreenWidth / 5,
				GameScreen.ScreenWidth / 35, Pixmap.Format.RGBA8888);
		monsterHpPix.setColor(Color.GREEN);
		monsterHpPix.fillRectangle(1, 1, GameScreen.ScreenWidth / 5,
				GameScreen.ScreenWidth / 35);
		Texture monsterHpTex = new Texture(monsterHpPix);
		monterHpTexRegion = new TextureRegion(monsterHpTex);
	}

	private void skillState() {
		Pixmap skillPix = new Pixmap(GameScreen.ScreenWidth / 15,
				GameScreen.ScreenWidth / 15, Pixmap.Format.RGBA8888);
		skillPix.setColor(Color.GREEN);
		skillPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 15,
				GameScreen.ScreenWidth / 15);
		Texture skillTex = new Texture(skillPix);

		skillTexRegion_1 = new TextureRegion(skillTex);
		skillTexRegion_2 = new TextureRegion(skillTex);
		skillTexRegion_3 = new TextureRegion(skillTex);
		skillTexRegion_4 = new TextureRegion(skillTex);
		skillTexRegion_spec = new TextureRegion(skillTex);

		temp.setRegion(skillTexRegion_1);
		btnSkill_1 = new Button(temp);
		btnSkill_1.setPosition(
				GameScreen.ScreenWidth - skillTexRegion_1.getRegionWidth() * 2
						- GameScreen.ScreenWidth * 2 / 50,
				skillTexRegion_1.getRegionHeight() + GameScreen.ScreenHeight
						* 2 / 50);
		btnSkill_1.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能1");
				ani = Hero.getHero().mSkillManager.getSkillByNumber(0).skillAni;
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_2);
		btnSkill_2 = new Button(temp);
		btnSkill_2
				.setPosition(
						GameScreen.ScreenWidth
								- skillTexRegion_2.getRegionWidth()
								- GameScreen.ScreenWidth / 50,
						(skillTexRegion_2.getRegionHeight() + GameScreen.ScreenHeight / 50) / 2);
		btnSkill_2.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能2");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_3);
		btnSkill_3 = new Button(temp);
		btnSkill_3.setPosition(
				GameScreen.ScreenWidth - skillTexRegion_3.getRegionWidth() * 2
						- GameScreen.ScreenWidth * 2 / 50,
				+GameScreen.ScreenHeight / 50);
		btnSkill_3.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能3");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_4);
		btnSkill_4 = new Button(temp);
		btnSkill_4
				.setPosition(
						GameScreen.ScreenWidth
								- skillTexRegion_4.getRegionWidth() * 3
								- GameScreen.ScreenWidth * 3 / 50,
						(skillTexRegion_4.getRegionHeight() + GameScreen.ScreenHeight / 50) / 2);
		btnSkill_4.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能4");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_spec);
		btnSkill_spec = new Button(temp);
		btnSkill_spec.setPosition(
				GameScreen.ScreenWidth / 2 - chatTexRegion.getRegionWidth() / 2
						- skillTexRegion_spec.getRegionWidth() * 2,
				GameScreen.ScreenWidth / 50);
		btnSkill_spec.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了特殊技能");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}

	private void itemState() {
		Pixmap itemPix = new Pixmap(GameScreen.ScreenWidth / 15,
				GameScreen.ScreenWidth / 20, Pixmap.Format.RGBA8888);
		itemPix.setColor(Color.WHITE);
		itemPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 15,
				GameScreen.ScreenWidth / 20);
		itemTexRegion = new TextureRegion(new Texture(itemPix));

		temp.setRegion(itemTexRegion);
		btnItem = new Button(temp);
		btnItem.setPosition(
				GameScreen.ScreenWidth * 49 / 50
						- itemTexRegion.getRegionWidth(),
				skillTexRegion_2.getRegionHeight() * 2
						+ GameScreen.ScreenHeight * 2 / 30);
		btnItem.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了物品");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}

	private void miniMap() {
		Pixmap miniMapPix = new Pixmap(GameScreen.ScreenWidth / 6,
				GameScreen.ScreenWidth / 6, Pixmap.Format.RGBA8888);
		miniMapPix.setColor(Color.WHITE);
		miniMapPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 6,
				GameScreen.ScreenWidth / 6);
		miniMapTexRegion = new TextureRegion(new Texture(miniMapPix));

		temp.setRegion(miniMapTexRegion);
		btnMiniMap = new Button(temp);
		btnMiniMap.setPosition(
				GameScreen.ScreenWidth - miniMapTexRegion.getRegionWidth(),
				GameScreen.ScreenHeight - miniMapTexRegion.getRegionHeight());
		btnMiniMap.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了小地图");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}

	public void draw(SpriteBatch batch) {
		// //////////////////////HeroState////////////////////
		// batch.draw(headTextureRegion, GameScreen.ScreenWidth / 30,
		// GameScreen.ScreenHeight * 8 / 10);
		batch.draw(
				hpTexRegion,
				GameScreen.ScreenWidth / 50
						+ headTextureRegion.getRegionWidth() + 10,
				GameScreen.ScreenHeight - GameScreen.ScreenWidth / 50
						- headTextureRegion.getRegionHeight() / 2 + 5);
		batch.draw(
				mpTexRegion,
				GameScreen.ScreenWidth / 50
						+ headTextureRegion.getRegionWidth() + 10,
				GameScreen.ScreenHeight - GameScreen.ScreenWidth / 50
						- headTextureRegion.getRegionHeight() / 2
						- mpTexRegion.getRegionHeight() - 5);

		// //////////////////////monsterState////////////////////
		batch.draw(monterHpTexRegion, GameScreen.ScreenWidth / 2
				- monterHpTexRegion.getRegionWidth() / 2,
				GameScreen.ScreenHeight * 9 / 10);

		// //////////////////////skillState////////////////////
		// batch.draw(skillTextureRegion_1, GameScreen.ScreenWidth
		// - skillTextureRegion_1.getRegionWidth() * 2
		// - GameScreen.ScreenWidth * 2 / 40,
		// skillTextureRegion_1.getRegionHeight()
		// + GameScreen.ScreenHeight * 2 / 40);
		// batch.draw(skillTextureRegion_2, GameScreen.ScreenWidth
		// - skillTextureRegion_2.getRegionWidth()
		// - GameScreen.ScreenWidth / 40,
		// skillTextureRegion_2.getRegionHeight()
		// + GameScreen.ScreenHeight * 2 / 40);
		// batch.draw(skillTextureRegion_3, GameScreen.ScreenWidth
		// - skillTextureRegion_3.getRegionWidth() * 2
		// - GameScreen.ScreenWidth * 2 / 40,
		// +GameScreen.ScreenHeight / 40);
		// batch.draw(skillTextureRegion_4, GameScreen.ScreenWidth
		// - skillTextureRegion_4.getRegionWidth()
		// - GameScreen.ScreenWidth / 40, +GameScreen.ScreenHeight / 40);
		// batch.draw(
		// skillTextureRegion_spec,
		// GameScreen.ScreenWidth / 40,
		// GameScreen.ScreenHeight / 2
		// - skillTextureRegion_spec.getRegionHeight() / 2);

		// //////////////////////itemState////////////////////
		// batch.draw(itemTexRegion, GameScreen.ScreenWidth * 29 / 30
		// - itemTexRegion.getRegionWidth(),
		// skillTextureRegion_2.getRegionHeight() * 2
		// + GameScreen.ScreenHeight * 4 / 30);

		// //////////////////////miniMap////////////////////
		// batch.draw(miniMapTexRegion,
		// GameScreen.ScreenWidth - miniMapTexRegion.getRegionWidth(),
		// GameScreen.ScreenHeight - miniMapTexRegion.getRegionHeight());

		// //////////////////////missionDetail////////////////////
		batch.draw(missionDetailTexRegion,
				GameScreen.ScreenWidth - settingTexRegion.getRegionWidth()
						- missionDetailTexRegion.getRegionWidth() - 20,
				GameScreen.ScreenHeight - miniMapTexRegion.getRegionHeight()
						* 2 - 25);

		// ////////////////////teamState////////////////////
		batch.draw(
				teamTexRegion,
				GameScreen.ScreenWidth / 40,
				GameScreen.ScreenHeight - headTextureRegion.getRegionHeight()
						- GameScreen.ScreenWidth / 50
						- teamTexRegion.getRegionHeight() - 10);

		// ////////////////////getGoods////////////////////
		batch.draw(getGoodsTexRegion, 0, GameScreen.ScreenHeight
				- GameScreen.ScreenWidth / 50);

		// ////////////////////systemNotice////////////////////
		batch.draw(systemNoticeTexRegion, GameScreen.ScreenWidth / 2
				- systemNoticeTexRegion.getRegionWidth() / 2,
				GameScreen.ScreenHeight * 9 / 10 - 30);

		if (flag) {
			try {
				stateTime += Gdx.graphics.getDeltaTime();
				currentFrame = ani.getKeyFrame(stateTime);
				// batch.draw(
				// currentFrame,
				// GameScreen.ScreenWidth / 2
				// - currentFrame.getRegionWidth() / 2,
				// GameScreen.ScreenHeight / 2
				// - currentFrame.getRegionHeight() / 2, 1000, 1000);
				batch.draw(currentFrame, 0, 0);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addToStage(Stage stage) {
		stage.addActor(btnPlayerHead);
		stage.addActor(btnSkill_1);
		stage.addActor(btnSkill_2);
		stage.addActor(btnSkill_3);
		stage.addActor(btnSkill_4);
		stage.addActor(btnSkill_spec);
		stage.addActor(btnItem);
		stage.addActor(btnMiniMap);
		stage.addActor(btnChat);
		stage.addActor(btnSetting);
	}
}
