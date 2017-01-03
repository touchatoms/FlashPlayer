package com.netease.glsurfaceView.utils;

public class Action2 {

  Vector2 value;

  public Action2(float x, float y) {

    value = new Vector2(x, y);
    interpolation = Interpolation.getInterpolation("circleOut");
  }

  public Action2(float x, float y, String interpolation) {

    value = new Vector2(x, y);
    this.interpolation = Interpolation.getInterpolation(interpolation);
  }

  public Action2(Vector2 startposition, float duration, String interpolation) {
    value = new Vector2(startposition);
    this.duration = duration;
    this.interpolation = Interpolation.getInterpolation(interpolation);
  }

  public Action2(float duration, String interpolation) {
    value = new Vector2(0, 0);

    this.duration = duration;
    this.interpolation = Interpolation.getInterpolation(interpolation);
  }

  Vector2 targetPosition = new Vector2();
  Vector2 startPosition = new Vector2();

  boolean amiOverNo = false;
  float timer;
  Interpolation interpolation = Interpolation.getInterpolation("circleOut");
  public float duration = 1f;

  public void calValue(float deltaTime) {
    if (amiOverNo) {
      value.set(targetPosition).sub(startPosition);
      value.mul(interpolation.apply(Math.min(1, timer / duration)));
      value.add(startPosition);
      if (timer > duration) {
        amiOverNo = false;
        timer = 0;
        value.set(targetPosition);
      } else {
        timer += deltaTime;
      }
    }
  }

  public boolean getOver() {
    return !amiOverNo;
  }

  public Vector2 getValue() {
    return value;
  }

  public void setMoveDistance(float deltaX, float deltaY) {
    startPosition.set(value);
    targetPosition.set(value).add(deltaX, deltaY);
    amiOverNo = true;
    timer = 0;
  }

  public void aToB(Vector2 startPosition, Vector2 targetPosition) {
    value.set(startPosition);
    this.startPosition.set(startPosition);
    this.targetPosition.set(targetPosition);
    amiOverNo = true;
    timer = 0;
  }

  public void aToB(Vector2 startPosition, Vector2 targetPosition, float duration) {
    value.set(startPosition);
    this.startPosition.set(startPosition);
    this.targetPosition.set(targetPosition);
    amiOverNo = true;
    timer = 0;
    this.duration = duration;
  }

  public void setDuration(float duration) {
    this.duration = duration;
  }

}
