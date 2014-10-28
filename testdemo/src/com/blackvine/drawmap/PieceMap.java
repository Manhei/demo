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

    private int piece_x_in_worldmap;
    private int piece_y_in_worldmap;

    private Texture texture;

    private String mapSurfaceFile;      //地表层文件
    private String mapBuildFile;        //建筑层文件
    private int mapSurface[][];         //地表层信息
    private int mapBuild[][];           //建筑层信息

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
}
