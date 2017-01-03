package com.netease.glsurfaceView.utils;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class Kuai2GLView extends GLSurfaceView implements Disposed {
  public Screen mScreen;
  public float w = 1;
  public float h = 1;

  public Kuai2GLView(Context context) {
    super(context);
    set();
  }

  public Kuai2GLView(Context context, AttributeSet attrs) {
    super(context, attrs);
    set();
  }

  public interface OnStopListener {
    void onStop(Kuai2GLView view);

    void onStartLocate(Kuai2GLView view);
  }

  OnStopListener onStopListener;

  public OnStopListener getOnStopListener() {
    return onStopListener;
  }

  public void setOnStopListener(OnStopListener onStopListener) {
    this.onStopListener = onStopListener;
  }

  public void draw(float deltaTime, GL10 gl) {
    if (mScreen != null) {
      mScreen.update(deltaTime);
      mScreen.present(deltaTime, gl);
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public void set() {
    setZOrderOnTop(true);
    this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
    OpenGLRenderer renderer = new OpenGLRenderer(this);
    setRenderer(renderer);
    getHolder().setFormat(PixelFormat.TRANSLUCENT);
  }

  public void resize(float width, float height) {
    if (mScreen != null) {
      mScreen.resize(width, height);
    }
  }

  public void resize() {
    if (mScreen != null) {
      mScreen.resize();
    }
  }

  public void resume() {
    if (mScreen != null) {
      mScreen.resume();
    }
  }

  public void setSrceen(Screen screen) {
    if (mScreen != null) {
      mScreen.dispose();
    }
    mScreen = screen;
  }

  public void setSize(float width, float heigth) {
    w = width;
    h = heigth;
  }

  public boolean initElements() {
    if (mScreen != null) {
      return mScreen.initElements();
    }
    return false;
  }

  public void pause() {
    if (mScreen != null) {
      mScreen.pause();
    }
  }

  @Override
  public void disposed() {
    if (mScreen != null) {
      mScreen.dispose();
    }

  }

  @Override
  public void onResume() {
    super.onResume();
    resume();
  }

  @Override
  public void onPause() {
    super.onPause();
    pause();
  }

}
