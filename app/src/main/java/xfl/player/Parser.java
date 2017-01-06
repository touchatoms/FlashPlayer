package xfl.player;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

import xfl.DOMTimeline;
import xfl.Xfl;

public class Parser {

  public Xfl parse(InputStream stream, TextureAtlas textureAtlas) throws IOException {
    //得到 DocumentBuilderFactory 对象, 由该对象可以得到 DocumentBuilder 对象
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      //得到DocumentBuilder对象
      DocumentBuilder builder = factory.newDocumentBuilder();
      //得到代表整个xml的Document对象
      Document document = builder.parse(stream);
      //得到 "根节点"
      Element root = document.getDocumentElement();
      Xfl ret = null;
      NodeList timelineList = root.getElementsByTagName("timeline");
      if (timelineList != null && timelineList.getLength() > 0) {
        Element tempDOMTimelineElement = (Element) timelineList.item(0);
        NodeList tempDOMTimelineList = tempDOMTimelineElement.getElementsByTagName("DOMTimeline");
        if (tempDOMTimelineList != null && tempDOMTimelineList.getLength() > 0) {
          ret = new Xfl();
          ret.timelines = new DOMTimeline((Element) tempDOMTimelineList.item(0), textureAtlas);
        }
      }
      return ret;
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
