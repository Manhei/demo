package com.blackvine.drawmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by IceChen on 14-8-29.
 */
public class PieceMap {

<<<<<<< HEAD
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
=======
    private static final int width = 1024;
    private static final int height = 512;

    private int piece_x_in_worldmap;
    private int piece_y_in_worldmap;

    private Texture texture;
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0

	public static int getHeight() {
		return height;
	}

<<<<<<< HEAD
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texture, WorldMap.worldXtoScreenX(pieceX_InWorld * width),
				WorldMap.worldYtoScreenY(pieceY_InWorld * height, height));
	}
=======
    public PieceMap(int piece_x_in_worldmap, int piece_y_in_worldmap) {
        this.piece_x_in_worldmap = piece_x_in_worldmap;
        this.piece_y_in_worldmap = piece_y_in_worldmap;
        String texturePath =  "map/images/map"
                + String.format("%04d", piece_y_in_worldmap) + String.format("%04d", piece_x_in_worldmap)
                + ".png";
        if (WorldMap.getWorldMap().am.isLoaded(texturePath)) {
            texture = WorldMap.getWorldMap().am.get(texturePath);
        }
        else {
            System.out.print("x = " + piece_x_in_worldmap + " y = " + piece_y_in_worldmap + "\n");
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
        batch.draw(texture, WorldMap.worldmapXtoScreenX(piece_x_in_worldmap * width),
                WorldMap.worldmapYtoScreenY(piece_y_in_worldmap * height, height));
    }
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0
}
