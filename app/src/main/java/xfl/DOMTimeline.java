package xfl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

public class DOMTimeline {
  public String name;
  public int currentFrame;

  public TextureAtlas textureAtlas;

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

      int len = tempDOMLayerElements.getLength();
      layers = new DOMLayer[len];
      for (int i = 0; i < len; i++) {
        layers[i] = new DOMLayer((Element) tempDOMLayerElements.item(i), textureAtlas);
      }
    }
  }
}
