package com.netease.glsurfaceView.utils;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;

public abstract class Screen {
  protected Kuai2GLView view;

  public Screen(Kuai2GLView view) {
    this.view = view;
  }

  public abstract void initCamera();

  public abstract void update(float deltaTime);

  public abstract void present(float deltaTime, GL10 gl);

  public abstract void pause();

  public abstract void resume();

  public abstract void dispose();

  public abstract void onClick();

  public abstract void resize(float width, float height);

  public abstract void resize();

  public abstract boolean initElements();

  public Resources getResources() {
    return view.getResources();
  }

}
