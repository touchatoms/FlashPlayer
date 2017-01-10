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

    float x1 = 0; //vertices[X1];
    float y1 = 0;//vertices[Y1];
    float x2 = 0;//vertices[X2];
    float y2 = this.textureRegion.height;//vertices[Y2];
    float x3 = this.textureRegion.width;//vertices[X3];
    float y3 = this.textureRegion.height;//vertices[Y3];
    float x4 = this.textureRegion.width;//vertices[X4];
    float y4 = 0;//vertices[Y4];




    float a = (float) instance.matrix.a;
    float b = (float) instance.matrix.b;
    float c = (float) instance.matrix.c;
    float d = (float) instance.matrix.d;
    float tx = (float)instance.matrix.tx;//- animation.W/2);
    float ty = (float)instance.matrix.ty;// - animation.H/2);



    float fx1 = (float) (x1 *  a  + y1 * c) + tx;
    float fy1 = (float) (x1 *  b  + y1 * d) + ty;
    float fx2 = (float) (x2 *  a  + y2 * c) + tx;
    float fy2 = (float) (x2 *  b  + y2 * d) + ty;
    float fx3 = (float) (x3 *  a  + y3 * c) + tx;
    float fy3 = (float) (x3 *  b  + y3 * d) + ty;
    float fx4 = (float) (x4 *  a  + y4 * c) + tx;
    float fy4 = (float) (x4 *  b  + y4 * d) + ty;


//    float x0 = instance.matrix.a;
//    float x1 = instance.matrix.b;
//    float x2 = instance.matrix.c;
//    float y0 = instance.matrix.d;
//    float y1 = instance.matrix.tx;//- animation.W/2);
//    float y2 = instance.matrix.ty;// - animation.H/2);
//    float x3 = x0 + (x2 - x1);
//    float y3 = y0 + (y2 - y1);

    Log.d(TAG, "drawElement: " + instance);

//    plane.draw(this.textureRegion, x0, y0, x1, y1, x2, y2, x3, y3);
    plane.draw(this.textureRegion, fx1, fy1, fx2, fy2, fx3, fy3, fx4, fy4);
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    this.textureRegion = null;
  }

}
