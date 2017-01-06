package com.netease.frame.flash;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.AttributeSet;
import android.view.InflateException;

public class Fanimation extends Animation {

  public FlashElements flashElement;
  private int layerIndex = 0;
  private int frameIndex = 0;
  private int elementIndex = 0;

  public Fanimation(AttributeSet attrs) {

    flashElement = new FlashElements(attrs.getAttributeIntValue(null,
        "layerNumber", 1), attrs.getAttributeFloatValue(null, "width", 0),
        attrs.getAttributeFloatValue(null, "height", 0),
        attrs.getAttributeFloatValue(null, "endTime", 0),
        attrs.getAttributeValue(null, "flashName"));
  }

  @Override
  protected void rInflate(XmlPullParser parser, AttributeSet attrs) {
    // TODO Auto-generated method stub
    int loc = 0;
    layerIndex = 0;
    for (int i = 0; i < flashElement.layersNumer; i++) {
      layerParser(parser, attrs, loc);
    }
  }

  private void layerParser(XmlPullParser parser, AttributeSet attrs, int loc) {
    try {
      while (parser.next() != XmlPullParser.START_TAG) {
      }
      final String name = parser.getName();
      if ("layer".compareToIgnoreCase(name) == 0) {
        frameIndex = 0;
        String layerName = attrs.getAttributeValue(null, "layerName");
        int framesNumber = attrs.getAttributeIntValue(null, "framesNumber", 1);
        flashElement.layers[layerIndex] = new Layer(layerName, framesNumber);
        for (int i = 0; i < framesNumber; i++) {
          frameParser(parser, attrs, loc);
        }
        layerIndex++;
        // loc++;
      }

    } catch (IOException e) {
      throw new InflateException(e);
    } catch (XmlPullParserException e) {
      throw new InflateException(e);
    }

  }

  private void frameParser(XmlPullParser parser, AttributeSet attrs, int loc) {
    try {
      while ((parser.next()) != XmlPullParser.START_TAG) {
      }

      final String name = parser.getName();
      if ("frame".compareToIgnoreCase(name) == 0) {
        elementIndex = 0;
        int elementsNumber = attrs.getAttributeIntValue(null, "elementsNumber",
            1);
        float endTime = attrs.getAttributeFloatValue(null, "endTime", 0);

        float startTime;
        if (frameIndex == 0) {
          startTime = 0;
        } else {
          startTime = flashElement.layers[layerIndex].frames[frameIndex - 1].endTime;
        }
        flashElement.layers[layerIndex].frames[frameIndex] = new Frame(
            startTime, endTime, elementsNumber);
        for (int i = 0; i < elementsNumber; i++) {
          elementParaser(parser, attrs, loc);
        }
        frameIndex++;
        // loc++;
      }

    } catch (IOException e) {
      throw new InflateException(e);
    } catch (XmlPullParserException e) {
      throw new InflateException(e);
    }

  }

  private static float alphaMultiplier = 1;

  private void elementParaser(XmlPullParser parser, AttributeSet attrs, int loc) {
    try {
      while ((parser.next()) != XmlPullParser.START_TAG) {

      }
      final String name = parser.getName();
      if ("element".compareToIgnoreCase(name) == 0) {
        Vertex4 vertex4 = new Vertex4();
        String textureName = flashElement.name + "_"
            + attrs.getAttributeValue(null, "textureName");
        vertex4.x0 = (attrs.getAttributeFloatValue(null, "x0", 0));
        vertex4.y0 = (attrs.getAttributeFloatValue(null, "y0", 0));
        vertex4.x1 = (attrs.getAttributeFloatValue(null, "x1", 0));
        vertex4.y1 = (attrs.getAttributeFloatValue(null, "y1", 0));
        vertex4.x2 = (attrs.getAttributeFloatValue(null, "x2", 0));
        vertex4.y2 = (attrs.getAttributeFloatValue(null, "y2", 0));
        vertex4.x3 = vertex4.x0 + (vertex4.x2 - vertex4.x1);
        vertex4.y3 = vertex4.y0 + (vertex4.y2 - vertex4.y1);
        alphaMultiplier = attrs.getAttributeFloatValue(null, "alphaMultiplier",
            1);
        flashElement.layers[layerIndex].frames[frameIndex].elements[elementIndex] = new Element(
            textureName, vertex4, alphaMultiplier);
        elementIndex++;
        // loc++;
      }

    } catch (IOException e) {
      throw new InflateException(e);
    } catch (XmlPullParserException e) {
      throw new InflateException(e);
    }

  }
}
