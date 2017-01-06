
package com.netease.glsurfaceView.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.netease.frame.flash.Animation;
import com.netease.frame.flash.FlashPlayer;
import com.touchatoms.training.testopengl.R;

import xfl.Xfl;
import xfl.player.Parser;

public class ZhangyuScreen extends Screen {

  private static final String TAG = "ZhangyuScreen";

  public ZhangyuScreen(Kuai2GLView view) {
    super(view);
    createBitmap();
    state = OTHER;
  }

  @Override
  public void initCamera() {
  }

  @Override
  public void present(float deltaTime, GL10 gl) {
    if (plane != null) {
      plane.begin(gl);
      draw(deltaTime);
      plane.end(gl);
    }
  }

  @Override
  public void pause() {
    state = OTHER;
    try {
      soundPool.stop(mPrezentSound);
    } catch (Exception e) {

    }
  }

  @Override
  public void dispose() {
    state = OTHER;
    if (texAltas != null) {
      texAltas.disposed();
      texAltas = null;
    }
    if (plane != null) {
      plane.disposed();
      plane = null;
    }

    if (flashPlayer != null) {
      flashPlayer.disposed();
      flashPlayer = null;
    }

    if (soundPool != null) {
      soundPool.release();
    }
    Animation.removeAmination(R.drawable.zhangyuframe);
  }

  public Plane plane;
  private Bitmap background;
  private Bitmap backgroundFire;

  SoundPool soundPool;
  int soundRotate;
  AudioManager audio;
  int mPrezentSound;

  public static final float[][] POSITIONS = new float[2][2];
  public static Vector2[] targetPosition = new Vector2[2];
  private int state = 0;
  static final int READY = 0;
  static final int RUNNING = 1;
  static final int POST_RUNNING = 2;
  static final int END = 3;
  static final int PRE_RUNNING = 4;
  static final int OTHER = 5;
  static final int DISPLAY = 6;

  public interface OnStopListener {
    void onStop(Kuai2GLView view);

    void onStartLocate(Kuai2GLView view);
  }

  @Override
  public void update(float deltaTime) {
    if (plane == null || flashPlayer == null) {
      return;
    }

    flashPlayer.updateRunTime(deltaTime);
    switch (state) {
      case READY:
        action.calValue(deltaTime);
        if (action.getOver()) {
          state = RUNNING;
          action.formAToB(0, 1, 1f);
        }
        break;
      case RUNNING:
        action.calValue(deltaTime);
        if (action.getOver()) {
          state = POST_RUNNING;
          action.formAToB(1f, 0, 1f);
        }
        break;
      case POST_RUNNING:
        action.calValue(deltaTime);
        if (action.getOver()) {
          state = OTHER;
          try {
            soundPool.stop(mPrezentSound);
          } catch (Exception e) {

          }
          view.onStopListener.onStop(view);
        }
        break;
      default:
        break;
    }
  }

  private void draw(float deltaTime) {
    try {
      switch (state) {
        case READY:
          plane.setColor(1, 1, 1, action.getValue());
          flashPlayer.drawFlash(plane);
          break;
        case RUNNING:
          plane.setColor(1, 1, 1, 1);
          flashPlayer.drawFlash(plane);
          break;
        case POST_RUNNING:
          plane.setColor(1, 1, 1, action.getValue());
          flashPlayer.drawFlash(plane);
          plane.setColor(1, 1, 1, 1);
          break;
        default:
          break;
      }
    } catch (Exception e) {
    }
  }

  Action action = new Action(0, 1, "linear");
  TextureAtlas texAltas;
  TextureAtlas texAltasFire;
  FlashPlayer flashPlayer;

  private void createBitmap() {


    try {
      background = BitmapConform.toConformBitmap(
          BitmapFactory.decodeResource(getResources(), R.drawable.zhangyu1));
      backgroundFire = BitmapConform.toConformBitmap(
          BitmapFactory.decodeResource(getResources(), R.drawable.fire_tex));
    } catch (OutOfMemoryError e) {
      background = null;
      plane = null;
    } catch (Exception e) {
      background = null;
      plane = null;
    }

    if (background != null) {

      plane = new Plane(640, 800);
      InputStream in = getResources().openRawResource(R.raw.zhangyu);
      Texture texture = new Texture(background);
      texAltas = new TextureAtlas(in, texture);

      Parser parser = new Parser();
      try {

        InputStream inFire = getResources().openRawResource(R.raw.fire_tex);
        Texture textureFire = new Texture(backgroundFire);
        texAltasFire = new TextureAtlas(inFire, textureFire);

        InputStream inFlash = getResources().openRawResource(R.raw.raise_btn_effect);
        Xfl xfl = parser.parse(inFlash, texAltasFire);
        Log.d(TAG, "createBitmap: " + xfl);
      } catch (OutOfMemoryError e) {
        background = null;
        plane = null;
      } catch (IOException e) {
        e.printStackTrace();
      }

      Animation.createAnimationFromResouce(R.drawable.zhangyuframe,
          getResources());
      flashPlayer = new FlashPlayer(
          Animation.getFanimation(R.drawable.zhangyuframe).flashElement,
          texAltas, true, true);
    } else {
      plane = null;
    }
  }

  TextureRegion pp;

  @Override
  public boolean initElements() {
    if (background == null) {
      return false;
    } else {
      state = READY;
      action.formAToB(0, 1, 0.5f);
      return true;
    }
  }

  @Override
  public void resume() {
    if (texAltas != null) {
      texAltas.disposed();
      texAltas = null;
    }
    if (background != null) {
      background.recycle();
    }

    createBitmap();
    if (plane != null) {
      plane.setSize(view.w, view.h);
    }
  }

  @Override
  public void resize(float width, float height) {
    if (plane != null) {
      plane.setSize(width, height);
      // resize();
    }
  }

  @Override
  public void resize() {
    if (plane != null) {
      plane.setSize(view.w, view.h);
      for (int i = 0; i < targetPosition.length; i++) {
        targetPosition[i].set(POSITIONS[i][0], POSITIONS[i][1]);
        targetPosition[i].add(-plane.widthH, plane.heightH)
            .mul(1f / plane.scale);
        Log.d(TAG, "resize: ");
      }
    }
  }

  @Override
  public void onClick() {

  }
}
