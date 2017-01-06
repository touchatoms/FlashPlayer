package xfl.player;

import com.netease.frame.flash.Flash;
import com.netease.frame.flash.FlashElements;
import com.netease.frame.flash.FlashListener;
import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;

import xfl.DOMLayer;
import xfl.DOMTimeline;

public class FlashXFLPlayer implements Flash {
  public DOMLayerPlayer[] layerPlayer;
  //    public final FlashElements flashElement;
  public final DOMTimeline timeline;
  public float alphaMultiplier = 1f;

  private int state;
  private float runTime;
  private float flashEndTime;
  private boolean inScreen = true;
  private boolean isLooping;
  private boolean isEnd;
  private TextureAtlas textureAlats;

  public FlashListener listener = new FlashListener() {
    @Override
    public void playerEnd() {
    }
  };

  public FlashXFLPlayer(DOMTimeline timeline, TextureAtlas textureAlats) {
    this.timeline = timeline;
    this.textureAlats = textureAlats;
    isLooping = true;
    inScreen = false;
    state = PALY;
    initLayerPlayer(timeline, textureAlats);
  }

  public FlashXFLPlayer(DOMTimeline timeline, TextureAtlas textureAlats,
      boolean isLooping, boolean inScreen) {
    this.timeline = timeline;
    this.textureAlats = textureAlats;
    this.isLooping = isLooping;
    this.inScreen = inScreen;
    state = PALY;
    initLayerPlayer(timeline, textureAlats);
  }

  private void initLayerPlayer(DOMTimeline timeline, TextureAtlas texture) {
    layerPlayer = new DOMLayerPlayer[timeline.layersCount];
    for (int i = 0; i < timeline.layersCount; i++) {
      layerPlayer[i] = new DOMLayerPlayer(timeline.layers[i], texture);
    }
  }

  public void drawFlash(Plane plane) {
    if (state == PALY || state == PAUSE) {
      for (int i = layerPlayer.length - 1; i >= 0; i--) {
        if (isEnd) {
          layerPlayer[i].drawLayerEnd(plane);
        } else {
          layerPlayer[i].updataLayer(runTime);
          layerPlayer[i].drawLayer(plane);
        }
      }
    }
  }

  public void updateRunTime(float deltaTime) {
    if (state == PALY) {

      if (runTime >= getFalshEndTime()) {
        runTime = 0;

        if (!isLooping) {
          isEnd = true;
          if (!inScreen) {
            state = STOP;
          }
          listener.playerEnd();
        } else {
          for (int i = layerPlayer.length - 1; i >= 0; i--) {
            layerPlayer[i].reset();
          }
          listener.playerEnd();
        }
      } else {
        runTime += deltaTime;
      }
    }
  }

  public void updateRunTime(float deltaTime, float scale) {
    updateRunTime(deltaTime * scale);
  }

  public float getFalshEndTime() {
    if ( flashEndTime == 0) {
      for (DOMLayer layer : timeline.layers) {
        if (layer.frames.length > 0) {
          float frameTime = layer.frames[layer.frames.length - 1].getFrameTime();
          flashEndTime = frameTime > flashEndTime ? frameTime : flashEndTime;
        }
      }
    }
    return flashEndTime;
  }

  @Override
  public void play() {
    state = PALY;
    isEnd = false;
  }

  public void rePlay() {
    state = PALY;
    isEnd = false;
    runTime = 0;
    for (int i = 0; i < timeline.layersCount; i++) {
      layerPlayer[i].reset();
    }
  }

  public void setInScreen(boolean inScreen) {
    this.inScreen = inScreen;
  }

  public boolean getInScreen(boolean inScreen) {
    return this.inScreen;
  }

  @Override
  public void setLooping(boolean isLooping) {
    // TODO Auto-generated method stub
    this.isLooping = isLooping;
  }

  @Override
  public boolean isLooping() {
    // TODO Auto-generated method stub
    return isLooping;
  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub
    if (state == PALY) {
      state = PAUSE;
    }
  }

  @Override
  public void stop() {
    state = STOP;
    runTime = 0;
  }

  @Override
  public boolean isPlaying() {
    // TODO Auto-generated method stub
    return state == PALY && (!isEnd);
  }

  @Override
  public float getTimePosition() {
    // TODO Auto-generated method stub
    return runTime;
  }

  public boolean getIsEnd() {
    return isEnd;
  }

  public float getWidth() {
    return 0;
  }

  public float getHeight() {
    return 0;
  }

  @Override
  public void setPosition(float x, float y) {
    // TODO Auto-generated method stub

  }

  @Override
  public float getX() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public float getY() {
    // TODO Auto-generated method stub
    return 0;
  }

  public void setListener(FlashListener listener) {
    this.listener = listener;
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    if (textureAlats != null) {
      textureAlats.disposed();
    }
    if (layerPlayer != null) {
      for (int i = layerPlayer.length - 1; i >= 0; i--) {
        layerPlayer[i].disposed();
      }
      layerPlayer = null;
    }
  }

}
