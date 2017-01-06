package xfl.player;

import android.util.Log;

import com.netease.glsurfaceView.utils.Disposed;
import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

import xfl.DOMSymbolInstance;

public class DOMSymbolInstancePlayer implements Disposed {

  private static final String TAG = "DOMSymbolInstancePlayer";

  public DOMSymbolInstance instance;
  public TextureRegion textureRegion;

  public DOMSymbolInstancePlayer(DOMSymbolInstance instance, TextureAtlas texture) {
    this.instance = instance;
    this.textureRegion = texture.findRegion(instance.libraryItemName);
  }

  public void drawElement(Plane plane) {

    float x0 = instance.matrix.a;
    float y0 = instance.matrix.b;
    float x1 = instance.matrix.c;
    float y1 = instance.matrix.d;
    float x2 = instance.matrix.tx;//- animation.W/2);
    float y2 = instance.matrix.ty;// - animation.H/2);
    float x3 = x0 + (x2 - x1);
    float y3 = y0 + (y2 - y1);

    Log.d(TAG, "drawElement: " + instance);

    plane.draw(this.textureRegion, x0, y0, x1, y1, x2, y2, x3, y3);
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    this.textureRegion = null;
  }

}
