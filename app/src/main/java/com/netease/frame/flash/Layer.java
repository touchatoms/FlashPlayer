/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netease.frame.flash;

/**
 * 
 * @author pc
 */

public class Layer {
  public String layerName;
  public int framesNumber;
  public Frame[] frames;

  public Layer(String layerName, int framesNumber) {
    this.layerName = layerName;
    this.framesNumber = framesNumber;
    this.frames = new Frame[framesNumber];
  }

  public String toString() {
    return "layerName : " + this.layerName + "  framesNumber "
        + this.framesNumber;
  }
}
