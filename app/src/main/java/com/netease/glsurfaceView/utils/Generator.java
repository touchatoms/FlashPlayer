package com.netease.glsurfaceView.utils;

public class Generator {
  public static void genNoSame(int genNumber, int totalNumber, int[] results) {
    for (int i = 0; i < totalNumber; i++) {
      results[i] = i;
    }

    for (int i = 0; i < genNumber; i++) {
      int temp = (int) (Math.random() * (totalNumber - i)) + i;
      int temp2 = results[i];
      results[i] = results[temp];
      results[temp] = temp2;
    }
  }

  public static void order(int number, int[] results) {
    for (int i = number - 1; i > 0; i--) {
      for (int j = i - 1; j >= 0; j--) {
        if (results[i] < results[j]) {
          int temp = results[i];
          results[i] = results[j];
          results[j] = temp;
        }
      }
    }
  }

  public static void genSame(int genNumber, int totalNumber, int[] results) {
    for (int i = 0; i < totalNumber; i++) {
      results[i] = i;
    }
    int temp = (int) (Math.random() * (totalNumber));
    for (int i = 0; i < genNumber; i++) {
      results[i] = temp;
    }
  }

  public static void genNoSameWithOrder(int genNumber, int totalNumber,
      int[] results) {
    genNoSame(genNumber, totalNumber, results);
    order(genNumber, results);
  }

  public static void genNumber(int genNumber, int totalNumber, int[] results) {
    for (int i = 0; i < genNumber; i++) {
      results[i] = (int) (Math.random() * (totalNumber));
    }
  }
}
