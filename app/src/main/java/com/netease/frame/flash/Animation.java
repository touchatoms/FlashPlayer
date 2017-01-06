package com.netease.frame.flash;

import java.io.IOException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;

public abstract class Animation {

  private static final String TAG = "Animation";

  private static HashMap<Integer, Animation> animationManager = new HashMap<Integer, Animation>();

  public static Animation createAnimationFromResouce(int resId,
      Resources resources) {
    Animation result = animationManager.get(resId);
    if (result == null) {
      result = inflate(resources.getXml(resId));
      animationManager.put(resId, result);
    }
    return result;
  }

  public static void removeAmination(int resId) {
    animationManager.remove(resId);
  }

  public static Fanimation getFanimation(int resId) {
    return (Fanimation) animationManager.get(resId);
  }

  private static Animation inflate(XmlPullParser parser) {
    Animation result = null;
    final AttributeSet attrs = Xml.asAttributeSet(parser);
    Log.d(TAG, "inflate: " + attrs);
    try {
      int type;
      while ((type = parser.next()) != XmlPullParser.START_TAG
          && type != XmlPullParser.END_DOCUMENT) {
        Log.d(TAG, "inflate: " + type);
      }

      if (type != XmlPullParser.START_TAG) {
        throw new InflateException(parser.getPositionDescription()
            + ": No start tag found!");
      }
      String name = parser.getName();
      result = createAnimation(name, attrs);
      if (result != null) {
        result.rInflate(parser, attrs);
      }
    } catch (XmlPullParserException e) {
      InflateException ex = new InflateException(e.getMessage());
      ex.initCause(e);
      throw ex;
    } catch (IOException e) {
      InflateException ex = new InflateException(
          parser.getPositionDescription() + ": " + e.getMessage());
      ex.initCause(e);
      throw ex;
    }
    return result;
  }

  protected void rInflate(XmlPullParser parser, AttributeSet attrs) {

  }

  private static Animation createAnimation(String name, AttributeSet attrs)
      throws InflateException {
    return new Fanimation(attrs);
  }

  protected static void ignoreTag(XmlPullParser parser) throws IOException,
      XmlPullParserException {
    final int depth = parser.getDepth();
    int type;
    while (((type = parser.next()) != XmlPullParser.END_TAG || parser
        .getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {
    }
  }

}
