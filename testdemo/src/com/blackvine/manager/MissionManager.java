package com.blackvine.manager;
/**
 * 任务管理器 负责任务的读取 加载 添加
 * @author songjunfeng
 *
 */
public class MissionManager {
	private static MissionManager Instance = null;

	private MissionManager() {

	}

	public static MissionManager getMissionManager() {
		if (Instance == null) {
			Instance = new MissionManager();
		}
		return Instance;
	}
}
