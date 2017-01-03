package com.netease.glsurfaceView.utils;

import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class Texture implements Disposed {
  int texturedId = -1;
  Bitmap bitmap;
  GL10 gl;
  static int maxCapacity = 1;

  private static final IntBuffer BUFFER;

  static {
    BUFFER = IntBuffer.allocate(maxCapacity);
    BUFFER.limit(maxCapacity);
  }

  // public static HashMap<Bitmap, Texture> textures = new HashMap<Bitmap,
  // Texture>();

  public Texture(Bitmap bitmap, GL10 gl) {
    texturedId = createGLHandle(gl);
    this.bitmap = bitmap;
    load(gl);
  }

  public Texture(Bitmap bitmap) {
    texturedId = -1;
    this.bitmap = bitmap;
  }

  public void create(GL10 gl) {
    if (texturedId == -1) {
      texturedId = createGLHandle(gl);
      load(gl);
      this.gl = gl;
    }
  }

  public static Texture creatTexture(Bitmap bitmap, GL10 gl) {
    Texture tex = new Texture(bitmap, gl);
    return tex;
  }

  public static int createGLHandle(GL10 gl) {
    BUFFER.position(0);
    gl.glGenTextures(1, BUFFER);
    System.out.println("Tex   " + BUFFER.get(0));
    return BUFFER.get(0);
  }

  public void deleteTextures(GL10 gl) {
    if (texturedId == 0 || texturedId == -1 || gl == null) {
      return;
    }
    BUFFER.put(0, texturedId);
    gl.glDeleteTextures(texturedId, BUFFER);
  }

  private void load(GL10 gl) {
    gl.glBindTexture(GL10.GL_TEXTURE_2D, texturedId);

    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
        GL10.GL_LINEAR);
    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
        GL10.GL_LINEAR);

    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
        GL10.GL_CLAMP_TO_EDGE);
    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
        GL10.GL_CLAMP_TO_EDGE);

    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    try {
      deleteTextures(gl);
    } catch (Exception e) {

    }
    if (!bitmap.isRecycled()) {
      bitmap.recycle();
    }
  }

}
