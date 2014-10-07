package com.blackvine.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/**
 * Created by IceChen on 14-10-5.
 */
public class Npc extends BasicActor {

	public Npc() {
		its = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
		it = its.findRegion("hero_girl");
		intAnimation();
	}

	public Npc(float screenX, float screenY) {
		its = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
		it = its.findRegion("hero_girl");
		intAnimation();
		System.out.println("x===" + screenX);
		this.setScreenPosition(screenX, screenY);
	}
}
