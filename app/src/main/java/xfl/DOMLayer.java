package xfl;

import xfl.libgdx.Color;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

public class DOMLayer {
  public String name;
  public Color color;

  public DOMFrame[] frames;

  public TextureAtlas textureAtlas;

  public DOMLayer(Element xml, TextureAtlas textureAtlas) {

    this.textureAtlas = textureAtlas;

    if (!xml.getTagName().equals("DOMLayer")) {
      System.out.println("wrong xml to construct DOMLayer, get "
          + xml.getTagName());
    }

    name = xml.getAttribute("name");
    color = Color.valueOf(xml.getAttribute("color").substring(1));

    NodeList layersElement = xml.getElementsByTagName("frames");
    if (layersElement != null && layersElement.getLength() > 0) {
      Element elementsElement = (Element) layersElement.item(0);
      NodeList tempDOMLayerElements = elementsElement.getElementsByTagName("DOMFrame");

      int len = tempDOMLayerElements.getLength();
      frames = new DOMFrame[len];
      for (int i = 0; i < len; i++) {
        frames[i] = new DOMFrame((Element) tempDOMLayerElements.item(i), textureAtlas);
      }
    }
  }
}