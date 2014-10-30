package com.blackvine.drawmap;

import com.badlogic.gdx.assets.AssetManager;
import com.blackvine.manager.PieceManager;
import com.me.mygdxgame.GameScreen;

/**
 * 世界地图类 Created by IceChen on 14-8-27.
 */
public class WorldMap {

	private static WorldMap Instance = null;
	private static final int width = 10000; // 世界地图宽度
	private static final int height = 10000; // 世界地图高度
	private static final int piece_map_width = 64; // 地图块宽度
	private static final int piece_map_height = 4; // 地图块高度
	public PieceManager pieceManager;
	public AssetManager am;
	public boolean isLoding = true;

	private String mapFile[]; // 地图文件列表

	private WorldMap() {
		pieceManager = PieceManager.getPieceManager();
		am = new AssetManager();
		load();
	}

	public void updateAssetManager() {
		if (am.update()) {
			isLoding = false;
		}
	}

	// 预读大地图信息
	public void load() {

	}

	/**
	 * 读取某块地图文件 基本载入地图函数，此函数可重载不同的参数，最终都将转化为id，调用此函数载入地图
	 * 
	 * @param id
	 */

	// public PieceMap getPieceMap(int id) {
	// PieceMap map = new PieceMap();
	// /**
	// * 载入相应的地图文件(mapFile[id])，赋值给map
	// */
	// return map;
	// }

	/**
	 * 世界地图坐标转换为屏幕绘制坐标
	 * 
	 * @param xInWorld
	 *            世界地图X轴坐标
	 * @return xInScreen 屏幕绘制X轴坐标
	 */

	public static int worldXtoScreenX(int xInWorld) {
		int xInScreen = 0;
		xInScreen = xInWorld - ScreenMap.getInstance().getX_InWorld();
		return xInScreen;
	}

	/**
	 * 世界地图坐标转换为屏幕绘制坐标
	 * 
	 * @param yInWorld
	 *            世界地图Y轴坐标
	 * @param thisHeight
	 *            绘制物品自身高度
	 * @return yInScreen 屏幕绘制Y轴坐标
	 */
	public static int worldYtoScreenY(int yInWorld, int thisHeight) {
		int yInScreen = 0;
		yInScreen = ScreenMap.getInstance().getY_InWorld() - yInWorld
				- thisHeight + GameScreen.ScreenHeight;
		return yInScreen;
	}

	public static int getPieceMap_width() {
		return piece_map_width;
	}

	public static int getPieceMap_height() {
		return piece_map_height;
	}

	public static WorldMap getWorldMap() {
		if (Instance == null) {
			Instance = new WorldMap();
		}
		return Instance;
	}

	public void dispose() {
		am.dispose();
	}

}
