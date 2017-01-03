//CHECKSTYLE:OFF
package com.netease.glsurfaceView.utils;

public class Action {
  public Action(float x) {
    this(x, 1, "linear");
  }

  public Action(float x, float duration, String interpolation) {
    this.x = x;
    this.duration = duration;
    this.interpolation = Interpolation.getInterpolation(interpolation);
  }

  float targetPosition;
  float startPosition;

  boolean amiOver_no;
  float x;
  float timer;
  Interpolation interpolation;
  public float duration = 2f;

  public void calValue(float deltaTime) {
    if (amiOver_no) {
      x = targetPosition;
      x -= startPosition;
      mul(interpolation.apply(Math.min(1, timer / duration)));
      x += startPosition;
      timer += deltaTime;
      if (timer > duration) {
        amiOver_no = false;
        x = targetPosition;
        timer = 0;
      }
    }
  }

  public void setInterpolation(String interpolation) {
    this.interpolation = Interpolation.getInterpolation(interpolation);
  }

  public void setDuration(float duration) {
    this.duration = duration;
  }

  private void mul(float scale) {
    x *= scale;
  }

  public void setTo(float deltaX) {
    startPosition = x;
    targetPosition = deltaX;
    amiOver_no = true;
    timer = 0;
  }

  public void formAToB(float a, float b) {
    x = a;
    startPosition = a;
    targetPosition = b;
    amiOver_no = true;
    timer = 0;
  }

  public void formAToB(float a, float b, float duration) {
    x = a;
    startPosition = a;
    targetPosition = b;
    amiOver_no = true;
    timer = 0;
    this.duration = duration;
  }

  public boolean getOver() {
    return !amiOver_no;
  }

  public float getValue() {
    return x;
  }
}
