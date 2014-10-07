package com.blackvine.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by IceChen on 14-9-17.
 */
public class Rocker extends Actor {

    private BitmapFont bf;
    private float rad = 0;

    private float downPointX;
    private float downPointY;



    public Rocker() {
        setWidth(Gdx.graphics.getWidth() / 2);
        setHeight(Gdx.graphics.getHeight());

        this.addListener(new InputListener() {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPointX = x;
                downPointY = y;


                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                switch (Hero.getHero().getState()) {
                    case Hero.UP_LEFT:
                        Hero.getHero().setState(Hero.STATIC_UP_LEFT);
                        break;
                    case Hero.DOWN_RIGHT:
                        Hero.getHero().setState(Hero.STATIC_DOWN_RIGHT);
                        break;
                    case Hero.UP_RIGHT:
                        Hero.getHero().setState(Hero.STATIC_UP_RIGHT);
                        break;
                    case Hero.DOWN_LEFT:
                        Hero.getHero().setState(Hero.STATIC_DOWN_LEFT);
                        break;
                    case Hero.LEFT:
                        Hero.getHero().setState(Hero.STATIC_LEFT);
                        break;
                    case Hero.RIGHT:
                        Hero.getHero().setState(Hero.STATIC_RIGHT);
                        break;
                    case Hero.UP:
                        Hero.getHero().setState(Hero.STATIC_UP);
                        break;
                    case Hero.DOWN:
                        Hero.getHero().setState(Hero.STATIC_DOWN);
                        break;
                }
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {

                rad = (float)getRad(downPointX, downPointY, x, y);
                double tem = rad/Math.PI;
                if (tem > -1/(float)8 && tem < 1/(float)8) {
                    Hero.getHero().setState(Hero.RIGHT);
                }
                else if (tem > 1/(float)8 && tem < 3/(float)8) {
                    Hero.getHero().setState(Hero.UP_RIGHT);
                }
                else if (tem > 3/(float)8 && tem < 5/(float)8) {
                    Hero.getHero().setState(Hero.UP);
                }
                else if (tem > 5/(float)8 && tem < 7/(float)8) {
                    Hero.getHero().setState(Hero.UP_LEFT);
                }
                else if (tem > 7/(float)8 || tem < -7/(float)8) {
                    Hero.getHero().setState(Hero.LEFT);
                }
                else if (tem > -3/(float)8 && tem < -1/(float)8) {
                    Hero.getHero().setState(Hero.DOWN_RIGHT);
                }
                else if (tem > -5/(float)8 && tem < 3/(float)8) {
                    Hero.getHero().setState(Hero.DOWN);
                }
                else if (tem > -7/(float)8 && tem < 5/(float)8) {
                    Hero.getHero().setState(Hero.DOWN_LEFT);
                }


//四方向摇杆判断
//                if (x > downPointX) {
//                    if (y > downPointY) {
//                        Hero.getHero().setState(Hero.UP_RIGHT);
//                    }
//                    else {
//                        Hero.getHero().setState(Hero.DOWN_RIGHT);
//                    }
//                }
//                else {
//                    if (y > downPointY) {
//                        Hero.getHero().setState(Hero.UP_LEFT);
//                    }
//                    else {
//                        Hero.getHero().setState(Hero.DOWN_LEFT);
//                    }
//                }

                super.touchDragged(event, x, y, pointer);
            }
        });



    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }


    /***
     * 得到两点之间的弧度
     */
    public double getRad(float px1, float py1, float px2, float py2) {
        //得到两点X的距离
        float x = px2 - px1;
        //得到两点Y的距离
        float y = py1 - py2;
        //算出斜边长
        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //得到这个角度的余弦值（通过三角函数中的定理 ：邻边/斜边=角度余弦值）
        float cosAngle = x / xie;
        //通过反余弦定理获取到其角度的弧度
        float rad = (float) Math.acos(cosAngle);
        //注意：当触屏的位置Y坐标<摇杆的Y坐标我们要取反值-0~-180
        if (py2 < py1) {
            rad = -rad;
        }
        return rad;
    }
}
