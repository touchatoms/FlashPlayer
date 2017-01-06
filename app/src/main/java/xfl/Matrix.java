package xfl;


import org.w3c.dom.Element;

public class Matrix {
	public float a;
	public float b;
	public float c;
	public float d;
	public float tx;
	public float ty;

	public Matrix(Element xml) {
		if (!xml.getTagName().equals("Matrix")) {
			System.out.println("wrong xml to construct Matrix, get "
					+ xml.getTagName());
		}
		a = 1;
		b = 0;
		c = 0;
		d = 1;

		String str;

		str = xml.getAttribute("tx");
		if (str != null && str != "") {
			tx = Float.parseFloat(str);
		}
		str = xml.getAttribute("ty");
		if (str != null && str != "") {
			ty = Float.parseFloat(str);
		}

		str = xml.getAttribute("a");
		if (str != null && str != "") {
			a = Float.parseFloat(str);
		}

		str = xml.getAttribute("b");
		if (str != null && str != "") {
			b = Float.parseFloat(str);
		}

		str = xml.getAttribute("c");
		if (str != null && str != "") {
			c = Float.parseFloat(str);
		}

		str = xml.getAttribute("d");
		if (str != null && str != "") {
			d = Float.parseFloat(str);
		}
	}

	public Matrix() {
		a = 1;
		d = 1;
		b = 0;
		c = 0;
		tx = 0;
		ty = 0;
	}
}
