package com.blackvine.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blackvine.constant.Constants;
import com.blackvine.tools.MyFileNameFilter;

public class SingleAssetManager extends AssetManager {

	private static SingleAssetManager Instance = null;
	// private File skillFile; // 技能资源文件夹
	// private File[] skillsDir; // 技能总目录
	//
	// private File singleSkill; // 单个技能文件夹
	// private File[] singleSkillDir; // 单个技能目录下的文件数组
	private FileHandle mFile; // Gdx引擎读取内部文件

	private List skillNoList;

	public List getSkillNoList() {
		return skillNoList;
	}

	public void setSkillNoList(List skillNoList) {
		this.skillNoList = skillNoList;
	}

	private SingleAssetManager() {
		addTextureAsset();
	}

	/**
	 * 单例获取资源管理器
	 * 
	 * @return SingleAssetManager
	 */
	public static SingleAssetManager getSingleAssetManager() {
		if (Instance == null) {
			Instance = new SingleAssetManager();
		}
		return Instance;
	}

	/**
	 * 加载纹理资源
	 */
	public void addTextureAsset() {
		// skillFile = new File("assets" + File.separator + "skill"
		// + File.separator);
		// skillFile = new File(Constants.SKILL_DIR);
		// skillsDir = skillFile.listFiles();
		loadSkillConfig("config.manhei");
		loadTextureConfig("texture.manhei");
	}

	/**
	 * 读取技能配置文件
	 */
	private void loadSkillConfig(String path) {
		mFile = Gdx.files.internal(path);
		String s = null;
		String[] array;
		skillNoList = new ArrayList<Integer>();
		try {
			BufferedReader buff = new BufferedReader(mFile.reader());
			while ((s = buff.readLine()) != null) {
				{
					array = s.split("::");
					if (array.length > 1) {
						skillNoList.add(array[1]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (skillNoList != null) {
			for (int i = 0; i < skillNoList.size(); i++) {
				// singleSkill = new File(filePath.toString());
				// singleSkillDir = singleSkill.listFiles(filter);
				int k = Integer.parseInt((String) skillNoList.get(i));
				for (int j = 0; j < k; j++) {
					this.load(Constants.SKILL_DIR + i + "/" + j + ".png",
							Texture.class);
				}
			}
		}
	}

	/**
	 * 读取屏幕纹理配置文件
	 * 
	 * @param path
	 */
	private void loadTextureConfig(String path) {
		mFile = Gdx.files.internal(path);
		String s = null;
		try {
			BufferedReader buff = new BufferedReader(mFile.reader());
			while ((s = buff.readLine()) != null) {
				{
					this.load(Constants.SCREEN_TEXTURE_DIR + s, Texture.class);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
