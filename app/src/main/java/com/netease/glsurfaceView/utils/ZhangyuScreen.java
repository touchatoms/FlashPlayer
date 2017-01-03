
package com.netease.glsurfaceView.utils;

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

public class ZhangyuScreen extends Screen {

  private static final String TAG = "ZhangyuScreen";
  
  public ZhangyuScreen(Kuai2GLView view) {
    super(view);
    createBitmap();
    createSound();
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

  SoundPool soundPool;
  int soundRotate;
  AudioManager audio;
  int mPrezentSound;

  private void createSound() {
    //    if (soundPool == null) {
    //      soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 100);
    //      soundRotate = soundPool.load(BundleContextFactory.getInstance().getBundleContext().getAPKContext(), R.raw.zyking, 1);
    //    }
    //    audio = (AudioManager) view.getContext()
    //        .getSystemService(Context.AUDIO_SERVICE);
  }

  Circle[][] circles = new Circle[2][4];
  Action2[][] circlePosAction = new Action2[2][4];

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
          for (int i = 0; i < circlePosAction.length; i++) {
            for (int j = 0; j < circlePosAction[i].length; j++) {
              circlePosAction[i][j].aToB(circles[i][j].position,
                  targetPosition[i], 1f);
            }
          }
          action.formAToB(1f, 0, 1f);
        }
        break;
      case POST_RUNNING:
        action.calValue(deltaTime);
        for (int i = 0; i < circlePosAction.length; i++) {
          for (int j = 0; j < circlePosAction[i].length; j++) {
            circlePosAction[i][j].calValue(deltaTime);
            circles[i][j].position.set(circlePosAction[i][j].getValue());
          }
        }
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
          drawCircles();
          break;
        case RUNNING:
          plane.setColor(1, 1, 1, 1);
          flashPlayer.drawFlash(plane);
          drawCircles();
          break;
        case POST_RUNNING:
          plane.setColor(1, 1, 1, action.getValue());
          flashPlayer.drawFlash(plane);
          plane.setColor(1, 1, 1, 1);
          drawCircles();
          break;
        default:
          break;
      }
    } catch (Exception e) {
    }
  }

  Action action = new Action(0, 1, "linear");
  TextureAtlas texAltas;
  FlashPlayer flashPlayer;

  private void createBitmap() {

    try {
      background = BitmapConform.toConformBitmap(
          BitmapFactory.decodeResource(getResources(), R.drawable.zhangyu1));
    } catch (Exception e) {
      background = null;
      plane = null;
    } catch (OutOfMemoryError e) {
      background = null;
      plane = null;
    }

    if (background != null) {
      plane = new Plane(640, 800);
      InputStream in = getResources().openRawResource(R.raw.zhangyu);
      Texture texture = new Texture(background);
      texAltas = new TextureAtlas(in, texture);
      pp = texAltas.findRegion("zypp");
      Animation.createAnimationFromResouce(R.drawable.zhangyuframe,
          getResources());
      flashPlayer = new FlashPlayer(
          Animation.getFanimation(R.drawable.zhangyuframe).flashElement,
          texAltas, true, true);
      circles[0] = new Circle[4];

      circles[0][3] = new Circle(40, 100, 25, pp);
      circles[0][2] = new Circle(80, 100, 20, pp);
      circles[0][1] = new Circle(60, 50, 15, pp);
      circles[0][0] = new Circle(100, 30, 10, pp);
      circles[1] = new Circle[4];
      circles[1][3] = new Circle(-40, 100, 25, pp);
      circles[1][2] = new Circle(-80, 100, 20, pp);
      circles[1][1] = new Circle(-60, 50, 15, pp);
      circles[1][0] = new Circle(-100, -30, 10, pp);

      for (int i = 0; i < circlePosAction.length; i++) {
        for (int j = 0; j < circlePosAction[i].length; j++) {
          circlePosAction[i][j] = new Action2(0, 0, "sineOut");
        }
        targetPosition[i] = new Vector2();
      }
    } else {
      plane = null;
    }
  }

  private void drawCircles() {
    for (int i = 0; i < circles.length; i++) {
      for (int j = 0; j < circles[i].length; j++) {
        circles[i][j].draw(plane);
      }
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
      circles[0][3].position.set(80, -200);
      circles[0][2].position.set(130, -180);
      circles[0][1].position.set(200, -150);
      circles[0][0].position.set(220, -30);
      circles[1][3].position.set(-80, 150);
      circles[1][2].position.set(-120, 180);
      circles[1][1].position.set(-200, -30);
      circles[1][0].position.set(-150, -60);
      try {
        int ringerMode = AudioManager.RINGER_MODE_NORMAL;
        if (audio != null) {
          ringerMode = audio.getRingerMode();
        }
        //        if (ringerMode == AudioManager.RINGER_MODE_NORMAL
        //            && AppContext.getInstance().getPreference()
        //                .getInt(Preference.PREFERENCE_KEY_VOICE_OFF) == 0) {
        //          mPrezentSound = soundPool.play(soundRotate, 0.6f, 0.6f, 0, 0, 1.0f);
        //        }
      } catch (Exception e) {

      }
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
