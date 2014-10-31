package com.blackvine.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blackvine.drawmap.ScreenMap;
import com.me.mygdxgame.GameScreen;

/**
 * Created by IceChen on 14-9-13.
 */
public class Hero {

	private static Hero Instance = null;

	public static final int STATIC = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	public static final int UP_LEFT = 5;
	public static final int UP_RIGHT = 6;
	public static final int DOWN_LEFT = 7;
	public static final int DOWN_RIGHT = 8;
	public static final int STATIC_LEFT = 9;
	public static final int STATIC_RIGHT = 10;
	public static final int STATIC_UP = 11;
	public static final int STATIC_DOWN = 12;
	public static final int STATIC_UP_LEFT = 13;
	public static final int STATIC_UP_RIGHT = 14;
	public static final int STATIC_DOWN_LEFT = 15;
	public static final int STATIC_DOWN_RIGHT = 16;

	private float frameDuration = 0.15f;

	public float speed = 3;

	private TextureAtlas ta;
	private TextureRegion hero;
	private TextureRegion[][] walkFrames;
	private Animation[] walkAnimation;
	private int state = STATIC_LEFT;
	private float stateTime = 0;
	private TextureRegion keyFrame;

	private int heroWidth = 70;
	private int heroHeight = 124;

	private float getHeroWidth = heroWidth;
	private float getHeroHeight = heroHeight;
	private float mapX;
	private float mapY;
	private float screenX;
	private float screenY;

	private Hero() {
		ta = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
		hero = ta.findRegion("hero_girl");
		walkFrames = hero.split(heroWidth, heroHeight);
		walkAnimation = new Animation[17];
		walkAnimation[LEFT] = new Animation(frameDuration, walkFrames[1]);
		walkAnimation[RIGHT] = new Animation(frameDuration, walkFrames[2]);
		walkAnimation[UP] = new Animation(frameDuration, walkFrames[3]);
		walkAnimation[DOWN] = new Animation(frameDuration, walkFrames[0]);
		walkAnimation[STATIC_LEFT] = new Animation(frameDuration,
				walkFrames[1][0]);
		walkAnimation[STATIC_RIGHT] = new Animation(frameDuration,
				walkFrames[2][0]);
		walkAnimation[STATIC_UP] = new Animation(frameDuration,
				walkFrames[3][0]);
		walkAnimation[STATIC_DOWN] = new Animation(frameDuration,
				walkFrames[0][0]);
		walkAnimation[UP_LEFT] = new Animation(frameDuration, walkFrames[6]);
		walkAnimation[DOWN_RIGHT] = new Animation(frameDuration, walkFrames[5]);
		walkAnimation[UP_RIGHT] = new Animation(frameDuration, walkFrames[7]);
		walkAnimation[DOWN_LEFT] = new Animation(frameDuration, walkFrames[4]);
		walkAnimation[STATIC_UP_LEFT] = new Animation(frameDuration,
				walkFrames[6][0]);
		walkAnimation[STATIC_DOWN_RIGHT] = new Animation(frameDuration,
				walkFrames[5][0]);
		walkAnimation[STATIC_UP_RIGHT] = new Animation(frameDuration,
				walkFrames[7][0]);
		walkAnimation[STATIC_DOWN_LEFT] = new Animation(frameDuration,
				walkFrames[4][0]);
		setScreenX(GameScreen.ScreenWidth / 2 - getHeroWidth / 2);
		setScreenY(GameScreen.ScreenHeight / 2 - getHeroHeight / 2);

	}

	public void draw(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		keyFrame = walkAnimation[state].getKeyFrame(stateTime, true);
		go();
		batch.draw(keyFrame, getScreenX(), getScreenY(),
				keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public void go() {
		float temSpeed = (float) (speed / Math.pow(2, 0.5f)) * 1.1f;
		switch (state) {
		case UP_LEFT:
			// setX(getX()-speed);
			// setY(getY()+speed);
			ScreenMap.getInstance().moveX_InWorld(-temSpeed);
			ScreenMap.getInstance().moveY_InWorld(-temSpeed);
			break;
		case UP_RIGHT:
			// setX(getX()+speed);
			// setY(getY()+speed);
			ScreenMap.getInstance().moveX_InWorld(+temSpeed);
			ScreenMap.getInstance().moveY_InWorld(-temSpeed);
			break;
		case DOWN_LEFT:
			// setX(getX()-speed);
			// setY(getY()-speed);
			ScreenMap.getInstance().moveX_InWorld(-temSpeed);
			ScreenMap.getInstance().moveY_InWorld(+temSpeed);
			break;
		case DOWN_RIGHT:
			// setX(getX()+speed);
			// setY(getY()-speed);
			ScreenMap.getInstance().moveX_InWorld(+temSpeed);
			ScreenMap.getInstance().moveY_InWorld(+temSpeed);
			break;
		case LEFT:
			// setX(getX()-speed);
			// setY(getY()+speed);
			ScreenMap.getInstance().moveX_InWorld(-speed);
			break;
		case UP:
			// setX(getX()+speed);
			// setY(getY()+speed);
			ScreenMap.getInstance().moveY_InWorld(-speed);
			break;
		case DOWN:
			// setX(getX()-speed);
			// setY(getY()-speed);
			ScreenMap.getInstance().moveY_InWorld(+speed);
			break;
		case RIGHT:
			// setX(getX()+speed);
			// setY(getY()-speed);
			ScreenMap.getInstance().moveX_InWorld(+speed);
			break;
		}
		// if (getScreenX() < 0) {
		// setScreenX(0);
		// }
		// if (getScreenX() > Gdx.graphics.getWidth() -
		// keyFrame.getRegionWidth()) {
		// setScreenX(Gdx.graphics.getWidth() - keyFrame.getRegionWidth());
		// }
		// if (getScreenY() < 0) {
		// setScreenY(0);
		// }
		// if (getScreenY() > Gdx.graphics.getHeight() -
		// keyFrame.getRegionHeight()) {
		// setScreenY(Gdx.graphics.getHeight() - keyFrame.getRegionHeight());
		// }
	}

	public static Hero getHero() {
		if (Instance == null) {
			Instance = new Hero();
		}
		return Instance;
	}

	public float getMapX() {
		return mapX;
	}

	public void setMapX(float mapX) {
		this.mapX = mapX;
	}

	public float getMapY() {
		return mapY;
	}

	public void setMapY(float mapY) {
		this.mapY = mapY;
	}

	/**
	 * 更改为获取screenX
	 * 
	 * @return
	 */
	public float getScreenX() {
		return screenX;
	}

	public void setScreenX(float screenX) {
		this.screenX = screenX;
	}

	/**
	 * 更改为获取screenY
	 * 
	 * @return
	 */
	public float getScreenY() {
		return screenY;
	}

	public void setScreenY(float screenY) {
		this.screenY = screenY;
	}

}
