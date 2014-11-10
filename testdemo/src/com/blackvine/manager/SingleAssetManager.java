package com.blackvine.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.blackvine.constant.Constants;
import com.blackvine.tools.MyFileNameFilter;

public class SingleAssetManager extends AssetManager {

	private static SingleAssetManager Instance = null;
	private StringBuilder filePath = new StringBuilder();
	// private File skillFile; // 技能资源文件夹
	// private File[] skillsDir; // 技能总目录
	//
	// private File singleSkill; // 单个技能文件夹
	// private File[] singleSkillDir; // 单个技能目录下的文件数组
	MyFileNameFilter filter;
	private static List skillNoList;

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
		// filter = new MyFileNameFilter();
		loadSkillConfig("config.manhei");
		if (skillNoList != null) {
			for (int i = 0; i < skillNoList.size(); i++) {
				// filePath = filePath.append("skill/" + i + "/");
				// filePath = filePath.append(Constants.SKILL_DIR + i);
				// singleSkill = new File(filePath.toString());
				// singleSkillDir = singleSkill.listFiles(filter);
				int k = Integer.parseInt((String) skillNoList.get(i));
				for (int j = 0; j < k; j++) {
					// this.load(Constants.SKILL_DIR + i + "/" + j + ".png",
					// Texture.class);
					this.load("skill/" + i + "/" + j + ".png", Texture.class);
				}
				if (filePath.length() > 0) {
					filePath = filePath.delete(0, filePath.length());
				}
			}
		}
	}

	/**
	 * 读取技能配置文件
	 */
	private void loadSkillConfig(String path) {
		String s = null;
		String[] array;
		skillNoList = new ArrayList<Integer>();
		try {
			FileReader skillConfig = new FileReader(path);
			BufferedReader buff = new BufferedReader(skillConfig);
			while ((s = buff.readLine()) != null) {
				{
					array = s.split("::");
					if (array.length > 1) {
						skillNoList.add(array[1]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
