package com.blackvine.drawmap;

import android.test.UiThreadTest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackvine.manager.PieceManager;

/**
 * Created by IceChen on 14/10/23.
 */
public class NinePieceMap {
	private static NinePieceMap Instance = null;
	private final int col = 3;
	private final int row = 3;
	private int pieceX_InWorld; // 地图块在世界地图中的X坐标 （坐上为0,0）
	private int pieceY_InWorld; // 地图块在世界地图中的Y坐标 （坐上为0,0）
	PieceMap[] map = new PieceMap[col * row];
	private int width;
	private int height;

<<<<<<< HEAD
	private NinePieceMap() {
		initPiece();
		width = PieceMap.getWidth() * col;
		height = PieceMap.getHeight() * row;
	}

	private void initPiece() {
		pieceX_InWorld = ScreenMap.getInstance().getX_InWorld()
				/ PieceMap.getWidth() - 1;
		pieceY_InWorld = ScreenMap.getInstance().getY_InWorld()
				/ PieceMap.getHeight() - 1;
		PieceManager.getPieceManager()
				.load(pieceX_InWorld, pieceY_InWorld, col);
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		for (int i = 0; i < col * row; i++) {
			if (map[i] != null) {
				map[i].draw(batch, parentAlpha);
			}
		}
		if (ScreenMap.getInstance().getX_InWorld() / PieceMap.getWidth() - 1 != pieceX_InWorld
				|| ScreenMap.getInstance().getY_InWorld()
						/ PieceMap.getHeight() - 1 != pieceY_InWorld) {
			updatePiece();
		}
	}

	public void updatePiece() {
		pieceX_InWorld = ScreenMap.getInstance().getX_InWorld()
				/ PieceMap.getWidth() - 1;
		pieceY_InWorld = ScreenMap.getInstance().getY_InWorld()
				/ PieceMap.getHeight() - 1;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (pieceX_InWorld + i > -1
						&& pieceX_InWorld + i < WorldMap.getPieceMap_width()
						&& pieceY_InWorld + j > -1
						&& pieceY_InWorld + j < WorldMap.getPieceMap_height()) {
					map[i * row + j] = new PieceMap(pieceX_InWorld + i,
							pieceY_InWorld + j);
				} else {
					map[i * row + j] = null;
				}
			}
		}
		System.out.print("(" + pieceX_InWorld + "," + pieceY_InWorld + ")");
		preload();
	}

	public int getWidth() {
		return width;
	}
=======
    private NinePieceMap() {
        initPiece();
        width = PieceMap.getWidth() * col;
        height = PieceMap.getHeight() * row;
    }

    private void initPiece() {
        piece_x_in_worldmap = ScreenMap.getInstance().getX_in_wordmap() / PieceMap.getWidth() - 1;
        piece_y_in_worldmap = ScreenMap.getInstance().getY_in_wordmap() / PieceMap.getHeight() - 1;
        PieceManager.getPieceManager().load(piece_x_in_worldmap, piece_y_in_worldmap, col);
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        for (int i = 0; i < col * row; i++) {
            if (map[i] != null) {
                map[i].draw(batch, parentAlpha);
            }
        }
        if (ScreenMap.getInstance().getX_in_wordmap() / PieceMap.getWidth() - 1 != piece_x_in_worldmap
                || ScreenMap.getInstance().getY_in_wordmap() / PieceMap.getHeight() - 1 != piece_y_in_worldmap)  {
            updatePiece();
        }
    }

    public void updatePiece() {
        piece_x_in_worldmap = ScreenMap.getInstance().getX_in_wordmap() / PieceMap.getWidth() - 1;
        piece_y_in_worldmap = ScreenMap.getInstance().getY_in_wordmap() / PieceMap.getHeight() - 1;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (piece_x_in_worldmap + i > -1 && piece_x_in_worldmap + i < WorldMap.getMap_piece_width()
                        && piece_y_in_worldmap + j > -1 && piece_y_in_worldmap + j < WorldMap.getMap_piece_height()) {
                    map[i*row+j] = new PieceMap(piece_x_in_worldmap + i, piece_y_in_worldmap + j);
                }
                else {
                    map[i*row+j] = null;
                }
            }
        }
        System.out.print("(" + piece_x_in_worldmap + "," + piece_y_in_worldmap + ")");
        preload();
    }
>>>>>>> de8a6072de01bca5b50c35c66440fee1acaccdb0

	public int getHeight() {
		return height;
	}

	public int getpieceX_InWorld() {
		return pieceX_InWorld;
	}

	public int getpieceY_InWorld() {
		return pieceY_InWorld;
	}

	public static NinePieceMap getNinePieceMap() {
		if (Instance == null) {
			Instance = new NinePieceMap();
		}
		return Instance;
	}

	public void preload() {
		PieceManager.getPieceManager().load(pieceX_InWorld - 1,
				pieceY_InWorld - 1, col + 2);
	}
}
