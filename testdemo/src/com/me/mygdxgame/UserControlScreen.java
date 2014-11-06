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
import com.blackvine.constant.Constants;

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

	public int skill_1_SkillNo = 2, skill_2_SkillNo = 4, skill_3_SkillNo = 5,
			skill_4_SkillNo = 6;

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
				(int) (Constants.SCREEN_WIDTH / 2.5),
				Constants.SCREEN_WIDTH / 35, Pixmap.Format.RGBA8888);
		systemNoticePix.setColor(Color.WHITE);
		systemNoticePix.fillRectangle(0, 0,
				(int) (Constants.SCREEN_WIDTH / 2.5),
				Constants.SCREEN_WIDTH / 35);
		systemNoticeTexRegion = new TextureRegion(new Texture(systemNoticePix));

	}

	private void getGoods() {
		Pixmap getGoodsPix = new Pixmap(Constants.SCREEN_WIDTH
				- miniMapTexRegion.getRegionWidth(),
				Constants.SCREEN_WIDTH / 50, Pixmap.Format.RGBA8888);
		getGoodsPix.setColor(Color.WHITE);
		getGoodsPix.fillRectangle(0, 0, Constants.SCREEN_WIDTH
				- miniMapTexRegion.getRegionWidth(),
				Constants.SCREEN_WIDTH / 50);
		getGoodsTexRegion = new TextureRegion(new Texture(getGoodsPix));

	}

	private void teamState() {
		Pixmap teamPix = new Pixmap(Constants.SCREEN_HEIGHT / 4,
				(int) (Constants.SCREEN_HEIGHT / 2.8), Pixmap.Format.RGBA8888);
		teamPix.setColor(Color.ORANGE);
		teamPix.fillRectangle(0, 0, Constants.SCREEN_HEIGHT / 4,
				(int) (Constants.SCREEN_HEIGHT / 2.8));
		teamTexRegion = new TextureRegion(new Texture(teamPix));

	}

	private void missionDetail() {
		Pixmap missionPix = new Pixmap(Constants.SCREEN_HEIGHT / 4,
				(int) (Constants.SCREEN_HEIGHT / 3.5), Pixmap.Format.RGBA8888);
		missionPix.setColor(Color.PINK);
		missionPix.fillRectangle(0, 0, Constants.SCREEN_HEIGHT / 4,
				(int) (Constants.SCREEN_HEIGHT / 3.5));
		missionDetailTexRegion = new TextureRegion(new Texture(missionPix));

	}

	private void setting() {
		Pixmap settingPix = new Pixmap(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 20, Pixmap.Format.RGBA8888);
		settingPix.setColor(Color.WHITE);
		settingPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 3,
				Constants.SCREEN_HEIGHT / 8);
		settingTexRegion = new TextureRegion(new Texture(settingPix));
		temp.setRegion(settingTexRegion);
		btnSetting = new Button(temp);
		btnSetting.setPosition(
				Constants.SCREEN_WIDTH - settingTexRegion.getRegionWidth(),
				Constants.SCREEN_HEIGHT - miniMapTexRegion.getRegionHeight()
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
		Pixmap chatPix = new Pixmap(Constants.SCREEN_WIDTH / 3,
				Constants.SCREEN_HEIGHT / 8, Pixmap.Format.RGBA8888);
		chatPix.setColor(Color.WHITE);
		chatPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 3,
				Constants.SCREEN_HEIGHT / 8);
		chatTexRegion = new TextureRegion(new Texture(chatPix));
		temp.setRegion(chatTexRegion);
		btnChat = new Button(temp);
		btnChat.setPosition(
				Constants.SCREEN_WIDTH / 2 - chatTexRegion.getRegionWidth() / 2,
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

		HeadPix = new Pixmap(Constants.SCREEN_WIDTH / 10,
				Constants.SCREEN_WIDTH / 10, Pixmap.Format.RGBA8888);
		HeadPix.setColor(Color.WHITE);
		HeadPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 10,
				Constants.SCREEN_WIDTH / 10);
		headTextureRegion = new TextureRegion(new Texture(HeadPix));
		temp.setRegion(headTextureRegion);
		btnPlayerHead = new Button(temp);
		btnPlayerHead.setPosition(Constants.SCREEN_WIDTH / 50,
				Constants.SCREEN_HEIGHT - headTextureRegion.getRegionHeight()
						- Constants.SCREEN_WIDTH / 50);
		btnPlayerHead.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了头像");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		Pixmap hpPix = new Pixmap(Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 40, Pixmap.Format.RGBA8888);
		hpPix.setColor(Color.RED);
		hpPix.fillRectangle(1, 1, Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 40);
		hpTexRegion = new TextureRegion(new Texture(hpPix));

		Pixmap mpPix = new Pixmap(Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 40, Pixmap.Format.RGBA8888);
		mpPix.setColor(Color.BLUE);
		mpPix.fillRectangle(1, 1, Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 40);
		mpTexRegion = new TextureRegion(new Texture(mpPix));

	}

	private void monsterState() {
		Pixmap monsterHpPix = new Pixmap(Constants.SCREEN_WIDTH / 5,
				Constants.SCREEN_WIDTH / 35, Pixmap.Format.RGBA8888);
		monsterHpPix.setColor(Color.GREEN);
		monsterHpPix.fillRectangle(1, 1, Constants.SCREEN_WIDTH / 5,
				Constants.SCREEN_WIDTH / 35);
		Texture monsterHpTex = new Texture(monsterHpPix);
		monterHpTexRegion = new TextureRegion(monsterHpTex);
	}

	private void skillState() {
		Pixmap skillPix = new Pixmap(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 15, Pixmap.Format.RGBA8888);
		skillPix.setColor(Color.GREEN);
		skillPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 15);
		Texture skillTex = new Texture(skillPix);

		skillTexRegion_1 = new TextureRegion(skillTex);
		skillTexRegion_2 = new TextureRegion(skillTex);
		skillTexRegion_3 = new TextureRegion(skillTex);
		skillTexRegion_4 = new TextureRegion(skillTex);
		skillTexRegion_spec = new TextureRegion(skillTex);

		temp.setRegion(skillTexRegion_1);
		btnSkill_1 = new Button(temp);
		btnSkill_1.setPosition(
				Constants.SCREEN_WIDTH - skillTexRegion_1.getRegionWidth() * 2
						- Constants.SCREEN_WIDTH * 2 / 50,
				skillTexRegion_1.getRegionHeight() + Constants.SCREEN_HEIGHT
						* 2 / 50);
		btnSkill_1.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能1");
				ani = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_1_SkillNo()).skillAni;
				stateTime = 0;
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_2);
		btnSkill_2 = new Button(temp);
		btnSkill_2
				.setPosition(
						Constants.SCREEN_WIDTH
								- skillTexRegion_2.getRegionWidth()
								- Constants.SCREEN_WIDTH / 50,
						(skillTexRegion_2.getRegionHeight() + Constants.SCREEN_HEIGHT / 50) / 2);
		btnSkill_2.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能2");
				ani = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_2_SkillNo()).skillAni;
				stateTime = 0;
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_3);
		btnSkill_3 = new Button(temp);
		btnSkill_3.setPosition(
				Constants.SCREEN_WIDTH - skillTexRegion_3.getRegionWidth() * 2
						- Constants.SCREEN_WIDTH * 2 / 50,
				+Constants.SCREEN_HEIGHT / 50);
		btnSkill_3.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能3");
				ani = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_3_SkillNo()).skillAni;
				stateTime = 0;
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_4);
		btnSkill_4 = new Button(temp);
		btnSkill_4
				.setPosition(
						Constants.SCREEN_WIDTH
								- skillTexRegion_4.getRegionWidth() * 3
								- Constants.SCREEN_WIDTH * 3 / 50,
						(skillTexRegion_4.getRegionHeight() + Constants.SCREEN_HEIGHT / 50) / 2);
		btnSkill_4.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能4");
				ani = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_4_SkillNo()).skillAni;
				stateTime = 0;
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTexRegion_spec);
		btnSkill_spec = new Button(temp);
		btnSkill_spec.setPosition(
				Constants.SCREEN_WIDTH / 2 - chatTexRegion.getRegionWidth() / 2
						- skillTexRegion_spec.getRegionWidth() * 2,
				Constants.SCREEN_WIDTH / 50);
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
		Pixmap itemPix = new Pixmap(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 20, Pixmap.Format.RGBA8888);
		itemPix.setColor(Color.WHITE);
		itemPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 20);
		itemTexRegion = new TextureRegion(new Texture(itemPix));

		temp.setRegion(itemTexRegion);
		btnItem = new Button(temp);
		btnItem.setPosition(
				Constants.SCREEN_WIDTH * 49 / 50
						- itemTexRegion.getRegionWidth(),
				skillTexRegion_2.getRegionHeight() * 2
						+ Constants.SCREEN_HEIGHT * 2 / 30);
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
		Pixmap miniMapPix = new Pixmap(Constants.SCREEN_WIDTH / 6,
				Constants.SCREEN_WIDTH / 6, Pixmap.Format.RGBA8888);
		miniMapPix.setColor(Color.WHITE);
		miniMapPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 6,
				Constants.SCREEN_WIDTH / 6);
		miniMapTexRegion = new TextureRegion(new Texture(miniMapPix));

		temp.setRegion(miniMapTexRegion);
		btnMiniMap = new Button(temp);
		btnMiniMap.setPosition(
				Constants.SCREEN_WIDTH - miniMapTexRegion.getRegionWidth(),
				Constants.SCREEN_HEIGHT - miniMapTexRegion.getRegionHeight());
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
		// batch.draw(headTextureRegion, Constants.SCREEN_WIDTH / 30,
		// Constants.SCREEN_HEIGHT * 8 / 10);
		batch.draw(
				hpTexRegion,
				Constants.SCREEN_WIDTH / 50
						+ headTextureRegion.getRegionWidth() + 10,
				Constants.SCREEN_HEIGHT - Constants.SCREEN_WIDTH / 50
						- headTextureRegion.getRegionHeight() / 2 + 5);
		batch.draw(
				mpTexRegion,
				Constants.SCREEN_WIDTH / 50
						+ headTextureRegion.getRegionWidth() + 10,
				Constants.SCREEN_HEIGHT - Constants.SCREEN_WIDTH / 50
						- headTextureRegion.getRegionHeight() / 2
						- mpTexRegion.getRegionHeight() - 5);

		// //////////////////////monsterState////////////////////
		batch.draw(monterHpTexRegion, Constants.SCREEN_WIDTH / 2
				- monterHpTexRegion.getRegionWidth() / 2,
				Constants.SCREEN_HEIGHT * 9 / 10);

		// //////////////////////skillState////////////////////
		// batch.draw(skillTextureRegion_1, Constants.SCREEN_WIDTH
		// - skillTextureRegion_1.getRegionWidth() * 2
		// - Constants.SCREEN_WIDTH * 2 / 40,
		// skillTextureRegion_1.getRegionHeight()
		// + Constants.SCREEN_HEIGHT * 2 / 40);
		// batch.draw(skillTextureRegion_2, Constants.SCREEN_WIDTH
		// - skillTextureRegion_2.getRegionWidth()
		// - Constants.SCREEN_WIDTH / 40,
		// skillTextureRegion_2.getRegionHeight()
		// + Constants.SCREEN_HEIGHT * 2 / 40);
		// batch.draw(skillTextureRegion_3, Constants.SCREEN_WIDTH
		// - skillTextureRegion_3.getRegionWidth() * 2
		// - Constants.SCREEN_WIDTH * 2 / 40,
		// +Constants.SCREEN_HEIGHT / 40);
		// batch.draw(skillTextureRegion_4, Constants.SCREEN_WIDTH
		// - skillTextureRegion_4.getRegionWidth()
		// - Constants.SCREEN_WIDTH / 40, +Constants.SCREEN_HEIGHT / 40);
		// batch.draw(
		// skillTextureRegion_spec,
		// Constants.SCREEN_WIDTH / 40,
		// Constants.SCREEN_HEIGHT / 2
		// - skillTextureRegion_spec.getRegionHeight() / 2);

		// //////////////////////itemState////////////////////
		// batch.draw(itemTexRegion, Constants.SCREEN_WIDTH * 29 / 30
		// - itemTexRegion.getRegionWidth(),
		// skillTextureRegion_2.getRegionHeight() * 2
		// + Constants.SCREEN_HEIGHT * 4 / 30);

		// //////////////////////miniMap////////////////////
		// batch.draw(miniMapTexRegion,
		// Constants.SCREEN_WIDTH - miniMapTexRegion.getRegionWidth(),
		// Constants.SCREEN_HEIGHT - miniMapTexRegion.getRegionHeight());

		// //////////////////////missionDetail////////////////////
		batch.draw(missionDetailTexRegion,
				Constants.SCREEN_WIDTH - settingTexRegion.getRegionWidth()
						- missionDetailTexRegion.getRegionWidth() - 20,
				Constants.SCREEN_HEIGHT - miniMapTexRegion.getRegionHeight()
						* 2 - 25);

		// ////////////////////teamState////////////////////
		batch.draw(
				teamTexRegion,
				Constants.SCREEN_WIDTH / 40,
				Constants.SCREEN_HEIGHT - headTextureRegion.getRegionHeight()
						- Constants.SCREEN_WIDTH / 50
						- teamTexRegion.getRegionHeight() - 10);

		// ////////////////////getGoods////////////////////
		batch.draw(getGoodsTexRegion, 0, Constants.SCREEN_HEIGHT
				- Constants.SCREEN_WIDTH / 50);

		// ////////////////////systemNotice////////////////////
		batch.draw(systemNoticeTexRegion, Constants.SCREEN_WIDTH / 2
				- systemNoticeTexRegion.getRegionWidth() / 2,
				Constants.SCREEN_HEIGHT * 9 / 10 - 30);

		if (flag) {

			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = ani.getKeyFrame(stateTime);
			batch.draw(
					currentFrame,
					Constants.SCREEN_WIDTH / 2 - currentFrame.getRegionWidth()
							/ 2,
					Constants.SCREEN_HEIGHT / 2
							- currentFrame.getRegionHeight() / 2);

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

	public int getSkill_1_SkillNo() {
		return skill_1_SkillNo;
	}

	public void setSkill_1_SkillNo(int skill_1_SkillNo) {
		this.skill_1_SkillNo = skill_1_SkillNo;
	}

	public int getSkill_2_SkillNo() {
		return skill_2_SkillNo;
	}

	public void setSkill_2_SkillNo(int skill_2_SkillNo) {
		this.skill_2_SkillNo = skill_2_SkillNo;
	}

	public int getSkill_3_SkillNo() {
		return skill_3_SkillNo;
	}

	public void setSkill_3_SkillNo(int skill_3_SkillNo) {
		this.skill_3_SkillNo = skill_3_SkillNo;
	}

	public int getSkill_4_SkillNo() {
		return skill_4_SkillNo;
	}

	public void setSkill_4_SkillNo(int skill_4_SkillNo) {
		this.skill_4_SkillNo = skill_4_SkillNo;
	}
}
