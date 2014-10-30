package com.blackvine.manager;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.blackvine.actor.Skill;

/**
 * 技能管理类
 * 
 * @author c
 *
 */
public class SkillManager {

    private SkillManager mSkillManager;

    // 所有预加载的技能
    private ArrayList<Skill> mSkills;
    // 技能编号
    private int[] mFileNumber = null;

    public SkillManager getInstance() {
        if (mSkillManager == null) {
            mSkillManager = new SkillManager();
        }
        return mSkillManager;
    }

    /**
     * 初始化函数 加载用户所有技能
     */
    private SkillManager() {
        mSkills = new ArrayList<Skill>();
    }

    /**
     * 初始化所有技能
     * 
     * @param numbers
     *            技能的编号数组
     */
    public void setSkillAll(int[] numbers) {
        mFileNumber = numbers;
        for (int i = 0; i < mFileNumber.length; i++) {
            Skill skill = getSkillSingle(mFileNumber[i]);
            mSkills.add(skill);
        }
    }

    /**
     * 获取单个技能
     * 
     * @param i
     *            技能编号
     * @return skill
     */
    public Skill getSkillByNumber(int i) {
        return mSkills.get(i);
    }

    /**
     * 获取到单个技能 内部调用
     */
    private Skill getSkillSingle(int i) {
        FileHandle fileHandle = getFileHandle(i);
        Skill skill = Skill.getSkill(fileHandle);
        return skill;
    }

    /**
     * 通过编号获取filehandle
     * 
     * @param i
     * @return
     */
    private FileHandle getFileHandle(int i) {
        String file = getFileString(i);
        FileHandle fileHandle = Gdx.files.internal(file);
        return fileHandle;
    }

    /**
     * 预留 数据未确定 暂时先写个默认值 通过技能编号获取到文件名
     * 
     * @return
     */
    private String getFileString(int i) {
        return "data/hero.pack";
    }
}
