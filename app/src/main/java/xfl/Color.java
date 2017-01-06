package xfl;

import org.w3c.dom.Element;

public class Color {

  public Color(Element xml) {
    if (!xml.getTagName().equals("Color")) {
      System.out.println("wrong xml to construct Color, get "
          + xml.getTagName());
    }

    alphaMultiplier = 1d;
    String str = xml.getAttribute("alphaMultiplier");
    if (str != null) {
      alphaMultiplier = Double.parseDouble(str);
    }
  }

  public Color() {
    alphaMultiplier = 1d;
  }

  public double alphaMultiplier;
}
