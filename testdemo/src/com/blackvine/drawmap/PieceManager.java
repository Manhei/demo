package com.blackvine.drawmap;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by IceChen on 14/10/25.
 */
public class PieceManager {
    private static PieceManager Instance = null;

    private PieceManager() {
    }

    public void load(int beginX, int beginY, int sidecar) {
        for (int i = beginX; i < beginX + sidecar; i++) {
            for (int j = beginY; j < beginY + sidecar; j++ ) {
                if (i > -1 && i < WorldMap.getMap_piece_width()
                        && j > -1 && j < WorldMap.getMap_piece_height()) {
                    String texturePath =  "map/images/map"
                            + String.format("%04d", j) + String.format("%04d", i)
                            + ".png";
                    if (!WorldMap.getWorldMap().am.isLoaded(texturePath)) {
                        WorldMap.getWorldMap().am.load(texturePath, Texture.class);
                    }
                }
            }
        }
        WorldMap.getWorldMap().isLoding = true;
        System.out.print("true");
    }

    public static PieceManager getPieceManager() {
        if (Instance == null) {
            Instance = new PieceManager();
        }
        return Instance;
    }
}
