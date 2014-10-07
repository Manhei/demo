package com.blackvine.drawmap;



/**
 * 世界地图类
 * Created by IceChen on 14-8-27.
 */
public class WordMap {
    private final int width = 10000;                //世界地图宽度
    private final int height = 10000;               //世界地图高度
    private final int map_piece_width = 100;        //地图块宽度
    private final int map_piece_height = 100;       //地图块高度

    private String mapFile[];                       //地图文件列表


    public WordMap() {
        load();
    }

    //预读大地图信息
    public void load() {
    }

    /**
     *  读取某块地图文件
     *  基本载入地图函数，此函数可重载不同的参数，最终都将转化为id，调用此函数载入地图
     *  @param id
     */

    public PieceMap getPieceMap(int id) {
        PieceMap map = new PieceMap(map_piece_width, map_piece_height);
        /**
         * 载入相应的地图文件(mapFile[id])，赋值给map
         */
        return map;
    }

}
