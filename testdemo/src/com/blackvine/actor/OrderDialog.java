package com.blackvine.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class OrderDialog extends Actor {
	private Pixmap topPixmap;
	private Pixmap bottomPixmap;
	private Texture topTexture;
	private Texture bottomTexture;
	private TextureRegion topTextureRegion;
	private TextureRegion bottomTextureRegion;
	private int width;
	private int height;
	private int time;
	private String[] said;
	private int mainX = 0;
	private int position = 0;
	private boolean isGirl = true;
	private Texture mainTexture;
	private TextureRegion textureRegion;
	private FreeTypeFontGenerator freeTypeFontGenerator;
	private FreeTypeBitmapFontData freeTypeBitmapFontData;
	private BitmapFont bitmapFont;
	

	public void setSaid(String[] said) {
		this.said = said;
		time = said.length;
//		freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		freeTypeFontParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + said[position];
		freeTypeBitmapFontData = freeTypeFontGenerator.generateData(20,freeTypeFontGenerator.DEFAULT_CHARS + said[position].toString(),false);
		bitmapFont = new BitmapFont(freeTypeBitmapFontData,freeTypeBitmapFontData.getTextureRegions(),false);
		bitmapFont.setColor(Color.WHITE);
	}

	public OrderDialog () {
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		this.setX(0);
		this.setY(0);
		this.setWidth(width);
		this.setHeight(height);
		initView();
	}
	
	private void initView() {
		topPixmap = new Pixmap(64,8, Pixmap.Format.RGBA8888);
		topPixmap.setColor(Color.BLACK);
		topPixmap.fillRectangle(0, 0, width, height/10);
		topTexture = new Texture(topPixmap);
		topTextureRegion = new TextureRegion(topTexture, width, height/10);
		bottomPixmap = new Pixmap(64,8, Pixmap.Format.RGBA8888);
		bottomPixmap.setColor(Color.BLACK);
		bottomPixmap.fillRectangle(0,0, width, height/3);
		bottomTexture = new Texture(bottomPixmap);
		bottomTextureRegion = new TextureRegion(bottomTexture,width,height/3);
		mainTexture = new Texture(Gdx.files.internal("data/girl.jpg"));
		textureRegion = new TextureRegion(mainTexture,200,height/2);
		freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/ziti.ttf"));
		textureRegion = new TextureRegion(mainTexture,200,height/2);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(topTextureRegion, 0, 9*height/10);
		batch.draw(bottomTextureRegion, 0, 0);
		batch.draw(textureRegion,mainX,height/3);
		bitmapFont.drawMultiLine(batch, said[position], width/10, height/3 - 5);
		super.draw(batch, parentAlpha);
	}
	
	public boolean isAlive(){
		return time >0;
	}
	
	public void cutdownTime() {
		time -= 1;
		if (isGirl) {
			isGirl = false;
			mainTexture = new Texture(Gdx.files.internal("data/boy.jpg"));
			mainX = width -200;
		}else {
			isGirl = true;
			mainTexture = new Texture(Gdx.files.internal("data/girl.jpg"));
			mainX = 0;
		}
		if (time == 0) {
			isGirl = true;
			mainX = 0;
		}
		position += 1;
		if (position < said.length) {
			textureRegion = new TextureRegion(mainTexture,200,height/2);
			freeTypeBitmapFontData = freeTypeFontGenerator.generateData(20,freeTypeFontGenerator.DEFAULT_CHARS + said[position].toString(),false);
			bitmapFont = new BitmapFont(freeTypeBitmapFontData,freeTypeBitmapFontData.getTextureRegions(),false);
			bitmapFont.setColor(Color.WHITE);
		}else {
			position = 0;
		}
	}
	
}
