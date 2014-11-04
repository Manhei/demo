package com.blackvine.actor;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Skill {

//	/**
//	 * 技能的坐标跟宽高
//	 */
//	private int x;
//	private int y;
//	private int w;
//	private int h;
	private int[] attack_Range; // 攻击范围
	private int attack_Distance = 0; // 技能攻击距离

	/**
	 * 绘制技能
	 * 
	 * @param batch
	 * @param parentAlpha
	 */
	public void draw(SpriteBatch batch, float parentAlpha) {
	}

	private Skill(FileHandle fileHandle) {
		init(fileHandle);
	}

	public static Skill getSkill(FileHandle fileHandle) {
		Skill skill = new Skill(fileHandle);
		return skill;
	}

	/**
	 * 通过配置文件来解析技能的事件和图片
	 * 
	 * @param fileHandle
	 */
	private void init(FileHandle fileHandle) {

	}

	// public int getX() {
	// return x;
	// }
	//
	// public void setX(int x) {
	// this.x = x;
	// }
	//
	// public int getY() {
	// return y;
	// }
	//
	// public void setY(int y) {
	// this.y = y;
	// }

	public int[] getAttack_Range() {
		return attack_Range;
	}

	public void setAttack_Range(int[] attack_Range) {
		this.attack_Range = attack_Range;
	}

	public int getAttack_Distance() {
		return attack_Distance;
	}

	public void setAttack_Distance(int attack_Distance) {
		this.attack_Distance = attack_Distance;
	}
}
