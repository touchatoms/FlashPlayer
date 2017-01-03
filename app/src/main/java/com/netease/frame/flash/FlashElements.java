/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netease.frame.flash;

public class FlashElements {
  public int layersNumer = 0;
  public Layer[] layers;
  public float width = 0;
  public float height = 0;
  public float endTime = 0;
  public String name;

  public int state;

  public FlashElements(int layersNumerber, float width, float height,
      float endTime, String name) {
    this.layersNumer = layersNumerber;
    this.layers = new Layer[layersNumerber];
    this.width = width;
    this.height = height;
    this.endTime = endTime;
    this.name = name;
  }

  public String toString() {
    return "Name : " + this.name + "  layerNumber  " + this.layersNumer
        + "  Time :   " + this.endTime + "  Width   " + this.width
        + "  Height  " + this.height;
  }

}
