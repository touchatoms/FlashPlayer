package xfl;

import org.w3c.dom.Element;

public class Pointer {
  float x;
  float y;

  public Pointer(Element xml) {
    if (xml.getTagName() != "Pointer") {
      System.out.println("wrong xml to construct Pointer, get "
          + xml.getTagName());
    }

    x = Integer.valueOf(xml.getAttribute("x") == null ? "0" : xml.getAttribute("x"));
    y = Integer.valueOf(xml.getAttribute("y") == null ? "0" : xml.getAttribute("y"));
  }

}
