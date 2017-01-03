package com.netease.glsurfaceView.utils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class OpenGLRenderer implements Renderer {

  private Kuai2GLView view;

  public OpenGLRenderer(Kuai2GLView flash) {
    view = flash;
  }

  float deltaTime;
  long lastFrameTime;

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    gl.glClearColor(0f, 0f, 0f, 0f);
    gl.glShadeModel(GL10.GL_SMOOTH);
    gl.glClearDepthf(1.0f);
    gl.glEnable(GL10.GL_DEPTH_TEST);
    gl.glDepthFunc(GL10.GL_LEQUAL);
    gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
  }

  @Override
  public void onDrawFrame(GL10 gl) {
    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    gl.glLoadIdentity();
    long time = System.nanoTime();
    deltaTime = (time - lastFrameTime) / 1000000000.0f;
    lastFrameTime = time;
    view.draw(deltaTime, gl);
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    gl.glViewport(0, 0, width, height);
    gl.glMatrixMode(GL10.GL_PROJECTION);
    gl.glLoadIdentity();

    deltaTime = 0;
    lastFrameTime = System.nanoTime();
    // GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
    // 1000.0f);
    GLU.gluOrtho2D(gl, 0, width, 0, height);
    gl.glMatrixMode(GL10.GL_MODELVIEW);
    gl.glLoadIdentity();
    view.setSize(width, height);
    // view.resize(width, height);
  }

}
