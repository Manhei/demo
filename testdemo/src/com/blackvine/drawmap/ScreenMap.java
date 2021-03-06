package com.blackvine.drawmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 当前屏幕类 Created by IceChen on 14-8-27.
 */
public class ScreenMap extends Actor {

	private static ScreenMap Instance = null;

	private int xInWorld = 2600; // 当前屏幕在世界地图的X坐标
	private int yInWorld = 1024; // 当前屏幕在世界地图的Y坐标

//	private NinePieceMap map;
    private C45DegreesMap map;
	private WorldMap worldMap;

	private ScreenMap() {
	}

	public void load() {
		worldMap = WorldMap.getWorldMap();
//		map = NinePieceMap.getNinePieceMap();
        map = new C45DegreesMap();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		worldMap.updateAssetManager();
		map.draw(batch, -100, -Gdx.graphics.getHeight());
		super.draw(batch, parentAlpha);
	}

	public void moveX_InWorld(float x) {
		xInWorld += (int) x;
	}

	public void moveY_InWorld(float y) {
		yInWorld += (int) y;
	}

	public static ScreenMap getInstance() {
		if (Instance == null) {
			Instance = new ScreenMap();
		}
		return Instance;
	}

	public int getX_InWorld() {
		return xInWorld;
	}

	public int getY_InWorld() {
		return yInWorld;
	}

}
