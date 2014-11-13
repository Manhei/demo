package com.blackvine.actor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackvine.animation.MyAnimation;
import com.blackvine.constant.Constants;
import com.blackvine.manager.SingleAssetManager;
import com.blackvine.tools.MyFileNameFilter;

public class Skill {

	// /**
	// * 技能的坐标跟宽高
	// */
	// private int x;
	// private int y;
	// private int w;
	// private int h;
	private int[] attack_Range; // 攻击范围
	private float attack_Distance = 20; // 技能攻击距离
	public MyAnimation skillAni;
	private int[] totalDuration;// 播放时间数组
	public TextureRegion[] keyFrames;
	public TextureRegion texRegion;
	public Texture tex;
	private long[] vibrateTime;// 技能震动时间

	private int skillNo;// 技能编号
	private int[] frameDuration, duration;// 技能时间数组
	private SingleAssetManager am;

	// private File singleSkill; // 单个技能文件夹
	// private File[] singleSkillDir; // 单个技能目录下的文件数组
	// private MyFileNameFilter filter; // png文件过滤器

	private List mList; // 技能总数list
	FileHandle mFile;

	// /**
	// * 绘制技能
	// *
	// * @param batch
	// * @param parentAlpha
	// */
	// public void draw(SpriteBatch batch, float parentAlpha) {
	// }

	/**
	 * 
	 * @param skillNo
	 *            技能编号
	 */
	public Skill(int skillNo) {
		// init(fileHandle);
		this.skillNo = skillNo;
		am = SingleAssetManager.getSingleAssetManager();
		texRegion = new TextureRegion();
		initTextureRegionAndDuration(skillNo); // 初始化技能图片和时间

		attack_Range = new int[] { texRegion.getRegionWidth(),
				texRegion.getRegionHeight() };
	}

	private void initTextureRegionAndDuration(int skillNo) {
		// singleSkill = new File("skill" + File.separator + skillNo
		// + File.separator);
		// singleSkill = new File(Constants.SKILL_DIR + skillNo);
		// filter = new MyFileNameFilter();
		// singleSkillDir = singleSkill.listFiles(filter);
		mList = am.getSkillNoList();
		if (mList != null) {
			int texSize = Integer.parseInt((String) mList.get(skillNo));
			keyFrames = new TextureRegion[texSize];
			for (int i = 0; i < texSize; i++) {
				tex = am.get("skill/" + skillNo + "/" + i + ".png",
						Texture.class);

				// texRegion.setTexture(tex);
				texRegion = new TextureRegion(tex);
				keyFrames[i] = texRegion;
			}

			duration = getDuration(Constants.SKILL_DIR + skillNo
					+ File.separator + "delay.txt");
			skillAni = new MyAnimation(duration, keyFrames);
		}
	}

	/**
	 * 获取某技能的每一帧时间数组
	 * 
	 * @param filePath
	 * @return
	 */
	public int[] getDuration(String filePath) {
		mFile = Gdx.files.internal(filePath);
		String s, s2 = null;
		String[] array;
		int i = 0, arrayLength = 0;
		BufferedReader buff = null, buff1 = null;
		try {
			buff = new BufferedReader(mFile.reader());

			while ((s = buff.readLine()) != null) {
				{
					array = s.split(":");
					if (array.length > 1) {
						arrayLength++;
					}
				}
			}
			frameDuration = new int[arrayLength];
			buff1 = new BufferedReader(mFile.reader());
			while ((s = buff1.readLine()) != null) {
				array = s.split(":");
				if (array.length > 1) {
					s2 = array[1].toString().trim();
					frameDuration[i] = Integer.parseInt(s2);
					i++;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff1.close();
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return frameDuration;
	}

	public void setTextureRegionAndDuration(int[] totalDuration,
			TextureRegion[] keyFrames) {
		this.totalDuration = totalDuration;
		this.keyFrames = keyFrames;
		skillAni.setTextureRegionAndDuration(totalDuration, keyFrames);
	}

	// public static Skill getSkill(FileHandle fileHandle) {
	// Skill skill = new Skill(fileHandle);
	// return skill;
	// }

	// /**
	// * 通过配置文件来解析技能的事件和图片
	// *
	// * @param fileHandle
	// */
	// private void init(FileHandle fileHandle) {
	//
	// }

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

	public float getAttack_Distance() {
		return attack_Distance;
	}

	public void setAttack_Distance(int attack_Distance) {
		this.attack_Distance = attack_Distance;
	}

	public long[] getVibrateTime() {
		return vibrateTime;
	}

	public void setVibrateTime(long[] vibrateTime) {
		this.vibrateTime = vibrateTime;
	}
}
