package xfl;

import org.w3c.dom.Element;

public class Point {
	public float x;
	public float y;
	
	public Point(Element xml) {
		if (!xml.getTagName().equals("Point")) {
			System.out.println("wrong xml to construct Point, get "
					+ xml.getTagName());
		}

		String str = null;

		str = xml.getAttribute("x");
		if (str != null && str != "") {
			x = Float.valueOf(str);
		}
		str = xml.getAttribute("y");
		if (str != null && str != "") {
			y = Float.valueOf(str);
		}

	}

}
