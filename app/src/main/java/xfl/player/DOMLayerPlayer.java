package xfl.player;

import com.netease.glsurfaceView.utils.Disposed;
import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;

import xfl.DOMLayer;

public class DOMLayerPlayer implements Disposed {
  private int frameIndex = 0;
  public DOMLayer layer;
  private DOMFramePlayer[] framePlayers;

  public DOMLayerPlayer(DOMLayer layer, TextureAtlas texture) {
    this.layer = layer;
    framePlayers = new DOMFramePlayer[layer.framesCount];
    for (int i = 0; i < layer.framesCount; i++) {
      framePlayers[i] = new DOMFramePlayer(layer.frames[i], texture);
    }
  }

  public void drawLayer(Plane plane) {
    framePlayers[frameIndex].drawFrame(plane);
  }

  public void drawLayerEnd(Plane plane) {
    framePlayers[layer.framesCount - 1].drawFrame(plane);
  }

  public void updataLayer(float runTime) {

    float frameTime = (layer.frames[frameIndex].index + layer.frames[frameIndex].duration) * PlayerConst.FRAME_TIME;

    if (runTime >= frameTime) {
      frameIndex++;
    }
    if (frameIndex >= layer.framesCount) {
      frameIndex = layer.framesCount - 1;
      System.out.println("frame  :" + frameIndex);
    }
  }

  public void reset() {
    frameIndex = 0;
  }

  @Override
  public void disposed() {
    if (framePlayers != null) {
      for (int i = 0; i < layer.framesCount; i++) {
        framePlayers[i].disposed();
      }
      framePlayers = null;
    }

  }
}
