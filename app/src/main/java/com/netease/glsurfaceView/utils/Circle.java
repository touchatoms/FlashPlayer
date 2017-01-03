package com.netease.glsurfaceView.utils;

public class Circle {
  public Vector2 position;
  Vector2 velocity;
  TextureRegion tex;
  public float rotation = 0;
  float wVelocity = 0;
  float va = 5f;
  float wa = 60f;
  float radius = 1;

  int target = 0;
  boolean isStop = false;

  public Circle() {
    this(0, null);
  }

  public Circle(float r, TextureRegion tex) {
    this(0, 0, r, tex);
  }

  public Circle(float px, float py, float r, TextureRegion tex) {
    this(px, py, r, 0, 0, tex);
  }

  public Circle(float px, float py, float r, float vx, float vy,
      TextureRegion tex) {
    this.position = new Vector2(px, py);
    this.radius = r;
    this.velocity = new Vector2(vx, vy);
    this.tex = tex;
  }

  public Circle(float px, float py, float r, float vx, float vy,
      float wVelocity, TextureRegion tex) {
    this.position = new Vector2(px, py);
    this.radius = r;
    this.velocity = new Vector2(vx, vy);
    this.wVelocity = wVelocity;
    this.tex = tex;
    // normalV =
    // (tangentV*(this.position.lenth()-this.radius-40))/(this.position.lenth()*200);
  }

  public void reset(float px, float py, float r, float vx, float vy,
      float wVelocity, TextureRegion tex) {
    this.position = new Vector2(px, py);
    this.radius = r;
    this.velocity = new Vector2(vx, vy);
    this.wVelocity = wVelocity;
    this.tex = tex;
    normalV = (float) (Math.random() * 20 + 40);
    tangentV = 200;
    flag = false;
    tempLenth = 2;
    isStop = false;
  }

  public float getRadius() {
    return this.radius;
  }

  public float getRotation() {
    return this.rotation;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public void setRegion(TextureRegion tex) {
    this.tex = tex;
  }

  public void draw(Plane plane) {
    if (tex != null) {
      plane.draw(tex, this.position.x - this.radius, this.position.y
          - this.radius, 2 * this.radius, 2 * this.radius, this.rotation);
    }
  }

  float tempVa = 0;
  float tempWa = 0;
  float tempLenth = 0;

  public void update(float deltaTime) {
    this.position.add(velocity.x * deltaTime, velocity.y * deltaTime);
    tempVa = this.va * deltaTime;
    tempLenth = this.velocity.lenth();

    if (tempLenth > this.va) {
      tempLenth -= tempVa;
    } else {
      tempLenth = 0;
    }

    this.velocity.nor().mul(tempLenth);

    this.rotation += this.wVelocity * deltaTime;

    this.rotation %= 360;
    // System.out.println(rotation);

    tempWa = this.wa * deltaTime;
    if (this.wVelocity > this.wa) {
      this.wVelocity -= tempWa;
    } else {
      if (this.wVelocity < -this.wa) {
        this.wVelocity += tempWa;
      } else {
        this.wVelocity = 20;
      }
    }

  }

  public void updateFixed(float deltaTime) {
    this.position.add(velocity.x * deltaTime, velocity.y * deltaTime);
    this.rotation += this.wVelocity * deltaTime;
    this.rotation %= 360;
    // System.out.println(rotation);

    normal1.set(0, 0).sub(this.position);
    normal1.nor();
    tangent1.set(-normal1.y, normal1.x).mul(tangentV);
    normal1.mul(normalV);
    this.velocity.set(tangent1);
  }

  float normalV = (float) (Math.random() * 20 + 40);
  float tangentV = 200;

  boolean flag = false;

  public void updateBegin(float deltaTime) {
    if (!isStop) {
      this.position.add(velocity.x * deltaTime, velocity.y * deltaTime);
      if (flag) {
        float angle = this.position.angle();
        if (angle < target + 0.5f && angle > target - 0.5f) {
          this.tangentV = 0;
          this.wVelocity = 0;
          isStop = true;
        }

      }
      tempLenth = this.velocity.lenth();
      normal1.set(0, 0).sub(this.position);
      normal1.nor();
      tangent1.set(-normal1.y, normal1.x).mul(tangentV);
      normal1.mul(normalV);
      this.velocity.set(tangent1).add(normal1);
      this.rotation += this.wVelocity * deltaTime;
      this.rotation %= 360;
    }
  }

  public float getVlenth() {
    return this.tempLenth;
  }

  public boolean getIsStop() {
    return this.isStop;
  }

  public void setTarget(int target) {
    this.target = target;
  }

  private static float temp1 = 0;
  private static float temp2 = 0;

  public static boolean collides(Circle circle1, Circle circle2) {
    temp1 = circle1.position.x - circle2.position.x;
    temp2 = circle1.position.y - circle2.position.y;
    temp1 = temp1 * temp1 + temp2 * temp2;

    temp2 = circle1.getRadius() + circle2.getRadius();
    temp2 = temp2 * temp2;

    return temp1 < temp2;

  }

  public static boolean collidesInner(Circle circle1, Circle circle2Inner) {
    temp1 = circle1.position.x - circle2Inner.position.x;
    temp2 = circle1.position.y - circle2Inner.position.y;
    temp1 = temp1 * temp1 + temp2 * temp2;

    temp2 = circle1.getRadius() - circle2Inner.getRadius();
    temp2 = temp2 * temp2;

    return temp1 > temp2;

  }

  static Vector2 normal1 = new Vector2();
  static Vector2 v1 = new Vector2();
  static Vector2 tangent1 = new Vector2();

  static Vector2 normal2 = new Vector2();
  static Vector2 v2 = new Vector2();
  static Vector2 tangent2 = new Vector2();

  // public static void checkCollInner(Circle bigCircle, Circle circle1) {
  // if (Circle.collidesInner(bigCircle, circle1)) {
  // normal1.set(circle1.position).sub(bigCircle.position);
  //
  // normal1.nor();
  // v1.set(circle1.velocity);
  //
  // float normalLenth = v1.dot(normal1);
  //
  // normal1.mul(normalLenth);
  //
  // tangent1.set(v1.x - normal1.x, v1.y - normal1.y);
  // normal1.mul(-1);
  //
  // circle1.velocity
  // .set(tangent1.x + normal1.x, tangent1.y + normal1.y);
  //
  // }
  // }

  // public static void checkColl(Circle circle1, Circle circle2) {
  // if (Circle.collides(circle1, circle2)) {
  // normal1.set(circle2.position).sub(circle1.position);
  // normal1.nor();
  // v1.set(circle1.velocity);
  // float normalLenth1 = v1.dot(normal1);
  // normal1.mul(normalLenth1);
  // tangent1.set(v1.x - normal1.x, v1.y - normal1.y);
  //
  // normal2.set(circle1.position).sub(circle2.position);
  // normal2.nor();
  // v2.set(circle2.velocity);
  // float normalLenth2 = v2.dot(normal2);
  // normal2.mul(normalLenth2);
  // tangent2.set(v2.x - normal2.x, v2.y - normal2.y);
  //
  // circle1.velocity
  // .set(tangent1.x + normal2.x, tangent1.y + normal2.y);
  // circle2.velocity
  // .set(tangent2.x + normal1.x, tangent2.y + normal1.y);
  // }
  // }

  // public static void checkCollOutter(Circle bigCircle, Circle circle1) {
  // if (Circle.collides(bigCircle, circle1)) {
  // normal1.set(bigCircle.position).sub(circle1.position);
  //
  // // lenth = normal.lenth()*bigCircle.w*0.001;
  // normal1.nor();
  // v1.set(circle1.velocity);
  //
  // float normalLenth = v1.dot(normal1);
  //
  // normal1.mul(normalLenth);
  //
  // tangent1.set(v1.x - normal1.x, v1.y - normal1.y);
  //
  // // lenth += tangent.lenth();
  //
  // // tangent.nor();
  // // tangent.mul(lenth);
  //
  // normal1.mul(-1);
  //
  // circle1.velocity
  // .set(tangent1.x + normal1.x, tangent1.y + normal1.y);
  //
  // }
  // }

  public static void checkCollOutter(Circle bigCircle, Circle circle1) {
    if (Circle.collides(bigCircle, circle1)) {
      circle1.normalV = 0;
      circle1.flag = true;

    }
  }
}
