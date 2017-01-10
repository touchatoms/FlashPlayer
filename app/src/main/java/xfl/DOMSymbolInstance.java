package xfl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.netease.glsurfaceView.utils.Plane;
import com.netease.glsurfaceView.utils.TextureAtlas;
import com.netease.glsurfaceView.utils.TextureRegion;

public class DOMSymbolInstance {
  public String libraryItemName;
  public String symbolType;
  //	public int firstFrame;
  //	public String loop;

  public Matrix matrix;
  public Point transformationPoint;
  public AdjustColorFilter filters;
  public Color color;
  public TextureAtlas textureAtlas;
  public TextureRegion textureRegion;
  //	public float centerPoint3DX;
  //	public float centerPoint3DY;

  public DOMSymbolInstance(Element xml, TextureAtlas textureAtlas) {
    this.textureAtlas = textureAtlas;

    if (!xml.getTagName().equals("DOMSymbolInstance")) {
      System.out.println("wrong xml to construct DOMSymbolInstance, get "
          + xml.getTagName());
    }

    libraryItemName = xml.getAttribute("libraryItemName");

    this.textureRegion = textureAtlas.findRegion(libraryItemName);
    //		symbolType = xml.getAttribute("symbolType", null);
    //		if (symbolType == null || !symbolType.equals("graphic")) {
    //			System.out.println("only graphic SymbolInstance supported! in " + libraryItemName);
    //		}
    //	firstFrame = xml.getIntAttribute("firstFrame");
    //	loop = xml.getAttribute("loop");
    //	centerPoint3DX = xml.getFloatAttribute("centerPoint3DX", 0);
    //	centerPoint3DY = xml.getFloatAttribute("centerPoint3DY", 0);

    NodeList matrixList = xml.getElementsByTagName("matrix");
    if (matrixList != null && matrixList.getLength() > 0) {
      Element matrixElement = (Element) matrixList.item(0);
      if (matrixElement != null) {
        // Matrix是matrix的下一层
        NodeList MatrixList = matrixElement.getElementsByTagName("Matrix");
        if (MatrixList != null && MatrixList.getLength() > 0) {
          matrix = new Matrix((Element)MatrixList.item(0));
        }
      }
    }

    NodeList pointList = xml.getElementsByTagName("transformationPoint");
    if (pointList != null && pointList.getLength() > 0) {
      Element pointElement = (Element) pointList.item(0);
      if (pointElement != null) {
        // Point是transformationPoint的下一层
        NodeList PointList = pointElement.getElementsByTagName("Point");
        if (PointList != null && PointList.getLength() > 0) {
          transformationPoint = new Point((Element)PointList.item(0));
        }
      }
    }

//    tmp = xml.getChildByName("filters");
//    if (tmp != null) {
//      tmp = tmp.getChildByName("AdjustColorFilter");
//      if (tmp != null) {
//        filters = new AdjustColorFilter(tmp);
//      }
//    }

    NodeList colorList = xml.getElementsByTagName("color");
    if (colorList != null && colorList.getLength() > 0) {
      Element colorlement = (Element) colorList.item(0);
      if (colorlement != null) {
        // Color是color的下一层
        NodeList ColorList = colorlement.getElementsByTagName("Color");
        if (ColorList != null && ColorList.getLength() > 0) {
          color = new Color((Element)ColorList.item(0));
        }
      }
    }
  }
}
