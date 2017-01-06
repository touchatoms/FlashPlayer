package xfl.player;

import com.netease.frame.flash.ElementPlayer;
import com.netease.glsurfaceView.utils.Disposed;
import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;

import xfl.DOMSymbolInstance;

public class DOMFramePlayer implements Disposed {
  private DOMSymbolInstancePlayer[] elementPlayers;

  public DOMFramePlayer(DOMSymbolInstance frame, TextureAtlas texture) {
    elementPlayers = new ElementPlayer[frame.elementsNumber];
    for (int i = 0; i < frame.elementsNumber; i++) {
      elementPlayers[i] = new DOMSymbolInstancePlayer(frame.elements[i], texture);
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
