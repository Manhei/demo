package com.blackvine.drawmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by IceChen on 14/11/5.
 */
public class C45DegreesMap {
    int width = 20;
    int height = 30;
    TextureRegion map[][];
    int itemWidth;
    int itemHeight;

    public C45DegreesMap() {
        map = new TextureRegion[width][height];
        for (int i = 0; i < width; i++ ) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new TextureRegion(new Texture(Gdx.files.internal("caodi3.png")));
//                e.setTexture(new Texture(Gdx.files.internal("caodi3.png")));
            }
        }
        itemWidth = map[0][0].getRegionWidth();
        itemHeight = map[0][0].getRegionHeight();
    }

    public void draw(SpriteBatch batch, int x, int y) {
        for (int i = 0; i < width; i++ ) {
            for (int j = 0; j < height; j++) {
                batch.draw(map[i][j], x + (itemWidth / 2) * (i - j), y + (itemHeight / 2) * (i + j));
            }
        }
    }
}
