package com.blackvine.manager;

import com.badlogic.gdx.assets.AssetManager;

/**
 * NPC的管理类
 * 
 * @author songjunfeng
 * 
 */
public class NpcManager {

	private static NpcManager Instance = null;
	AssetManager am;

	private NpcManager() {
		am = new AssetManager();
		loadNpcAsset();
	}

	/**
	 * 从配置文件加载资源
	 */
	private void loadNpcAsset() {
	}

	public static NpcManager getNpcManager() {
		if (Instance == null) {
			Instance = new NpcManager();
		}
		return Instance;
	}
}
