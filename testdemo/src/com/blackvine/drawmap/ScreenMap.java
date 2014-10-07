package com.blackvine.drawmap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blackvine.actor.Hero;

/**
 * 当前屏幕类
 * Created by IceChen on 14-8-27.
 */
public class ScreenMap extends Actor{

    private static ScreenMap Instance = null;

    private int x_in_wordmap = 2600;       //当前屏幕在世界地图的X坐标
    private int y_in_wordmap = 1024;       //当前屏幕在世界地图的Y坐标

    private int width = 10;         //屏幕宽度图块数
    private int height = 10;        //屏幕高度图块数

    private Texture map;
    private SpriteBatch batch;

    private ScreenMap() {
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());
        map = new Texture(Gdx.files.internal("data/map.png"));
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(map, -x_in_wordmap, -(map.getHeight() - y_in_wordmap - Gdx.graphics.getHeight()));
//    	System.out.println("screen x ==" +x_in_wordmap + "==screen y==" +y_in_wordmap);
        super.draw(batch, parentAlpha);
    }

    public void move_X_in_wordmap(float x) {
        x_in_wordmap += (int)x;

        if (x_in_wordmap > map.getWidth() - Gdx.graphics.getWidth()) {
            x_in_wordmap = map.getWidth() - Gdx.graphics.getWidth();
            if (Hero.getHero().getX() + x < Gdx.graphics.getWidth()) {
                Hero.getHero().setX(Hero.getHero().getX() + x);
            }
        }
        else if (x_in_wordmap < 0) {
            x_in_wordmap = 0;

            if (Hero.getHero().getX() + x > 0) {
                Hero.getHero().setX(Hero.getHero().getX() + x);
            }
        }
    }

    public void move_Y_in_wordmap(float y) {
        y_in_wordmap += (int)y;

        if (y_in_wordmap > map.getHeight() - Gdx.graphics.getHeight()) {
            y_in_wordmap = map.getHeight() - Gdx.graphics.getHeight();

            if (Hero.getHero().getY() + y < Gdx.graphics.getHeight()) {
                Hero.getHero().setY(Hero.getHero().getY() - y);
            }
        }
        else if (y_in_wordmap < 0) {
            y_in_wordmap = 0;

            if (Hero.getHero().getY() + y > 0) {
                Hero.getHero().setY(Hero.getHero().getY() - y);
            }
        }
    }

    public static ScreenMap getInstance() {
        if (Instance == null) {
            Instance = new ScreenMap();
        }
        return Instance;
    }

	public int getX_in_wordmap() {	
		return x_in_wordmap;
	}

	public int getY_in_wordmap() {
		return y_in_wordmap;
	}

    
}
