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

/**
 * Created by IceChen on 14-10-5.
 */
public class BasicActor extends Actor{

    private int margin = 2;
    private int pixHeight = 5;
    protected int maxHp = 100; //总血量
    protected int currenHp = 100; //当前血量
    private int width = 70;
    private int height = 124;
    private float screenX = 2600;
    private float screenY = 1024;

    private float frameDuration = 0.15f;
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

    private int state = STATIC_DOWN;
    private float stateTime = 0;
    private TextureRegion[][] walkFrames;
    private Animation[] walkAnimation;
    protected TextureAtlas its;
    protected TextureRegion it;

    private Pixmap mHPPixmap;
    private Pixmap nHPPixmap;
    private TextureRegion mHPTextureRegion;
    private TextureRegion nHPTextureRegion;
    private TextureRegion keyFrame;

    public BasicActor(){
//        intAnimation();
//    	initHp();
    }

    protected void intAnimation(){
        //根据每一个Actor的属性来指定资源文件
//        its = new TextureAtlas(Gdx.files.internal("data/hero.pack"));
//        it = its.findRegion();
        walkFrames = it.split(width, height);
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
        setWidth(width);
        setHeight(height);
    }

    public void setCurrenHp(int currenHp) {
        this.currenHp = currenHp;
    }

    public void cutDownHp(int cutdownHp) {
        if (currenHp != 0)
        {
            if (currenHp >= cutdownHp){
                this.currenHp -= cutdownHp;
            }else{
                this.currenHp = 0;
            }

        }
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        keyFrame = walkAnimation[state].getKeyFrame(stateTime, true);
        initHp(batch);
        batch.draw(keyFrame, this.getX(), this.getY(), keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
    }

    public Boolean isAlive(){
       return this.currenHp > 0;
    }
    
    private void initHp(SpriteBatch batch){
        Pixmap pixmap = new Pixmap(64,8, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK.r,Color.BLACK.g,Color.BLACK.b,Color.BLACK.a);
        pixmap.drawRectangle(0, 0, width -1, pixHeight);
        Texture pixmaptex = new Texture(pixmap);
        TextureRegion pix = new TextureRegion(pixmaptex, width - 1 , height);
        batch.draw(pix, this.getX(), this.getY() + this.margin);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(1, 1, ( width * currenHp / maxHp ) -3, pixHeight - 2);
        Texture hpixmaptex = new Texture(pixmap);
        TextureRegion npix = new TextureRegion(hpixmaptex,width - 3,height);
        batch.draw(npix, this.getX(),this.getY() + this.margin);
    }

    public void setScreenPosition(float x, float y) {    	
    		this.setX(screenX - x);
			this.setY(y - screenY - height);
    }

	public void setScreenX(float screenX) {
		this.screenX = screenX;
	}

	public void setScreenY(float screenY) {
		this.screenY = screenY;
	}

	public float getScreenX() {
		return screenX;
	}

	public float getScreenY() {
		return screenY;
	}

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}

