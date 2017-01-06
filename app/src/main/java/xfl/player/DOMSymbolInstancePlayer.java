package xfl.player;

import com.netease.frame.flash.Element;
import com.netease.glsurfaceView.utils.Disposed;
import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

public class DOMSymbolInstancePlayer implements Disposed {
  public Element element;
  public TextureRegion textureRegion;

  public DOMSymbolInstancePlayer(Element element, TextureAtlas texture) {
    this.element = element;
    this.textureRegion = texture.findRegion(element.textureName);
  }

  public void drawElement(Plane plane) {
    // plane.draw(this.textureRegion, this.element.vertex4.x0+offsetx,
    // this.element.vertex4.y0+offsety, this.element.vertex4.x1+offsetx,
    // this.element.vertex4.y1+offsety, this.element.vertex4.x2+offsetx,
    // this.element.vertex4.y2+offsety, this.element.vertex4.x3+offsetx,
    // this.element.vertex4.y3+offsety);
    // plane.setColor(1, 1, 1, this.element.alphaMultiplier);
    plane.draw(this.textureRegion, this.element.vertex4.x0,
        this.element.vertex4.y0, this.element.vertex4.x1,
        this.element.vertex4.y1, this.element.vertex4.x2,
        this.element.vertex4.y2, this.element.vertex4.x3,
        this.element.vertex4.y3);
  }

  @Override
  public void disposed() {
    // TODO Auto-generated method stub
    this.textureRegion = null;
  }

}
