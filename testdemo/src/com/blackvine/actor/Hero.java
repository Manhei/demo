package com.blackvine.actor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blackvine.drawmap.ScreenMap;

/**
 * Created by IceChen on 14-9-13.
 */
public class Hero extends Actor {

    private int margin = 2;
    private int pixHeight = 5;
    private int maxHp = 100; //总血量
    private int currenHp = 100; //当前血量

    private static Hero Instance = null;

    public static final int STATIC = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    public static final int UP_LEFT = 5;
    public static final int UP_RIGHT = 6;
    public static final int DOWN_LEFT = 7;
    public static final int DOWN_RIGHT = 8;
    public static final int STATIC_LEFT = 9;
    public static final int STATIC_RIGHT = 10;
    public static final int STATIC_UP = 11;
    public static final int STATIC_DOWN = 12;
    public static final int STATIC_UP_LEFT = 13;
    public static final int STATIC_UP_RIGHT = 14;
    public static final int STATIC_DOWN_LEFT = 15;
    public static final int STATIC_DOWN_RIGHT = 16;

    private float frameDuration = 0.15f;

    public float speed = 3;


    private TextureAtlas ta;
    private TextureRegion hero;
    private TextureRegion[][] walkFrames;
    private Animation[] walkAnimation;
    private int state = STATIC_LEFT;
    private float stateTime = 0;
    private TextureRegion keyFrame;

    private int hero_width = 70;
    private int hero_height = 124;

    private float getHero_width = hero_width;
    private float getHero_height = hero_height;



    private Hero() {
        ta = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
        hero = ta.findRegion("hero_girl");
        walkFrames = hero.split(hero_width, hero_height);
        walkAnimation = new Animation[17];
        walkAnimation[LEFT] = new Animation(frameDuration, walkFrames[1]);
        walkAnimation[RIGHT] = new Animation(frameDuration, walkFrames[2]);
        walkAnimation[UP] = new Animation(frameDuration, walkFrames[3]);
        walkAnimation[DOWN] = new Animation(frameDuration, walkFrames[0]);
        walkAnimation[STATIC_LEFT] = new Animation(frameDuration, walkFrames[1][0]);
        walkAnimation[STATIC_RIGHT] = new Animation(frameDuration, walkFrames[2][0]);
        walkAnimation[STATIC_UP] = new Animation(frameDuration, walkFrames[3][0]);
        walkAnimation[STATIC_DOWN] = new Animation(frameDuration, walkFrames[0][0]);
        walkAnimation[UP_LEFT] = new Animation(frameDuration, walkFrames[6]);
        walkAnimation[DOWN_RIGHT] = new Animation(frameDuration, walkFrames[5]);
        walkAnimation[UP_RIGHT] = new Animation(frameDuration, walkFrames[7]);
        walkAnimation[DOWN_LEFT] = new Animation(frameDuration, walkFrames[4]);
        walkAnimation[STATIC_UP_LEFT] = new Animation(frameDuration, walkFrames[6][0]);
        walkAnimation[STATIC_DOWN_RIGHT] = new Animation(frameDuration, walkFrames[5][0]);
        walkAnimation[STATIC_UP_RIGHT] = new Animation(frameDuration, walkFrames[7][0]);
        walkAnimation[STATIC_DOWN_LEFT] = new Animation(frameDuration, walkFrames[4][0]);


        setWidth(getHero_width);
        setHeight(getHero_height);
        setX(Gdx.graphics.getWidth()/2 - getWidth()/2);
        setY(Gdx.graphics.getHeight()/2 - getHeight()/2);

    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        keyFrame = walkAnimation[state].getKeyFrame(stateTime, true);
        go();
        initHp(batch);
        batch.draw(keyFrame, getX(), getY(), keyFrame.getRegionWidth(), keyFrame.getRegionHeight());

        super.draw(batch, parentAlpha);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public void go() {
        float temSpeed = (float) (speed / Math.pow(2, 0.5f)) * 1.1f;
        switch(state) {
            case UP_LEFT:
//                setX(getX()-speed);
//                setY(getY()+speed);
                ScreenMap.getInstance().move_X_in_wordmap(-temSpeed);
                ScreenMap.getInstance().move_Y_in_wordmap(-temSpeed);
                break;
            case UP_RIGHT:
//                setX(getX()+speed);
//                setY(getY()+speed);
                ScreenMap.getInstance().move_X_in_wordmap(+temSpeed);
                ScreenMap.getInstance().move_Y_in_wordmap(-temSpeed);
                break;
            case DOWN_LEFT:
//                setX(getX()-speed);
//                setY(getY()-speed);
                ScreenMap.getInstance().move_X_in_wordmap(-temSpeed);
                ScreenMap.getInstance().move_Y_in_wordmap(+temSpeed);
                break;
            case DOWN_RIGHT:
//                setX(getX()+speed);
//                setY(getY()-speed);
                ScreenMap.getInstance().move_X_in_wordmap(+temSpeed);
                ScreenMap.getInstance().move_Y_in_wordmap(+temSpeed);
                break;
            case LEFT:
//                setX(getX()-speed);
//                setY(getY()+speed);
                ScreenMap.getInstance().move_X_in_wordmap(-speed);
                break;
            case UP:
//                setX(getX()+speed);
//                setY(getY()+speed);
                ScreenMap.getInstance().move_Y_in_wordmap(-speed);
                break;
            case DOWN:
//                setX(getX()-speed);
//                setY(getY()-speed);
                ScreenMap.getInstance().move_Y_in_wordmap(+speed);
                break;
            case RIGHT:
//                setX(getX()+speed);
//                setY(getY()-speed);
                ScreenMap.getInstance().move_X_in_wordmap(+speed);
                break;
        }
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > Gdx.graphics.getWidth() - keyFrame.getRegionWidth()) {
            setX(Gdx.graphics.getWidth() - keyFrame.getRegionWidth());
        }
        if (getY() < 0) {
            setY(0);
        }
        if (getY() > Gdx.graphics.getHeight() - keyFrame.getRegionHeight()) {
            setY(Gdx.graphics.getHeight() - keyFrame.getRegionHeight());
        }
    }

    public static Hero getHero() {
        if (Instance == null) {
            Instance = new Hero();
        }
        return Instance;
    }


    private void initHp(SpriteBatch batch){
        Pixmap pixmap = new Pixmap(64,8, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK.r,Color.BLACK.g,Color.BLACK.b,Color.BLACK.a);
        pixmap.drawRectangle(0, 0, hero_width -1, pixHeight);
        Texture pixmaptex = new Texture(pixmap);
        TextureRegion pix = new TextureRegion(pixmaptex, hero_width - 1 , hero_height);
        batch.draw(pix, this.getX(), this.getY() + this.margin);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(1, 1, ( hero_width * currenHp / maxHp ) -3, pixHeight - 2);
        Texture hpixmaptex = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion npix = new TextureRegion(hpixmaptex,hero_width - 3,hero_height);
        batch.draw(npix, this.getX(),this.getY() + this.margin);
    }

}
