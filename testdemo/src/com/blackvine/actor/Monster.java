package com.blackvine.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Monster extends BasicActor {
	private boolean isAI = true;
	private static final int speed = 5;
	private float stateTime = 0;
	public Monster() {
		its = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
		it = its.findRegion("hero_girl");
		intAnimation();
	}
	
	public Monster(float x,float y) {
		its = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
		it = its.findRegion("hero_girl");
		intAnimation();
		setScreenX(x);
		setScreenY(y);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	/**
	 * 自动行走
	 * 初期只实现左右行走
	 */
	public void autoWalk() {
		if (isAI) {
			if (stateTime < 20) {
				setState(STATIC_RIGHT);
				setScreenX(getScreenX() + speed);
			}else if (stateTime > 20 && stateTime < 40) {
				setState(STATIC_DOWN);
				setScaleX(getScreenY() - speed);
			}else if (stateTime > 40 &&stateTime < 60) {
				setState(STATIC_LEFT);
				setScaleX(getScreenX() + speed);
			}else {
				setState(STATIC_UP);
				setScaleY(getScreenY() + speed);
				if (stateTime == 100) {
					stateTime = 0;
				}
			}
			stateTime += 1;
		}else {
			stateTime = 0;
		}
	}
	
	
}
