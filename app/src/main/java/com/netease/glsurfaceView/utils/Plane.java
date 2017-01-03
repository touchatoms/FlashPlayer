package com.netease.glsurfaceView.utils;

import javax.microedition.khronos.opengles.GL10;

public class Plane implements Disposed {

  public float widthH = 1;

  public float heightH = 1;

  public float standardWidth = 320;
  public float standardHegiht = 320;
  public float scale = 1;
  int size = 1000;

  float textureCoordinates[] = new float[8 * size];
  short[] indices = new short[6 * size];
  float[] vertices = new float[8 * size];
  float[] colors = new float[4 * size];

  float mcolor = Color.toFloatBits(1f, 1f, 1f, 1f);

  public int index = 0;

  public Mesh mesh = new Mesh();

  private Texture texture = null;

  private GL10 gl;

  public void setColor(float r, float g, float b, float a) {
    mcolor = Color.toFloatBits(r, g, b, a);
  }

  public Plane(float width, float hegiht) {
    short j = 0;
    for (int i = 0; i < size; i += 6, j += 4) {
      indices[i + 0] = (short) (j + 0);
      indices[i + 1] = (short) (j + 1);
      indices[i + 2] = (short) (j + 2);
      indices[i + 3] = (short) (j + 2);
      indices[i + 4] = (short) (j + 3);
      indices[i + 5] = (short) (j + 0);
    }
    mesh.setIndices(indices);
    mesh.setVertices(vertices);
    mesh.setTextureCoordinates(textureCoordinates);
    mesh.setColors(colors);
    standardWidth = width;
    standardHegiht = hegiht;
  }

  int tempindex = 0;

  public void draw(TextureRegion r, float sx, float sy, float sw, float sh,
      float rotation) {
    checkTex(r.getTexture());

    genCoordinate(sx, sy, sw, sh, rotation);
    draw(x1, y1, x2, y2, x3, y3, x4, y4, r.u, r.v, r.u2, r.v2);

  }

  public void draw(Texture tex, float sx, float sy, float sw, float sh,
      float rotation) {
    checkTex(tex);

    // genCoordinate(sx, sy, sw, sh, rotation);

    genCoordinate(sx + sw / 2f, sy + sh / 2f, -sw / 2f, -sh / 2f, sw, sh,
        rotation);

    draw(x1, y1, x2, y2, x3, y3, x4, y4, 0, 0, 1, 1);
  }

  public void draw(Texture tex, float parentX, float parentY, float localX,
      float localY, float sw, float sh, float rotation) {
    checkTex(tex);

    genCoordinate(parentX, parentY, localX, localY, sw, sh, rotation);
    draw(x1, y1, x2, y2, x3, y3, x4, y4, 0, 0, 1, 1);
  }

  public void draw(TextureRegion tex, float parentX, float parentY,
      float localX, float localY, float sw, float sh, float rotation) {
    checkTex(tex.getTexture());

    genCoordinate(parentX, parentY, localX, localY, sw, sh, rotation);
    draw(x1, y1, x2, y2, x3, y3, x4, y4, tex.u, tex.v, tex.u2, tex.v2);
  }

  float worldOriginX, worldOriginY, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y;

  float x1, y1, x2, y2, x3, y3, x4, y4;

  private void genCoordinate(float parentX, float parentY, float localX,
      float localY, float sw, float sh, float rotation) {
    worldOriginX = parentX + localX;
    worldOriginY = parentX + localY;
    p1x = localX;
    p1y = localY;
    p2x = sw + localX;
    p2y = localY;
    p3x = sw + localX;
    p3y = sh + localY;
    p4x = localX;
    p4y = sh + localY;
    if (rotation != 0) {
      final float cos = NumberUtils.cosDeg(rotation);
      final float sin = NumberUtils.sinDeg(rotation);
      x1 = cos * p1x - sin * p1y;
      y1 = sin * p1x + cos * p1y;

      x2 = cos * p2x - sin * p2y;
      y2 = sin * p2x + cos * p2y;

      x3 = cos * p3x - sin * p3y;
      y3 = sin * p3x + cos * p3y;

      x4 = x1 + (x3 - x2);
      y4 = y3 - (y2 - y1);
    } else {
      x1 = p1x;
      y1 = p1y;

      x2 = p2x;
      y2 = p2y;

      x3 = p3x;
      y3 = p3y;

      x4 = p4x;
      y4 = p4y;
    }

    x1 += worldOriginX;
    y1 += worldOriginY;
    x2 += worldOriginX;
    y2 += worldOriginY;
    x3 += worldOriginX;
    y3 += worldOriginY;
    x4 += worldOriginX;
    y4 += worldOriginY;
  }

  private void genCoordinate(float sx, float sy, float sw, float sh,
      float rotation) {
    worldOriginX = sx + sw / 2;
    worldOriginY = sy + sh / 2;
    p1x = -sw / 2;
    p1y = -sh / 2;
    p2x = -p1x;
    p2y = p1y;
    p3x = p2x;
    p3y = -p1y;
    p4x = p1x;
    p4y = p3y;
    if (rotation != 0) {
      final float cos = NumberUtils.cosDeg(rotation);
      final float sin = NumberUtils.sinDeg(rotation);
      x1 = cos * p1x - sin * p1y;
      y1 = sin * p1x + cos * p1y;

      x2 = cos * p2x - sin * p2y;
      y2 = sin * p2x + cos * p2y;

      x3 = cos * p3x - sin * p3y;
      y3 = sin * p3x + cos * p3y;

      x4 = x1 + (x3 - x2);
      y4 = y3 - (y2 - y1);
    } else {
      x1 = p1x;
      y1 = p1y;

      x2 = p2x;
      y2 = p2y;

      x3 = p3x;
      y3 = p3y;

      x4 = p4x;
      y4 = p4y;
    }

    x1 += worldOriginX;
    y1 += worldOriginY;
    x2 += worldOriginX;
    y2 += worldOriginY;
    x3 += worldOriginX;
    y3 += worldOriginY;
    x4 += worldOriginX;
    y4 += worldOriginY;
  }

  public void draw(float x0, float y0, float x1, float y1, float x2, float y2,
      float x3, float y3, float x, float y, float w, float h) {

    tempindex = index * 8;
    vertices[tempindex++] = x0;
    vertices[tempindex++] = y0;

    vertices[tempindex++] = x1;
    vertices[tempindex++] = y1;

    vertices[tempindex++] = x2;
    vertices[tempindex++] = y2;

    vertices[tempindex++] = x3;
    vertices[tempindex++] = y3;

    tempindex = index * 8;
    textureCoordinates[tempindex++] = x;
    textureCoordinates[tempindex++] = h;

    textureCoordinates[tempindex++] = w;
    textureCoordinates[tempindex++] = h;

    textureCoordinates[tempindex++] = w;
    textureCoordinates[tempindex++] = y;

    textureCoordinates[tempindex++] = x;
    textureCoordinates[tempindex++] = y;

    tempindex = index * 4;

    colors[tempindex++] = mcolor;
    colors[tempindex++] = mcolor;
    colors[tempindex++] = mcolor;
    colors[tempindex++] = mcolor;

    tempindex = index * 6;
    int temp2 = index * 4;
    indices[tempindex++] = (short) temp2;
    indices[tempindex++] = (short) (temp2 + 1);
    indices[tempindex++] = (short) (temp2 + 2);
    indices[tempindex++] = (short) (temp2 + 2);
    indices[tempindex++] = (short) (temp2 + 3);
    indices[tempindex++] = (short) (temp2 + 0);

    index++;
  }

  public void draw(TextureRegion r, float x0, float y0, float x1, float y1,
      float x2, float y2, float x3, float y3) {
    checkTex(r.getTexture());
    draw(x0, y0, x1, y1, x2, y2, x3, y3, r.u, r.v, r.u2, r.v2);
  }

  public void flush(GL10 gl) {
    mesh.setIndices(index * 6);
    mesh.resetVertices(vertices, index * 8);
    mesh.resetTextureCoordinates(textureCoordinates, index * 8);
    mesh.resetColors(colors, index * 4);
    mesh.draw(gl, texture);
    index = 0;
  }

  public void checkSize() {
    if (index >= size) {
      flush(gl);
    }
  }

  public void checkTex(Texture tex) {
    tex.create(gl);
    if (texture == null) {
      texture = tex;
    } else {
      if (!texture.equals(tex)) {
        flush(gl);
        texture = tex;
      }
    }
    checkSize();
  }

  public void begin(GL10 gl) {
    // Counter-clockwise winding.
    this.gl = gl;
    index = 0;
    gl.glFrontFace(GL10.GL_CCW);
    // Enable face culling.
    gl.glEnable(GL10.GL_CULL_FACE);
    // What faces to remove with the face culling.
    gl.glCullFace(GL10.GL_BACK);
    gl.glEnable(GL10.GL_BLEND);
    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    // gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_DST_ALPHA);
    gl.glTranslatef(widthH, heightH, 0);
    gl.glScalef(scale, scale, 1);
  }

  public void setSize(float width, float height) {
    widthH = width / 2f;
    heightH = height / 2f;
    if (width < height) {
      scale = width / standardWidth;
    } else {
      scale = height / standardWidth;
    }
  }

  public void end(GL10 gl) {
    if (index > 0) {
      flush(gl);
    }

    // Disable the vertices buffer.
    gl.glDisable(GL10.GL_BLEND);
    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    // Disable face culling.
    gl.glDisable(GL10.GL_CULL_FACE);
  }

  @Override
  public void disposed() {
    if (textureCoordinates != null) {
      textureCoordinates = null;
    }
    if (indices != null) {
      indices = null;
    }
    if (vertices != null) {
      vertices = null;
    }
    if (colors != null) {
      colors = null;
    }
    if (mesh != null) {
      mesh.disposed();
      mesh = null;
    }
  }

}
