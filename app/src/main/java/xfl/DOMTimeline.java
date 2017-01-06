package xfl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.netease.glsurfaceView.utils.TextureAtlas;

public class DOMTimeline {
  public String name;
  public int currentFrame;

  public TextureAtlas textureAtlas;
  public int layersCount;

  public DOMLayer[] layers;

  public DOMTimeline(Element xml, TextureAtlas textureAtlas ) {
    this.textureAtlas = textureAtlas;
    if (!xml.getTagName().equals("DOMTimeline")) {
      System.out.println("wrong xml to construct DOMTimeline, get "
          + xml.getTagName());
    }

    name = xml.getAttribute("name");
    currentFrame = Integer.valueOf(xml.getAttribute("currentFrame") == null ? "0" : xml.getAttribute("currentFrame"));

    NodeList layersElement = xml.getElementsByTagName("layers");
    if (layersElement != null && layersElement.getLength() > 0) {
      Element elementsElement = (Element) layersElement.item(0);
      NodeList tempDOMLayerElements = elementsElement.getElementsByTagName("DOMLayer");
      layersCount = tempDOMLayerElements.getLength();
      layers = new DOMLayer[layersCount];
      for (int i = 0; i < layersCount; i++) {
        layers[i] = new DOMLayer((Element) tempDOMLayerElements.item(i), textureAtlas);
      }
    }
  }
}
