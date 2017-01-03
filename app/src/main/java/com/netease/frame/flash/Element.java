/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netease.frame.flash;

/**
 * 
 * @author pc
 */
public class Element {
  public String textureName;
  public Vertex4 vertex4;
  public float alphaMultiplier = 1;

  public Element(String textureName, Vertex4 vertex4, float alphaMultiplier) {
    this.textureName = textureName;
    this.vertex4 = vertex4;
    this.alphaMultiplier = alphaMultiplier;
  }

  public String toString() {
    return "textureName:  " + textureName;
  }
}
