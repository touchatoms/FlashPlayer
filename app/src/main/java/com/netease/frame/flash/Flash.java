package com.netease.frame.flash;

public interface Flash {
  int PALY = 0;
  int PAUSE = 1;
  int STOP = 2;

  void play();

  void pause();

  void stop();

  boolean isPlaying();

  void setLooping(boolean isLooping);

  boolean isLooping();

  float getTimePosition();

  void setPosition(float x, float y);

  float getX();

  float getY();

  void disposed();
}
