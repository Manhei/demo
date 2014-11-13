package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blackvine.actor.Hero;
import com.blackvine.actor.Skill;
import com.blackvine.animation.MyAnimation;
import com.blackvine.constant.Constants;
import com.blackvine.manager.SingleAssetManager;

/**
 * 用户操作层 状态显示 技能按钮 小地图等界面
 * 
 * @author songjunfeng
 * 
 */
public class UserControlScreen {

	private static UserControlScreen Instance = null;
	// Texture headTexture;
	Sprite headSprite, itemSprite, miniMapSprite, chatSprite, settingSprite,
			missionDetailSprite, teamSprite, getGoodsSprite,
			systemNoticeSprite;
	Pixmap HeadPix;
	SpriteBatch batch;
	ImageButton btnPlayerHead, btnSkill_bounds_1, btnSkill_bounds_2,
			btnSkill_bounds_3, btnSkill_bounds_4, btnSkill_bounds_spec,
			btnItem, btnMiniMap, btnChat, btnSetting;
	Sprite hpBorderSprite, hpSprite, monterHpSprite;
	Sprite mpBorderSprite, mpSprite;
	Sprite skillSprite_bounds;
	TextureRegionDrawable temp;

	private MyAnimation ani;
	private Skill mSkill;
	private SingleAssetManager am;
	private float drawX = 0, drawY = 0;
	private float stateTime = 0;
	private TextureRegion currentFrame;
	public static boolean flag = false;
	public int skill_1_SkillNo = 1, skill_2_SkillNo = 4, skill_3_SkillNo = 5,
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
		am = SingleAssetManager.getSingleAssetManager();
		initHeroState(); // 角色状态
		monsterState(); // 怪物状态
		chat(); // 聊天
		skillState(); // 技能状态
		itemState(); // 物品栏
		miniMap(); // 小地图
		// setting(); // 设置页面
		missionDetail(); // 任务描述
		teamState(); // 组队信息
		getGoods();// 物品获得信息栏 屏幕最上方
		systemNotice();// 系统公告

	}

	private void systemNotice() {
		// Pixmap systemNoticePix = new Pixmap(
		// (int) (Constants.SCREEN_WIDTH / 2.5),
		// Constants.SCREEN_WIDTH / 35, Pixmap.Format.RGBA8888);
		// systemNoticePix.setColor(Color.WHITE);
		// systemNoticePix.fillRectangle(0, 0,
		// (int) (Constants.SCREEN_WIDTH / 2.5),
		// Constants.SCREEN_WIDTH / 35);
		systemNoticeSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "shijiegonggao.png", Texture.class));
		systemNoticeSprite.setRegionWidth((int) (Constants.SCREEN_WIDTH / 2.5));
		systemNoticeSprite.setRegionHeight(Constants.SCREEN_WIDTH / 25);
	}

	private void getGoods() {
		Pixmap getGoodsPix = new Pixmap(Constants.SCREEN_WIDTH
				- miniMapSprite.getRegionWidth(), Constants.SCREEN_WIDTH / 50,
				Pixmap.Format.RGBA8888);
		getGoodsPix.setColor(Color.WHITE);
		getGoodsPix.fillRectangle(0, 0,
				Constants.SCREEN_WIDTH - miniMapSprite.getRegionWidth(),
				Constants.SCREEN_WIDTH / 50);
		getGoodsSprite = new Sprite(new Texture(getGoodsPix));

	}

	private void teamState() {
		Pixmap teamPix = new Pixmap(Constants.SCREEN_HEIGHT / 4,
				(int) (Constants.SCREEN_HEIGHT / 2.8), Pixmap.Format.RGBA8888);
		teamPix.setColor(Color.ORANGE);
		teamPix.fillRectangle(0, 0, Constants.SCREEN_HEIGHT / 4,
				(int) (Constants.SCREEN_HEIGHT / 2.8));
		teamSprite = new Sprite(new Texture(teamPix));

	}

	private void missionDetail() {
		// Pixmap missionPix = new Pixmap(Constants.SCREEN_HEIGHT / 4,
		// (int) (Constants.SCREEN_HEIGHT / 3.5), Pixmap.Format.RGBA8888);
		// missionPix.setColor(Color.PINK);
		// missionPix.fillRectangle(0, 0, Constants.SCREEN_HEIGHT / 4,
		// (int) (Constants.SCREEN_HEIGHT / 3.5));
		missionDetailSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "renwu.png", Texture.class));
		// missionDetailTexRegion.setRegionWidth(Constants.SCREEN_HEIGHT / 4);
		// missionDetailTexRegion
		// .setRegionHeight((int) (Constants.SCREEN_HEIGHT / 3.5));
		missionDetailSprite = new Sprite(missionDetailSprite);
		missionDetailSprite.setSize((float) (Constants.SCREEN_HEIGHT / 3.2),
				Constants.SCREEN_HEIGHT / 4);
	}

	private void setting() {
		Pixmap settingPix = new Pixmap(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 20, Pixmap.Format.RGBA8888);
		settingPix.setColor(Color.WHITE);
		settingPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 3,
				Constants.SCREEN_HEIGHT / 8);
		settingSprite = new Sprite(new Texture(settingPix));
		temp.setRegion(settingSprite);
		btnSetting = new ImageButton(temp);
		btnSetting.setPosition(
				Constants.SCREEN_WIDTH - settingSprite.getRegionWidth(),
				Constants.SCREEN_HEIGHT - miniMapSprite.getRegionHeight()
						- settingSprite.getRegionHeight());
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
		// Pixmap chatPix = new Pixmap(Constants.SCREEN_WIDTH / 3,
		// Constants.SCREEN_HEIGHT / 8, Pixmap.Format.RGBA8888);
		// chatPix.setColor(Color.WHITE);
		// chatPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 3,
		// Constants.SCREEN_HEIGHT / 8);
		chatSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "liaotian.png", Texture.class));
		temp = new TextureRegionDrawable(chatSprite);
		btnChat = new ImageButton(temp);
		btnChat.setSize((float) (Constants.SCREEN_WIDTH / 2.6),
				Constants.SCREEN_HEIGHT / 6);
		btnChat.setPosition(
				Constants.SCREEN_WIDTH / 2 - btnChat.getWidth() / 2, 2);
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

		// HeadPix = new Pixmap(Constants.SCREEN_WIDTH / 10,
		// Constants.SCREEN_WIDTH / 10, Pixmap.Format.RGBA8888);
		// HeadPix.setColor(Color.WHITE);
		// HeadPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 10,
		// Constants.SCREEN_WIDTH / 10);
		// headTextureRegion = new TextureRegion(new Texture(HeadPix));
		headSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "touxiang.png", Texture.class));
		temp = new TextureRegionDrawable(headSprite);
		btnPlayerHead = new ImageButton(temp);
		btnPlayerHead.setSize(Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 8);
		btnPlayerHead.setPosition(Constants.SCREEN_WIDTH / 100,
				Constants.SCREEN_HEIGHT - btnPlayerHead.getHeight()
						- Constants.SCREEN_WIDTH / 80);
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
		hpSprite = new Sprite(new Texture(hpPix));

		Pixmap mpPix = new Pixmap(Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 40, Pixmap.Format.RGBA8888);
		mpPix.setColor(Color.BLUE);
		mpPix.fillRectangle(1, 1, Constants.SCREEN_WIDTH / 8,
				Constants.SCREEN_WIDTH / 40);
		mpSprite = new Sprite(new Texture(mpPix));

	}

	private void monsterState() {
		// Pixmap monsterHpPix = new Pixmap(Constants.SCREEN_WIDTH / 5,
		// Constants.SCREEN_WIDTH / 35, Pixmap.Format.RGBA8888);
		// monsterHpPix.setColor(Color.GREEN);
		// monsterHpPix.fillRectangle(1, 1, Constants.SCREEN_WIDTH / 5,
		// Constants.SCREEN_WIDTH / 35);
		// Texture monsterHpTex = new Texture(monsterHpPix);
		monterHpSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "guaiwu.png", Texture.class));
		monterHpSprite.setSize(Constants.SCREEN_WIDTH / 4,
				Constants.SCREEN_HEIGHT / 8);
	}

	private void skillState() {
		// Pixmap skillPix = new Pixmap(Constants.SCREEN_WIDTH / 15,
		// Constants.SCREEN_WIDTH / 15, Pixmap.Format.RGBA8888);
		// skillPix.setColor(Color.GREEN);
		// skillPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 15,
		// Constants.SCREEN_WIDTH / 15);
		// Texture skillTex = new Texture(skillPix);

		skillSprite_bounds = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "jinengkuang.png", Texture.class));
		// skillTexRegion_2 = new TextureRegion(skillTex);
		// skillTexRegion_3 = new TextureRegion(skillTex);
		// skillTexRegion_4 = new TextureRegion(skillTex);
		// skillTexRegion_spec = new TextureRegion(skillTex);

		temp = new TextureRegionDrawable(skillSprite_bounds);
		btnSkill_bounds_1 = new ImageButton(temp);
		btnSkill_bounds_1.setSize(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 15);
		btnSkill_bounds_1.setPosition(Constants.SCREEN_WIDTH
				- btnSkill_bounds_1.getWidth() * 2 - Constants.SCREEN_WIDTH * 2
				/ 50, btnSkill_bounds_1.getHeight() + Constants.SCREEN_HEIGHT
				* 2 / 50);
		btnSkill_bounds_1.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能1");
				mSkill = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_1_SkillNo());
				ani = mSkill.skillAni;
				stateTime = 0;
				currentFrame = ani.getKeyFrame(stateTime);
				drawX = getSkill_XinWolrd();
				drawY = getSkill_YinWolrd();
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp = new TextureRegionDrawable(skillSprite_bounds);
		btnSkill_bounds_2 = new ImageButton(temp);
		btnSkill_bounds_2.setSize(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 15);
		btnSkill_bounds_2
				.setPosition(
						Constants.SCREEN_WIDTH - btnSkill_bounds_2.getWidth()
								- Constants.SCREEN_WIDTH / 50,
						(btnSkill_bounds_2.getHeight() + Constants.SCREEN_HEIGHT / 50) / 2);
		btnSkill_bounds_2.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能2");
				mSkill = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_2_SkillNo());
				ani = mSkill.skillAni;
				stateTime = 0;
				currentFrame = ani.getKeyFrame(stateTime);
				drawX = getSkill_XinWolrd();
				drawY = getSkill_YinWolrd();
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp = new TextureRegionDrawable(skillSprite_bounds);
		btnSkill_bounds_3 = new ImageButton(temp);
		btnSkill_bounds_3.setSize(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 15);
		btnSkill_bounds_3.setPosition(Constants.SCREEN_WIDTH
				- btnSkill_bounds_3.getWidth() * 2 - Constants.SCREEN_WIDTH * 2
				/ 50, +Constants.SCREEN_HEIGHT / 50);
		btnSkill_bounds_3.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能3");
				mSkill = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_3_SkillNo());
				ani = mSkill.skillAni;
				stateTime = 0;
				currentFrame = ani.getKeyFrame(stateTime);
				drawX = getSkill_XinWolrd();
				drawY = getSkill_YinWolrd();
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp = new TextureRegionDrawable(skillSprite_bounds);
		btnSkill_bounds_4 = new ImageButton(temp);
		btnSkill_bounds_4.setSize(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 15);
		btnSkill_bounds_4
				.setPosition(
						Constants.SCREEN_WIDTH - btnSkill_bounds_4.getWidth()
								* 3 - Constants.SCREEN_WIDTH * 3 / 50,
						(btnSkill_bounds_4.getHeight() + Constants.SCREEN_HEIGHT / 50) / 2);
		btnSkill_bounds_4.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能4");
				mSkill = Hero.getHero().mSkillManager
						.getSkillByNumber(getSkill_4_SkillNo());
				ani = mSkill.skillAni;
				stateTime = 0;
				currentFrame = ani.getKeyFrame(stateTime);
				drawX = getSkill_XinWolrd();
				drawY = getSkill_YinWolrd();
				flag = true;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp = new TextureRegionDrawable(skillSprite_bounds);
		btnSkill_bounds_spec = new ImageButton(temp);
		btnSkill_bounds_spec.setSize(Constants.SCREEN_WIDTH / 17,
				Constants.SCREEN_WIDTH / 17);
		btnSkill_bounds_spec.setPosition(
				Constants.SCREEN_WIDTH / 2 - btnChat.getWidth() / 2
						- btnSkill_bounds_spec.getWidth() * 2,
				Constants.SCREEN_WIDTH / 50);
		btnSkill_bounds_spec.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了特殊技能");
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}

	private void itemState() {
		// Pixmap itemPix = new Pixmap(Constants.SCREEN_WIDTH / 15,
		// Constants.SCREEN_WIDTH / 20, Pixmap.Format.RGBA8888);
		// itemPix.setColor(Color.WHITE);
		// itemPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 15,
		// Constants.SCREEN_WIDTH / 20);
		itemSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "wupinkuang.png", Texture.class));
		// itemSprite.setSize(Constants.SCREEN_WIDTH / 15,
		// Constants.SCREEN_WIDTH / 27);
		temp = new TextureRegionDrawable(itemSprite);
		btnItem = new ImageButton(temp);
		btnItem.setSize(Constants.SCREEN_WIDTH / 15,
				Constants.SCREEN_WIDTH / 27);
		btnItem.setPosition(
				Constants.SCREEN_WIDTH * 49 / 50 - btnItem.getWidth(),
				btnSkill_bounds_2.getHeight() * 2 + Constants.SCREEN_HEIGHT * 2
						/ 30);
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
		// Pixmap miniMapPix = new Pixmap(Constants.SCREEN_WIDTH / 6,
		// Constants.SCREEN_WIDTH / 6, Pixmap.Format.RGBA8888);
		// miniMapPix.setColor(Color.WHITE);
		// miniMapPix.drawRectangle(0, 0, Constants.SCREEN_WIDTH / 6,
		// Constants.SCREEN_WIDTH / 6);
		miniMapSprite = new Sprite(am.get(Constants.SCREEN_TEXTURE_DIR
				+ "map.png", Texture.class));

		temp = new TextureRegionDrawable(miniMapSprite);
		btnMiniMap = new ImageButton(temp);
		btnMiniMap.setSize(Constants.SCREEN_WIDTH / 6,
				Constants.SCREEN_WIDTH / 6);
		btnMiniMap.setPosition(Constants.SCREEN_WIDTH - btnMiniMap.getWidth(),
				Constants.SCREEN_HEIGHT - btnMiniMap.getHeight());
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
		if (flag) {

			stateTime += Gdx.graphics.getDeltaTime();
			if (ani != null) {
				currentFrame = ani.getKeyFrame(stateTime);
				batch.draw(currentFrame, drawX, drawY);
			}
		}
		// //////////////////////HeroState////////////////////
		// batch.draw(headTextureRegion, Constants.SCREEN_WIDTH / 30,
		// Constants.SCREEN_HEIGHT * 8 / 10);
		batch.draw(hpSprite,
				Constants.SCREEN_WIDTH / 100 + btnPlayerHead.getWidth() - 2,
				Constants.SCREEN_HEIGHT - Constants.SCREEN_WIDTH / 80
						- btnPlayerHead.getWidth() / 2 + 5);
		batch.draw(
				mpSprite,
				Constants.SCREEN_WIDTH / 100 + btnPlayerHead.getWidth() - 2,
				Constants.SCREEN_HEIGHT - Constants.SCREEN_WIDTH / 80
						- btnPlayerHead.getHeight() / 2
						- mpSprite.getRegionHeight() - 5);

		// //////////////////////monsterState////////////////////
		batch.draw(monterHpSprite,
				Constants.SCREEN_WIDTH / 2 - monterHpSprite.getWidth() / 2,
				Constants.SCREEN_HEIGHT - monterHpSprite.getHeight() - 5,
				monterHpSprite.getWidth(), monterHpSprite.getHeight());

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
		// batch.draw(missionDetailTexRegion, Constants.SCREEN_WIDTH
		// - missionDetailTexRegion.getRegionWidth() - 10,
		// Constants.SCREEN_HEIGHT - btnMiniMap.getHeight()
		// - missionDetailTexRegion.getRegionHeight() - 12);
		batch.draw(missionDetailSprite, Constants.SCREEN_WIDTH
				- missionDetailSprite.getWidth() - 10, Constants.SCREEN_HEIGHT
				- btnMiniMap.getHeight() - missionDetailSprite.getHeight() - 5,
				missionDetailSprite.getWidth(), missionDetailSprite.getHeight());

		// ////////////////////teamState////////////////////
		batch.draw(
				teamSprite,
				Constants.SCREEN_WIDTH / 40,
				Constants.SCREEN_HEIGHT - btnPlayerHead.getHeight()
						- Constants.SCREEN_WIDTH / 50
						- teamSprite.getRegionHeight() - 10);

		// ////////////////////getGoods////////////////////
		// batch.draw(getGoodsTexRegion, 0, Constants.SCREEN_HEIGHT
		// - Constants.SCREEN_WIDTH / 50);

		// ////////////////////systemNotice////////////////////
		batch.draw(systemNoticeSprite, Constants.SCREEN_WIDTH / 2
				- systemNoticeSprite.getRegionWidth() / 2,
				(float) (Constants.SCREEN_HEIGHT * 8.5 / 10) - 30);

	}

	/**
	 * 获取世界地图上技能的X坐标
	 * 
	 * @return 技能的X坐标
	 */
	private float getSkill_XinWolrd() {
		int state = Hero.getHero().getState();
		float HeroX = Constants.SCREEN_WIDTH / 2;
		float skillX = 0;
		float attack_Distance = mSkill.getAttack_Distance();
		switch (state) {
		case Hero.RIGHT:
		case Hero.STATIC_RIGHT:
			skillX = HeroX + attack_Distance;
			break;
		case Hero.LEFT:
		case Hero.STATIC_LEFT:
			skillX = HeroX - attack_Distance - currentFrame.getRegionWidth();
			break;
		case Hero.UP:
		case Hero.DOWN:
		case Hero.STATIC_UP:
		case Hero.STATIC_DOWN:
			skillX = HeroX - currentFrame.getRegionWidth() / 2;
			break;

		case Hero.UP_RIGHT:
		case Hero.DOWN_RIGHT:
		case Hero.STATIC_UP_RIGHT:
		case Hero.STATIC_DOWN_RIGHT:
			skillX = (float) (HeroX + attack_Distance * Math.cos(45));
			break;
		case Hero.UP_LEFT:
		case Hero.DOWN_LEFT:
		case Hero.STATIC_UP_LEFT:
		case Hero.STATIC_DOWN_LEFT:
			skillX = (float) (HeroX - attack_Distance * Math.cos(45) - currentFrame
					.getRegionWidth());
			break;
		}
		return skillX;
	}

	/**
	 * 获取世界地图上技能的Y坐标
	 * 
	 * @return 技能的Y坐标
	 */
	private float getSkill_YinWolrd() {
		int state = Hero.getHero().getState();
		float HeroY = Constants.SCREEN_HEIGHT / 2;
		float skillY = 0;
		float attack_Distance = mSkill.getAttack_Distance();

		switch (state) {
		case Hero.RIGHT:
		case Hero.STATIC_RIGHT:
		case Hero.LEFT:
		case Hero.STATIC_LEFT:
			skillY = HeroY - currentFrame.getRegionHeight() / 2;
			break;
		case Hero.UP:
		case Hero.STATIC_UP:
			skillY = HeroY + attack_Distance;
			break;
		case Hero.DOWN:
		case Hero.STATIC_DOWN:
			skillY = HeroY - attack_Distance - currentFrame.getRegionHeight();
			break;

		case Hero.UP_RIGHT:
		case Hero.UP_LEFT:
		case Hero.STATIC_UP_RIGHT:
		case Hero.STATIC_UP_LEFT:
			skillY = (float) (HeroY + attack_Distance * Math.sin(45))
					- currentFrame.getRegionHeight() / 3;
			System.out.println(skillY);
			break;
		case Hero.DOWN_RIGHT:
		case Hero.DOWN_LEFT:
		case Hero.STATIC_DOWN_RIGHT:
		case Hero.STATIC_DOWN_LEFT:
			skillY = (float) (HeroY - attack_Distance * Math.sin(45))
					- currentFrame.getRegionHeight() * 2 / 3;
			System.out.println(skillY);
			break;
		}
		return skillY;
	}

	public void addToStage(Stage stage) {

		stage.addActor(btnPlayerHead);
		stage.addActor(btnSkill_bounds_1);
		stage.addActor(btnSkill_bounds_2);
		stage.addActor(btnSkill_bounds_3);
		stage.addActor(btnSkill_bounds_4);
		stage.addActor(btnSkill_bounds_spec);
		stage.addActor(btnItem);
		stage.addActor(btnMiniMap);
		stage.addActor(btnChat);
		// stage.addActor(btnSetting);
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
