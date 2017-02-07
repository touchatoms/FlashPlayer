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

//    float x1 = 0; //vertices[X1];
//    float y1 = 0;//vertices[Y1];
//    float x2 = 0;//vertices[X2];
//    float y2 = this.textureRegion.height;//vertices[Y2];
//    float x3 = this.textureRegion.width;//vertices[X3];
//    float y3 = this.textureRegion.height;//vertices[Y3];
//    float x4 = this.textureRegion.width;//vertices[X4];
//    float y4 = 0;//vertices[Y4];
//
//
//
//
//    float a = (float) instance.matrix.a;
//    float b = (float) instance.matrix.b;
//    float c = (float) instance.matrix.c;
//    float d = (float) instance.matrix.d;
//    float tx = (float)instance.matrix.tx;//- animation.W/2);
//    float ty = (float)instance.matrix.ty;// - animation.H/2);
//
//
//
//    float fx1 = (float) (x1 *  a  + y1 * c) + tx;
//    float fy1 = (float) (x1 *  b  + y1 * d) + ty;
//    float fx2 = (float) (x2 *  a  + y2 * c) + tx;
//    float fy2 = (float) (x2 *  b  + y2 * d) + ty;
//    float fx3 = (float) (x3 *  a  + y3 * c) + tx;
//    float fy3 = (float) (x3 *  b  + y3 * d) + ty;
//    float fx4 = (float) (x4 *  a  + y4 * c) + tx;
//    float fy4 = (float) (x4 *  b  + y4 * d) + ty;

//    float fx1 = -(float) 0.5 * instance.matrix.a * this.textureRegion.width + instance.matrix.tx;
//    float fy1 = -(float) 0.5 * instance.matrix.b * this.textureRegion.height + instance.matrix.ty;
//    float fx2 = -(float) 0.5 * instance.matrix.a * this.textureRegion.width + instance.matrix.tx;
//    float fy2 = (float) 0.5 * instance.matrix.b * this.textureRegion.height + instance.matrix.ty;
//    float fx3 = (float) 0.5 * instance.matrix.a * this.textureRegion.width + instance.matrix.tx;
//    float fy3 = -(float) 0.5 * instance.matrix.b * this.textureRegion.height + instance.matrix.ty;
//    float fx4 = (float) 0.5 * instance.matrix.a * this.textureRegion.width + instance.matrix.tx;
//    float fy4 = (float) 0.5 * instance.matrix.b * this.textureRegion.height + instance.matrix.ty;

//    float fx1 = -(float) 0.5 * 512 ;
//    float fy1 = -(float) 0.5 * 1024;
//    float fx2 = -(float) 0.5 * 512 ;
//    float fy2 = (float) 0.5 * 1024;
//    float fx3 = (float) 0.5 * 512 ;
//    float fy3 = -(float) 0.5 * 1024;
//    float fx4 = (float) 0.5 * 512 ;
//    float fy4 = (float) 0.5 * 1024;

    int ratio = 4;

    float x0 = ((float) 1 * 512 * instance.matrix.a + 1 * 1024 * instance.matrix.c + instance.matrix.tx);
    float y0 = -((float) 1 * 512 * instance.matrix.b + 1 * 1024 * instance.matrix.d + instance.matrix.ty);

    float x1 = (float) 1 * 512 * instance.matrix.a + 1 * 1024 * instance.matrix.c + instance.matrix.tx;
    float y1 = (float) 1 * 512 * instance.matrix.b + 1 * 1024 * instance.matrix.d + instance.matrix.ty;

    float x2 = -((float) 1 * 512 * instance.matrix.a + 1 * 1024 * instance.matrix.c + instance.matrix.tx);
    float y2 = (float) 1 * 512 * instance.matrix.b + 1 * 1024 * instance.matrix.d + instance.matrix.ty;

    float x3 = -((float) 1 * 512 * instance.matrix.a + 1 * 1024 * instance.matrix.c + instance.matrix.tx);
    float y3 = -((float) 1 * 512 * instance.matrix.b + 1 * 1024 * instance.matrix.d + instance.matrix.ty);

    Log.d(TAG, "drawElement: " + instance);

    plane.draw(this.textureRegion, x0 / ratio, y0 / ratio, x1 / ratio, y1 / ratio, x2 / ratio, y2 / ratio, x3 / ratio, y3 / ratio);
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    this.textureRegion = null;
  }

}
