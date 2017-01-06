//package xfl.player;
//
//import com.netease.glsurfaceView.utils.Disposed;
//import com.netease.glsurfaceView.utils.Plane;
//import com.netease.glsurfaceView.utils.TextureAtlas;
//
//public class LayerPlayer implements Disposed {
//  private int frameindex = 0;
//  public Layer layer;
//  private FramePlayer[] framePlayers;
//
//  public LayerPlayer(Layer layer, TextureAtlas texture) {
//    this.layer = layer;
//    framePlayers = new FramePlayer[layer.framesNumber];
//    for (int i = 0; i < layer.framesNumber; i++) {
//      framePlayers[i] = new FramePlayer(layer.frames[i], texture);
//    }
//  }
//
//  public void drawLayer(Plane plane) {
//    framePlayers[frameindex].drawFrame(plane);
//  }
//
//  public void drawLayerEnd(Plane plane) {
//    framePlayers[layer.framesNumber - 1].drawFrame(plane);
//  }
//
//  public void updataLayer(float runTime) {
//    if (runTime >= layer.frames[frameindex].endTime) {
//      frameindex++;
//    }
//    if (frameindex >= layer.framesNumber) {
//      frameindex = layer.framesNumber - 1;
//      // System.out.println("frame  :" + frameindex);
//    }
//  }
//
//  public void reset() {
//    frameindex = 0;
//  }
//
//  @Override
//  public void disposed() {
//    if (framePlayers != null) {
//      for (int i = 0; i < layer.framesNumber; i++) {
//        framePlayers[i].disposed();
//      }
//      framePlayers = null;
//    }
//
//  }
//}
