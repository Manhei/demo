package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * 用户操作层 状态显示 技能按钮 小地图等界面
 * 
 * @author songjunfeng
 * 
 */
public class UserControlScreen extends Actor {

	private static UserControlScreen Instance = null;
	// Texture headTexture;
	TextureRegion headTextureRegion, itemTexRegion, miniMapTexRegion;
	Pixmap HeadPix;
	SpriteBatch batch;
	Button btnPlayerHead, btnSkill_1, btnSkill_2, btnSkill_3, btnSkill_4,
			btnSkill_spec, btnItem, btnMiniMap;
	TextureRegion hpBorderTexRegion, hpTexRegion, monterHpTexRegion;
	TextureRegion mpBorderTexRegion, mpTexRegion;
	TextureRegion skillTextureRegion_1, skillTextureRegion_2,
			skillTextureRegion_3, skillTextureRegion_4,
			skillTextureRegion_spec;
	TextureRegionDrawable temp;

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
		skillState(); // 技能状态
		itemState(); // 物品栏
		miniMap(); // 小地图

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
		btnPlayerHead.setPosition(GameScreen.ScreenWidth / 30,
				GameScreen.ScreenHeight * 8 / 10);
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
				GameScreen.ScreenWidth / 40, Pixmap.Format.RGBA8888);
		monsterHpPix.setColor(Color.GREEN);
		monsterHpPix.fillRectangle(1, 1, GameScreen.ScreenWidth / 5,
				GameScreen.ScreenWidth / 40);
		Texture monsterHpTex = new Texture(monsterHpPix);
		monterHpTexRegion = new TextureRegion(monsterHpTex);
	}

	private void skillState() {
		Pixmap skillPix = new Pixmap(GameScreen.ScreenWidth / 12,
				GameScreen.ScreenWidth / 12, Pixmap.Format.RGBA8888);
		skillPix.setColor(Color.WHITE);
		skillPix.drawRectangle(0, 0, GameScreen.ScreenWidth / 12,
				GameScreen.ScreenWidth / 12);
		Texture skillTex = new Texture(skillPix);

		skillTextureRegion_1 = new TextureRegion(skillTex);
		skillTextureRegion_2 = new TextureRegion(skillTex);
		skillTextureRegion_3 = new TextureRegion(skillTex);
		skillTextureRegion_4 = new TextureRegion(skillTex);
		skillTextureRegion_spec = new TextureRegion(skillTex);

		temp.setRegion(skillTextureRegion_1);
		btnSkill_1 = new Button(temp);
		btnSkill_1.setPosition(
				GameScreen.ScreenWidth - skillTextureRegion_1.getRegionWidth()
						* 2 - GameScreen.ScreenWidth * 2 / 40,
				skillTextureRegion_1.getRegionHeight()
						+ GameScreen.ScreenHeight * 2 / 40);
		btnSkill_1.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能1");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTextureRegion_2);
		btnSkill_2 = new Button(temp);
		btnSkill_2.setPosition(
				GameScreen.ScreenWidth - skillTextureRegion_2.getRegionWidth()
						- GameScreen.ScreenWidth / 40,
				skillTextureRegion_2.getRegionHeight()
						+ GameScreen.ScreenHeight * 2 / 40);
		btnSkill_2.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能2");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTextureRegion_3);
		btnSkill_3 = new Button(temp);
		btnSkill_3.setPosition(
				GameScreen.ScreenWidth - skillTextureRegion_3.getRegionWidth()
						* 2 - GameScreen.ScreenWidth * 2 / 40,
				+GameScreen.ScreenHeight / 40);
		btnSkill_3.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能3");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTextureRegion_4);
		btnSkill_4 = new Button(temp);
		btnSkill_4.setPosition(
				GameScreen.ScreenWidth - skillTextureRegion_4.getRegionWidth()
						- GameScreen.ScreenWidth / 40,
				+GameScreen.ScreenHeight / 40);
		btnSkill_4.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("点击了技能4");
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		temp.setRegion(skillTextureRegion_spec);
		btnSkill_spec = new Button(temp);
		btnSkill_spec.setPosition(
				GameScreen.ScreenWidth / 40,
				GameScreen.ScreenHeight / 2
						- skillTextureRegion_spec.getRegionHeight() / 2);
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
				GameScreen.ScreenWidth * 29 / 30
						- itemTexRegion.getRegionWidth(),
				skillTextureRegion_2.getRegionHeight() * 2
						+ GameScreen.ScreenHeight * 4 / 30);
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
				GameScreen.ScreenWidth / 30
						+ headTextureRegion.getRegionWidth() + 10,
				GameScreen.ScreenHeight * 8 / 10
						+ headTextureRegion.getRegionHeight() * 3 / 4 - 10);
		batch.draw(
				mpTexRegion,
				GameScreen.ScreenWidth / 30
						+ headTextureRegion.getRegionWidth() + 10,
				GameScreen.ScreenHeight * 8 / 10
						+ headTextureRegion.getRegionHeight() * 3 / 4
						- GameScreen.ScreenWidth / 40 - 20);

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

	}

}
