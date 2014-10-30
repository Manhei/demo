package com.me.mygdxgame;

import com.badlogic.gdx.Screen;
import com.blackvine.drawmap.NinePieceMap;
import com.blackvine.drawmap.ScreenMap;
import com.blackvine.drawmap.WorldMap;

/**
 * Created by IceChen on 14/10/28.
 */
class LodingScreen implements Screen {

    private ScreenMap screenMap;
    private MyGdxGame game;

    LodingScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        WorldMap.getWorldMap().updateAssetManager();
        if (!WorldMap.getWorldMap().isLoding) {
            NinePieceMap.getNinePieceMap().updatePiece();
            NinePieceMap.getNinePieceMap().preload();
            game.setScreen(new GameScreen());
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        screenMap = ScreenMap.getInstance();
        screenMap.load();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
