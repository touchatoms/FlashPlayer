package com.netease.frame.flash;

import com.netease.glsurfaceView.utils.Disposed;
import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;

public class FramePlayer implements Disposed {
  private ElementPlayer[] elementPlayers;

  public FramePlayer(Frame frame, TextureAtlas texture) {
    elementPlayers = new ElementPlayer[frame.elementsNumber];
    for (int i = 0; i < frame.elementsNumber; i++) {
      elementPlayers[i] = new ElementPlayer(frame.elements[i], texture);
    }
  }

  public void drawFrame(Plane plane) {
    for (int i = elementPlayers.length - 1; i >= 0; i--) {
      elementPlayers[i].drawElement(plane);
    }
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    if (elementPlayers != null) {
      for (int i = elementPlayers.length - 1; i >= 0; i--) {
        elementPlayers[i].disposed();
      }
      elementPlayers = null;
    }
  }
}
