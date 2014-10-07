package com.blackvine.drawmap;

/**
 * Created by IceChen on 14-8-29.
 */
public class PieceMap {

    private final int width;
    private final int height;

    private String mapSurfaceFile;      //地表层文件
    private String mapBuildFile;        //建筑层文件
    private int mapSurface[][];         //地表层信息
    private int mapBuild[][];           //建筑层信息

    public PieceMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
