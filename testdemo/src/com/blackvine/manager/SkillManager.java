package com.blackvine.manager;

import java.util.HashMap;
import java.util.Map;

import com.blackvine.actor.Skill;

/**
 * 技能管理类
 * 
 * @author c
 * 
 */
public class SkillManager {

	private static SkillManager mSkillManager;

	// 所有预加载的技能
	public static Map<Integer, Skill> mSkills;
	// 技能编号
	private int[] mFileNumber = null;

	public static SkillManager getInstance() {
		if (mSkillManager == null) {
			mSkillManager = new SkillManager();
		}
		return mSkillManager;
	}

	/**
	 * 初始化函数 加载用户所有技能
	 */
	private SkillManager() {
		mSkills = new HashMap<Integer, Skill>();
	}

	/**
	 * 初始化所有技能
	 * 
	 * @param numbers
	 *            技能的编号数组
	 */
	public void setSkillByNo(int[] skillNos) {
		mFileNumber = skillNos;
		for (int i = 0; i < mFileNumber.length; i++) {
			Skill skill = new Skill(mFileNumber[i]);
			if (!mSkills.containsKey(i)) {
				mSkills.put(i, skill);
			}
		}
	}

	public void setSkillByNo(int skillNo) {
		if (!mSkills.containsKey(skillNo)) {
			Skill skill = new Skill(skillNo);
			mSkills.put(skillNo, skill);
		}
	}

	/**
	 * 通过技能编号获取技能
	 * 
	 * @param skillNo
	 * @return Skill
	 */
	public Skill getSkillByNumber(int skillNo) {
		return mSkills.get(skillNo);
	}

	// /**
	// * 获取到单个技能 内部调用
	// */
	// private Skill getSkillSingle(int i) {
	// FileHandle fileHandle = getFileHandle(i);
	// Skill skill = Skill.getSkill(fileHandle);
	// return skill;
	// }

	// /**
	// * 通过编号获取filehandle
	// *
	// * @param i
	// * @return
	// */
	// private FileHandle getFileHandle(int i) {
	// String file = getFileString(i);
	// FileHandle fileHandle = Gdx.files.internal(file);
	// return fileHandle;
	// }

	// /**
	// * 预留 数据未确定 暂时先写个默认值 通过技能编号获取到文件名
	// *
	// * @return
	// */
	// private String getFileString(int i) {
	// return "data/hero.pack";
	// }

}
