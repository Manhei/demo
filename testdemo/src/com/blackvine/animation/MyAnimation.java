package com.blackvine.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.UserControlScreen;

public class MyAnimation {
	TextureRegion[] keyFrames;
	int[] totalDuration;
	float totalTime = 0;
	float sum = 0;

	/**
	 * 
	 * @param totalDuration
	 *            每一帧播放时长的数组
	 * @param keyFrames
	 */
	public MyAnimation(int[] totalDuration, TextureRegion[] keyFrames) {
		this.totalDuration = totalDuration;
		this.keyFrames = keyFrames;
		getTotalPlayDuration(totalDuration);
	}

	public void setTextureRegionAndDuration(int[] totalDuration,
			TextureRegion[] textureRegion) {
		this.keyFrames = textureRegion;
		this.totalDuration = totalDuration;
		getTotalPlayDuration(totalDuration);
	}

	public void getTotalPlayDuration(int[] totalDuration) {
		totalTime = 0;
		if (totalDuration != null) {
			for (int i = 0; i < totalDuration.length; i++) {
				totalTime += totalDuration[i];
			}
			totalTime = totalTime / 1000;
		}
	}

	public TextureRegion getKeyFrame(float stateTime) {
		int frameNumber = getKeyFrameIndex(stateTime);
		return keyFrames[frameNumber];
	}

	public int getKeyFrameIndex(float stateTime) {

		if (keyFrames == null || keyFrames.length == 1)
			return 0;
		float temp = stateTime % totalTime;
		int frameNumber = 0;
		sum = (float) totalDuration[frameNumber] / 1000;
		while (temp - sum > 0) {
			frameNumber++;
			if (frameNumber == keyFrames.length) {
				frameNumber--;
				break;
			}
			sum += (float) totalDuration[frameNumber] / 1000;
		}
		if (frameNumber == totalDuration.length - 1) {
			UserControlScreen.flag = false;
		}
		return frameNumber;
	}
}
