package xfl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

import xfl.player.PlayerConst;

public class DOMFrame {
  private static final String TAG = "DOMFrame";

  public int index;
  public int duration;
  public int keyMode;
  public String tweenType;
  public int acceleration;
  public int instanceCount;
  public TextureAtlas textureAtlas;

  public DOMSymbolInstance[] elements;

  public DOMFrame(Element xml, TextureAtlas textureAtlas) {
    this.textureAtlas = textureAtlas;
    if (!xml.getTagName().equals("DOMFrame")) {
      System.out.println("wrong xml to construct DOMFrame, get "
          + xml.getTagName());
    }

    String str;
    str = xml.getAttribute("index");
    if (str != null && str != "") {
      index = Integer.valueOf(str);
    }
    str = xml.getAttribute("duration");
    if (str != null && str != "") {
      duration = Integer.valueOf(str);
    }
    str = xml.getAttribute("keyMode");
    if (str != null && str != "") {
      keyMode = Integer.valueOf(str);
    }

    tweenType = xml.getAttribute("tweenType");

    if (xml.getAttribute("acceleration") != null && xml.getAttribute("acceleration") != "") {
      acceleration = Integer.valueOf(xml.getAttribute("acceleration"));
    }

    NodeList elementsList = xml.getElementsByTagName("elements");
    if (elementsList != null && elementsList.getLength() > 0) {
      Element elementsElement = (Element) elementsList.item(0);

      NodeList instanceList = elementsElement.getElementsByTagName("DOMSymbolInstance");

      int len = instanceList.getLength();
      instanceCount = len;
      elements = new DOMSymbolInstance[len];
      for (int i = 0; i < len; i++) {
        elements[i] = new DOMSymbolInstance((Element) instanceList.item(i), textureAtlas);
      }
    }

  }

  public float getFrameTime() {
    return (index + duration) * PlayerConst.FRAME_TIME;
  }

}
