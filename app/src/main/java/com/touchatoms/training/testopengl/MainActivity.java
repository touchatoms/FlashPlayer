package com.touchatoms.training.testopengl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.netease.glsurfaceView.utils.Kuai2GLView;
import com.netease.glsurfaceView.utils.ZhangyuScreen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private static final String TAG = "MainActivity";

  private Kuai2GLView zyKing;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initFlashView();
    findViewById(R.id.button_flash).setOnClickListener(this);

  }

  private void initFlashView() {
    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
        (FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
    zyKing = new Kuai2GLView(this);
    zyKing.setSrceen(new ZhangyuScreen(zyKing));

    zyKing.setOnStopListener(new Kuai2GLView.OnStopListener() {

      @Override
      public void onStop(Kuai2GLView view) {
        Log.d(TAG, "onStop: ");
      }

      @Override
      public void onStartLocate(Kuai2GLView view) {

      }
    });

    addContentView(zyKing, params);
  }

  @Override
  public void onClick(View view) {
    Log.d(TAG, "playFlash: ");
    zyKing.initElements();
    zyKing.resize();
  }
}