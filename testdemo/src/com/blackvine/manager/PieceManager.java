package com.blackvine.manager;

import com.badlogic.gdx.graphics.Texture;
import com.blackvine.drawmap.WorldMap;

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
                if (i > -1 && i < WorldMap.getPieceMap_width()
                        && j > -1 && j < WorldMap.getPieceMap_height()) {
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
    }

    public static PieceManager getPieceManager() {
        if (Instance == null) {
            Instance = new PieceManager();
        }
        return Instance;
    }
}
