package com.blackvine.drawmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by IceChen on 14-8-29.
 */
public class PieceMap {

	private static final int width = 1024;
	private static final int height = 512;

	private int pieceX_InWorld; // 地图块在世界地图中的X坐标 （坐上为0,0）
	private int pieceY_InWorld; // 地图块在世界地图中的Y坐标

	private Texture texture;

	private String mapSurfaceFile; // 地表层文件
	private String mapBuildFile; // 建筑层文件
	private int mapSurface[][]; // 地表层信息
	private int mapBuild[][]; // 建筑层信息

	public PieceMap(int pieceX_InWorld, int pieceY_InWorld) {
		this.pieceX_InWorld = pieceX_InWorld;
		this.pieceY_InWorld = pieceY_InWorld;
		String texturePath = "map/images/map"
				+ String.format("%04d", pieceY_InWorld)
				+ String.format("%04d", pieceX_InWorld) + ".png";
		if (WorldMap.getWorldMap().am.isLoaded(texturePath)) {
			texture = WorldMap.getWorldMap().am.get(texturePath);
		} else {
			System.out.print("x = " + pieceX_InWorld + " y = " + pieceY_InWorld
					+ "\n");
			System.exit(44);
		}
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, WorldMap.worldXtoScreenX(pieceX_InWorld * width),
				WorldMap.worldYtoScreenY(pieceY_InWorld * height, height));
	}
}
