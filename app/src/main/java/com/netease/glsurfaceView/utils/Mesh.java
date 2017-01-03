package com.netease.glsurfaceView.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Mesh implements Disposed {
  private FloatBuffer mVerticesBuffer;
  private ShortBuffer mIndicesBuffer;
  private FloatBuffer mTextureBuffer;
  private final float[] mRGBA = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
  private FloatBuffer mColorBuffer;

  /**
   * Render the mesh.
   * 
   * @param gl
   *          the OpenGL context to render to.
   */
  public void draw(GL10 gl, Texture texture) {

    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glVertexPointer(2, GL10.GL_FLOAT, 0, mVerticesBuffer);
    gl.glColor4f(mRGBA[0], mRGBA[1], mRGBA[2], mRGBA[3]);
    if (mColorBuffer != null) {
      gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
      gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, mColorBuffer);
    }

    if (texture.texturedId != -1 && mTextureBuffer != null) {
      gl.glEnable(GL10.GL_TEXTURE_2D);
      gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
      gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
      gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.texturedId);
    }

    gl.glDrawElements(GL10.GL_TRIANGLES, mIndicesBuffer.limit(),
        GL10.GL_UNSIGNED_SHORT, mIndicesBuffer);
  }

  /**
   * Set the vertices.
   * 
   * @param vertices
   */
  protected void setVertices(float[] vertices) {
    ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
    vbb.order(ByteOrder.nativeOrder());
    mVerticesBuffer = vbb.asFloatBuffer();
    mVerticesBuffer.put(vertices);
    mVerticesBuffer.position(0);
  }

  protected void resetVertices(float[] vertices, int index) {
    mVerticesBuffer.position(0);
    mVerticesBuffer.limit(index);
    mVerticesBuffer.put(vertices, 0, mVerticesBuffer.limit());
    mVerticesBuffer.position(0);
  }

  /**
   * Set the indices.
   * 
   * @param indices
   */
  protected void setIndices(short[] indices) {
    ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
    ibb.order(ByteOrder.nativeOrder());
    mIndicesBuffer = ibb.asShortBuffer();

    mIndicesBuffer.put(indices);
    mIndicesBuffer.position(0);
  }

  protected void setIndices(int index) {
    mIndicesBuffer.position(0);
    mIndicesBuffer.limit(index);
  }

  /**
   * Set the texture coordinates.
   * 
   * @param textureCoords
   */
  protected void setTextureCoordinates(float[] textureCoords) {

    // float is 4 bytes, therefore we multiply the number if
    // vertices with 4.
    ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4);
    byteBuf.order(ByteOrder.nativeOrder());
    mTextureBuffer = byteBuf.asFloatBuffer();
    mTextureBuffer.put(textureCoords);
    mTextureBuffer.position(0);
  }

  protected void resetTextureCoordinates(float[] textureCoords, int index) {
    // float is 4 bytes, therefore we multiply the number if
    // vertices with 4.

    mTextureBuffer.position(0);
    mTextureBuffer.limit(index);
    mTextureBuffer.put(textureCoords, 0, mTextureBuffer.limit());
    mTextureBuffer.position(0);

  }

  /**
   * Set one flat color on the mesh.
   * 
   * @param red
   * @param green
   * @param blue
   * @param alpha
   */
  protected void setColor(float red, float green, float blue, float alpha) {
    mRGBA[0] = red;
    mRGBA[1] = green;
    mRGBA[2] = blue;
    mRGBA[3] = alpha;
  }

  /**
   * Set the colors
   * 
   * @param colors
   */
  protected void setColors(float[] colors) {
    // float has 4 bytes.
    ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
    cbb.order(ByteOrder.nativeOrder());
    mColorBuffer = cbb.asFloatBuffer();
    mColorBuffer.put(colors);
    mColorBuffer.position(0);
  }

  protected void resetColors(float[] colors, int index) {
    mColorBuffer.position(0);
    mColorBuffer.limit(index);
    mColorBuffer.put(colors, 0, mColorBuffer.limit());
    mColorBuffer.position(0);
  }

  @Override
  public void disposed() {
    if (mVerticesBuffer != null) {
      mVerticesBuffer = null;
    }
    if (mIndicesBuffer != null) {
      mIndicesBuffer = null;
    }
    if (mVerticesBuffer != null) {
      mTextureBuffer = null;
    }

  }

}
