/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netease.frame.flash;

/**
 * 
 * @author pc
 */
public class Frame {
  public float startTime = 0;
  public float endTime = 0;
  public int elementsNumber = 0;
  public Element[] elements;

  public Frame(float startTime, float endTime, int elementsNumber) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.elementsNumber = elementsNumber;
    elements = new Element[elementsNumber];
  }

  public String toString() {
    return "FrameElements:   " + this.elementsNumber + "  time  :  "
        + this.startTime + "  to  " + this.endTime;
  }
}
