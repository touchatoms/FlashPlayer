package com.touchatoms.training.testopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ClearGLSurfaceView view = new ClearGLSurfaceView(this);


    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
        (FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);

    addContentView(view, params);

    ClearGLSurfaceView view1 = new ClearGLSurfaceView(this);

    FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams
        (FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
    addContentView(view1, params1);
  }

  class ClearGLSurfaceView extends GLSurfaceView {

    ClearRenderer mRenderer;

    public ClearGLSurfaceView(Context context) {
      super(context);

      setZOrderOnTop(true);
      setEGLConfigChooser(8, 8, 8, 8, 16, 0);
      getHolder().setFormat(PixelFormat.TRANSLUCENT);

      mRenderer = new ClearRenderer();
      setRenderer(mRenderer);
    }
  }

  class ClearRenderer implements GLSurfaceView.Renderer {

    FloatBuffer verticesBuffer;

    public void onSurfaceCreated(GL10 gl, EGLConfig config) { // Do nothing special.
      ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * 2 * 4); // 3:顶点数 2:维数,x和y 4:一个float型占4个字节
      byteBuffer.order(ByteOrder.nativeOrder());
      verticesBuffer = byteBuffer.asFloatBuffer();
      verticesBuffer.put(new float[] { 0, 0,
          320, 0,
          160, 480 });
      verticesBuffer.flip();
    }

    public void onSurfaceChanged(GL10 gl, int w, int h) {
      gl.glViewport(0, 0, w, h);
    }

    public void onDrawFrame(GL10 gl) {
      gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
      gl.glViewport(0, 0, 320, 480);
      gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
      gl.glMatrixMode(GL10.GL_PROJECTION);
      gl.glLoadIdentity();
      gl.glOrthof(0, 320, 0, 480, 1, -1);

      gl.glColor4f(1, 0, 0, 1);
      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//      verticesBuffer.clear();
//      verticesBuffer.put(new float[] { 0, 0,
//          320 + (int)(Math.random() * 100), 0,
//          160, 480 });

      gl.glVertexPointer(2, GL10.GL_FLOAT, 0, verticesBuffer);
      gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
    }
  }
}