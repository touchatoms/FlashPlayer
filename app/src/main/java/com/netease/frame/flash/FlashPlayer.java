package com.netease.frame.flash;

import android.util.Log;

import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;

public class FlashPlayer implements Flash {

  private static final String TAG = "FlashPlayer";

  private int state;
  public LayerPlayer[] layerPlayer;
  public float alphaMultiplier = 1f;

  boolean inScreen = true;
  boolean isEnd;

  public FlashListener listener = new FlashListener() {

    @Override
    public void playerEnd() {
    }
  };

  public final FlashElements flashElement;
  private boolean isLooping;
  TextureAtlas texture;

  private float runTime;

  public FlashPlayer(FlashElements flashElement, TextureAtlas texture) {
    this.flashElement = flashElement;
    this.texture = texture;
    isLooping = true;
    inScreen = false;
    state = PALY;
    initLayerPlayer(flashElement, texture);
  }

  public FlashPlayer(FlashElements flashElement, TextureAtlas texture,
      boolean isLooping, boolean inScreen) {
    this.flashElement = flashElement;
    this.texture = texture;
    this.isLooping = isLooping;
    this.inScreen = inScreen;
    state = PALY;
    initLayerPlayer(flashElement, texture);
  }

  private void initLayerPlayer(FlashElements flashElement, TextureAtlas texture) {
    layerPlayer = new LayerPlayer[flashElement.layersNumer];
    for (int i = 0; i < flashElement.layersNumer; i++) {
      layerPlayer[i] = new LayerPlayer(flashElement.layers[i], texture);
    }
  }

  public void drawFlash(Plane plane) {
    if (state == PALY || state == PAUSE) {
      for (int i = layerPlayer.length - 1; i >= 0; i--) {
        if (isEnd) {
          layerPlayer[i].drawLayerEnd(plane);
        } else {
          layerPlayer[i].updataLayer(runTime);
          layerPlayer[i].drawLayer(plane);
        }
      }
    }
  }

  public void updateRunTime(float deltaTime) {
    if (state == PALY) {

      if (runTime >= flashElement.endTime) {
        runTime = 0;

        if (!isLooping) {
          isEnd = true;
          if (!inScreen) {
            state = STOP;
          }
          listener.playerEnd();
        } else {
          for (int i = layerPlayer.length - 1; i >= 0; i--) {
            layerPlayer[i].reset();
          }
          listener.playerEnd();
        }
      } else {
        runTime += deltaTime;
      }
    }
  }

  public void updateRunTime(float deltaTime, float scale) {
    updateRunTime(deltaTime * scale);
  }

  @Override
  public void play() {
    state = PALY;
    isEnd = false;
  }

  public void rePlay() {
    state = PALY;
    isEnd = false;
    runTime = 0;
    for (int i = 0; i < flashElement.layersNumer; i++) {
      layerPlayer[i].reset();
    }
  }

  public void setInScreen(boolean inScreen) {
    this.inScreen = inScreen;
  }

  public boolean getInScreen(boolean inScreen) {
    return this.inScreen;
  }

  @Override
  public void setLooping(boolean isLooping) {
    // TODO Auto-generated method stub
    this.isLooping = isLooping;
  }

  @Override
  public boolean isLooping() {
    // TODO Auto-generated method stub
    return isLooping;
  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub
    if (state == PALY) {
      state = PAUSE;
    }
  }

  @Override
  public void stop() {
    state = STOP;
    runTime = 0;
  }

  @Override
  public boolean isPlaying() {
    // TODO Auto-generated method stub
    return state == PALY && (!isEnd);
  }

  @Override
  public float getTimePosition() {
    // TODO Auto-generated method stub
    return runTime;
  }

  public boolean getIsEnd() {
    return isEnd;
  }

  public float getWidth() {
    return flashElement.width;
  }

  public float getHeight() {
    return flashElement.height;
  }

  @Override
  public void setPosition(float x, float y) {
    // TODO Auto-generated method stub

  }

  @Override
  public float getX() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public float getY() {
    // TODO Auto-generated method stub
    return 0;
  }

  public void setListener(FlashListener listener) {
    this.listener = listener;
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    if (texture != null) {
      texture.disposed();
    }
    if (layerPlayer != null) {
      for (int i = layerPlayer.length - 1; i >= 0; i--) {
        layerPlayer[i].disposed();
      }
      layerPlayer = null;
    }
  }

}
