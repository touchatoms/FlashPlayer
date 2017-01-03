package com.netease.glsurfaceView.utils;

public class Vector2 {

  public float x = 0;
  public float y = 0;

  public Vector2() {
    x = 0;
    y = 0;
  }

  public Vector2(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public Vector2(Vector2 v) {
    x = v.x;
    y = v.y;
  }

  public float lenth() {
    return (float) Math.sqrt(x * x + y * y);
  }

  public Vector2 nor() {
    float len = lenth();
    if (len != 0) {
      x /= len;
      y /= len;
    }
    return this;
  }

  public float dot(Vector2 v) {
    return x * v.x + y * v.y;
  }

  public Vector2 mul(float scalar) {
    x *= scalar;
    y *= scalar;

    return this;
  }

  public float cross(Vector2 v) {
    return x * v.y - y * v.x;
  }

  public Vector2 add(Vector2 v) {
    x += v.x;
    y += v.y;
    return this;
  }

  public Vector2 add(float x, float y) {
    this.x += x;
    this.y += y;
    return this;
  }

  public Vector2 sub(Vector2 v) {
    x -= v.x;
    y -= v.y;
    return this;
  }

  public Vector2 sub(float x, float y) {
    this.x -= x;
    this.y -= y;
    return this;
  }

  public Vector2 set(float x, float y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Vector2 set(Vector2 v) {
    x = v.x;
    y = v.y;
    return this;
  }

  public float angle() {
    float angle = (float) Math.atan2(y, x) * NumberUtils.RADIANS_TO_DEGREES;
    if (angle < 0) {
      angle += 360;
    }
    return angle;
  }

}
