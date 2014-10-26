package com.blackvine.drawmap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by IceChen on 14/10/23.
 */
public class NinePieceMap {
    private static NinePieceMap Instance = null;
    private final int col = 3;
    private final int row = 3;
    private int piece_x_in_worldmap;
    private int piece_y_in_worldmap;
    PieceMap[] map = new PieceMap[col * row];
    private int width;
    private int height;

    private NinePieceMap() {

        initPiece();
        width = PieceMap.getWidth() * col;
        height = PieceMap.getHeight() * row;
    }

    private void initPiece() {
        piece_x_in_worldmap = ScreenMap.getX_in_wordmap() / PieceMap.getWidth() - 1;
        piece_y_in_worldmap = ScreenMap.getY_in_wordmap() / PieceMap.getHeight() - 1;
        PieceManager.getPieceManager().load(piece_x_in_worldmap, piece_y_in_worldmap, col);
        new Thread() {
            @Override
            public void run() {
                while (WorldMap.getWorldMap().isLoding) {
                    System.out.print("loop\n");
                }
                updatePiece();
                preload();
                super.run();
            }
        }.start();
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        for (int i = 0; i < col * row; i++) {
            if (map[i] != null) {
                map[i].draw(batch, parentAlpha);
            }
        }
        if (ScreenMap.getX_in_wordmap() / PieceMap.getWidth() - 1 != piece_x_in_worldmap
                || ScreenMap.getY_in_wordmap() / PieceMap.getHeight() - 1 != piece_y_in_worldmap)  {
            updatePiece();
        }
    }

    public void updatePiece() {
        piece_x_in_worldmap = ScreenMap.getX_in_wordmap() / PieceMap.getWidth() - 1;
        piece_y_in_worldmap = ScreenMap.getY_in_wordmap() / PieceMap.getHeight() - 1;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (piece_x_in_worldmap + i > -1 && piece_x_in_worldmap + i < WorldMap.getMap_piece_width()
                        && piece_y_in_worldmap + j > -1 && piece_y_in_worldmap + j < WorldMap.getMap_piece_height()) {
                    map[i*row+j] = new PieceMap(piece_x_in_worldmap + i, piece_y_in_worldmap + j);
                }
                else {
                    map[i*row+j] = null;
                }
            }
        }
        System.out.print("(" + piece_x_in_worldmap + "," + piece_y_in_worldmap + ")");
        preload();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPiece_x_in_worldmap() {
        return piece_x_in_worldmap;
    }

    public int getPiece_y_in_worldmap() {
        return piece_y_in_worldmap;
    }


    public static NinePieceMap getNinePieceMap() {
        if (Instance == null) {
            Instance = new NinePieceMap();
        }
        return Instance;
    }

    public void preload() {
        PieceManager.getPieceManager().load(piece_x_in_worldmap - 1, piece_y_in_worldmap - 1, col + 2);
    }
}
