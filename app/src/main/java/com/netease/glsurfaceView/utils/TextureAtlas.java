package com.netease.glsurfaceView.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * @author Administrator
 */
public class TextureAtlas implements Disposed {
  static final String[] TUPLE = new String[4];
  final HashMap<String, TextureRegion> regions = new HashMap<String, TextureRegion>();
  public Texture mTexture = null;

  private boolean flag = false;
  private float invpicWidth = 1f;
  private float invpicHeight = 1f;

  public TextureAtlas(InputStream in, Texture texture) {
    this(in, false, texture);
  }

  public TextureAtlas(InputStream in, boolean flip, Texture texture) {
    flag = false;
    mTexture = texture;
    regions.clear();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 64);
      while (true) {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        if (line.trim().length() == 0) {
          flag = false;
        } else if (!flag) {
          readTuple(reader);
          invpicWidth = 1f / Integer.parseInt(TUPLE[0]);
          invpicHeight = 1f / Integer.parseInt(TUPLE[1]);
          reader.readLine();
          readTuple(reader);
          readValue(reader);
          flag = true;
        } else {
          boolean rotate = Boolean.valueOf(readValue(reader));
          readTuple(reader);
          int left = Integer.parseInt(TUPLE[0]);
          int top = Integer.parseInt(TUPLE[1]);

          readTuple(reader);
          int width = Integer.parseInt(TUPLE[0]);
          int height = Integer.parseInt(TUPLE[1]);

          TextureRegion region = new TextureRegion(mTexture);
          region.left = left;
          region.top = top;
          region.width = width;
          region.height = height;
          region.set(invpicWidth, invpicHeight);
          region.name = line;
          region.rotate = rotate;

          if (readTuple(reader) == 4) { // split is optional
            region.splits = new int[] { Integer.parseInt(TUPLE[0]),
                Integer.parseInt(TUPLE[1]), Integer.parseInt(TUPLE[2]),
                Integer.parseInt(TUPLE[3]) };

            if (readTuple(reader) == 4) { // pad is optional, but
              // only present with
              // splits
              region.pads = new int[] { Integer.parseInt(TUPLE[0]),
                  Integer.parseInt(TUPLE[1]), Integer.parseInt(TUPLE[2]),
                  Integer.parseInt(TUPLE[3]) };

              readTuple(reader);
            }
          }

          region.originalWidth = Integer.parseInt(TUPLE[0]);
          region.originalHeight = Integer.parseInt(TUPLE[1]);

          readTuple(reader);
          region.offsetX = Integer.parseInt(TUPLE[0]);
          region.offsetY = Integer.parseInt(TUPLE[1]);

          region.index = Integer.parseInt(readValue(reader));

          if (flip) {
            region.flip = true;
          }

          // System.out.println("  region :" + region.toString());
          regions.put(region.name, region);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {

    }
  }

  static String readValue(BufferedReader reader) throws IOException {
    String line = reader.readLine();
    int colon = line.indexOf(':');
    if (colon == -1) {
      throw new IOException("Invalid line: " + line);
    }
    return line.substring(colon + 1).trim();
  }

  /** Returns the number of tuple values read (2 or 4). */
  static int readTuple(BufferedReader reader) throws IOException {
    String line = reader.readLine();
    int colon = line.indexOf(':');
    if (colon == -1) {
      throw new IOException("Invalid line: " + line);
    }
    int i = 0, lastMatch = colon + 1;
    for (i = 0; i < 3; i++) {
      int comma = line.indexOf(',', lastMatch);
      if (comma == -1) {
        if (i == 0) {
          throw new IOException("Invalid line: " + line);
        }
        break;
      }
      TUPLE[i] = line.substring(lastMatch, comma).trim();
      lastMatch = comma + 1;
    }
    TUPLE[i] = line.substring(lastMatch).trim();
    return i + 1;
  }

  public TextureRegion findRegion(String name) {
    return regions.get(name);
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    regions.clear();
    if (mTexture != null) {
      mTexture.disposed();
      mTexture = null;
    }
  }
}
