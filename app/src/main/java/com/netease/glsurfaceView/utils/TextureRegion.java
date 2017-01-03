package com.netease.glsurfaceView.utils;

public class TextureRegion {
  public int index;
  public String name;
  public float offsetX;
  public float offsetY;
  public int originalWidth;
  public int originalHeight;
  public boolean rotate;
  public int left;
  public int top;
  public int width;
  public int height;
  public boolean flip;
  public int[] splits;
  public int[] pads;

  public float u, v, u2, v2;

  public final Texture mTexture;

  public TextureRegion(Texture texture) {
    mTexture = texture;
  }

  @Override
  public String toString() {
    return "name :" + name + " left :" + left + "  top :" + top + " width: "
        + width + " height: " + height;
  }

  public void set(float invwidth, float invheight) {
    u = left * invwidth;
    v = top * invheight;
    u2 = u + width * invwidth;
    v2 = v + height * invheight;
  }

  public Texture getTexture() {
    return mTexture;
  }
}
