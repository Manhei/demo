package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class MyGdxGame extends Game {
    private static Screen currentScreen;

    @Override
    public void create() {
        currentScreen = new LodingScreen(this);
        this.setScreen(currentScreen);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void setCurrentScreen(Screen screen) {
        currentScreen.dispose();
        currentScreen = screen;
        setScreen(screen);
    }
}
