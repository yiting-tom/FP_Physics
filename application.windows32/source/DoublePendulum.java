import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DoublePendulum extends PApplet {

float r1 = 250;  
float r2 = 250;
float m1 = 5;    
float m2 = 5;
float a1 = PI/2;
float a2 = PI/2;
float a1_v = -0.000f;
float a2_v = -0.000f;
float px2 = -1;
float py2 = -1;
float cx, cy;
float g = 0.5f;
boolean flag = false;
///////////////////////////////////
float R1 = 250;  
float R2 = 250;
float M1 = 5;    
float M2 = 5;
float A1 = PI/2;
float A2 = PI/2;
float A1_v = -0.000f;
float A2_v = -0.000f;
float Px2 = -1;
float Py2 = -1;
///////////////////////////////////
float Rr1 = 250;  
float Rr2 = 250;
float Mm1 = 5;    
float Mm2 = 5;
float Aa1 = PI/2;
float Aa2 = PI/2;
float Aa1_v = -0.000f;
float Aa2_v = -0.000f;
float Ppx2 = -1;
float Ppy2 = -1;

PGraphics canvas;

public void setup() {
  
  cx = width/2;
  cy = height*2/5;
  canvas = createGraphics(width, height);
  canvas.beginDraw();
  canvas.background(10);
  canvas.endDraw();
}

public void draw() {
  background(0);
  imageMode(CORNER);
  image(canvas, 0, 0, width, height);

  fill(180, 255, 255);
  text("r1: " +r1, 10, 300);
  text("r2: " +r2, 10, 320);
  text("m1: " +m1, 10, 340);
  text("m2: " +m2, 10, 360);
  fill(255, 255, 180);
  text("R1: " +R1, 10, 400);
  text("R2: " +R2, 10, 420);
  text("M1: " +M1, 10, 440);
  text("M2: " +M2, 10, 460);
  fill(255, 180, 255);
  text("Rr1: " +Rr1, 10, 500);
  text("Rr2: " +Rr2, 10, 520);
  text("Mm1: " +Mm1, 10, 540);
  text("Mm2: " +Mm2, 10, 560);

  /*---------------------FORMULA---------------------*/
  float num1 = -g * (2 * m1 + m2) * sin(a1);
  float num2 = -m2 * g * sin(a1-2*a2);
  float num3 = -2*sin(a1-a2)*m2;
  float num4 = a2_v*a2_v*r2+a1_v*a1_v*r1*cos(a1-a2);
  float den = r1 * (2*m1+m2-m2*cos(2*a1-2*a2));
  float a1_a = (num1 + num2 + num3*num4) / den;
  num1 = 2 * sin(a1-a2);
  num2 = (a1_v*a1_v*r1*(m1+m2));
  num3 = g * (m1 + m2) * cos(a1);
  num4 = a2_v*a2_v*r2*m2*cos(a1-a2);
  den = r2 * (2*m1+m2-m2*cos(2*a1-2*a2));
  float a2_a = (num1*(num2+num3+num4)) / den;

  float Num1 = -g * (2 * M1 + M2) * sin(A1);
  float Num2 = -M2 * g * sin(A1-2*A2);
  float Num3 = -2*sin(A1-A2)*M2;
  float Num4 = A2_v*A2_v*R2+A1_v*A1_v*R1*cos(A1-A2);
  float Den = R1 * (2*M1+M2-M2*cos(2*A1-2*A2));
  float A1_a = (Num1 + Num2 + Num3*Num4) / Den;
  Num1 = 2 * sin(A1-A2);
  Num2 = (A1_v*A1_v*R1*(M1+M2));
  Num3 = g * (M1 + M2) * cos(A1);
  Num4 = A2_v*A2_v*R2*M2*cos(A1-A2);
  Den = R2 * (2*M1+M2-M2*cos(2*A1-2*A2));
  float A2_a = (Num1*(Num2+Num3+Num4)) / Den;

  float Nnum1 = -g * (2 * Mm1 + Mm2) * sin(Aa1);
  float Nnum2 = -Mm2 * g * sin(Aa1-2*Aa2);
  float Nnum3 = -2*sin(Aa1-Aa2)*Mm2;
  float Nnum4 = Aa2_v*Aa2_v*Rr2+Aa1_v*Aa1_v*Rr1*cos(Aa1-Aa2);
  float Dden = Rr1 * (2*Mm1+Mm2-Mm2*cos(2*Aa1-2*Aa2));
  float Aa1_a = (Nnum1 + Nnum2 + Nnum3*Nnum4) / Dden;
  Nnum1 = 2 * sin(Aa1-Aa2);
  Nnum2 = (Aa1_v*Aa1_v*Rr1*(Mm1+Mm2));
  Nnum3 = g * (Mm1 + Mm2) * cos(Aa1);
  Nnum4 = Aa2_v*Aa2_v*Rr2*Mm2*cos(Aa1-Aa2);
  Dden = Rr2 * (2*Mm1+Mm2-Mm2*cos(2*Aa1-2*Aa2));
  float Aa2_a = (Nnum1*(Nnum2+Nnum3+Nnum4)) / Dden;

  float x1 = r1 * sin(a1);
  float y1 = r1 * cos(a1);
  float x2 = x1 + r2 * sin(a2);
  float y2 = y1 + r2 * cos(a2);

  float X1 = R1 * sin(A1);
  float Y1 = R1 * cos(A1);
  float X2 = X1 + R2 * sin(A2);
  float Y2 = Y1 + R2 * cos(A2);

  float Xx1 = Rr1 * sin(Aa1);
  float Yy1 = Rr1 * cos(Aa1);
  float Xx2 = Xx1 + Rr2 * sin(Aa2);
  float Yy2 = Yy1 + Rr2 * cos(Aa2);

  textSize(18);
  fill(255);

  translate(cx, cy);

  stroke(255);
  strokeWeight(0.50f);

  line(0, 0, x1, y1);
  fill(0, 80, 80);
  ellipse(x1, y1, m1*2, m1*2);

  line(x1, y1, x2, y2);
  fill(0, 80, 80);
  ellipse(x2, y2, m2*2, m2*2);

  if (keyCode == 122 || keyCode == 123) {
    stroke(255);
    strokeWeight(0.50f);

    line(0, 0, X1, Y1);
    fill(80, 80, 0);
    ellipse(X1, Y1, M1*2, M1*2);

    line(X1, Y1, X2, Y2);
    fill(80, 80, 0);
    ellipse(X2, Y2, M2*2, M2*2);
    if (keyCode == 123) {
      stroke(255);
      strokeWeight(0.50f);

      line(0, 0, Xx1, Yy1);
      fill(80, 0, 80);
      ellipse(Xx1, Yy1, Mm1*2, Mm1*2);

      line(Xx1, Yy1, Xx2, Yy2);
      fill(80, 0, 80);
      ellipse(Xx2, Yy2, Mm2*2, Mm2*2);
    }
  }
  if (keyCode == 67) {
    canvas.clear();
  }
  /*--------------------CONFIG-----------------*/
  //m
  if (keyCode == 81) {  //Q
    m1+=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 87) {  //W
    m1-=0.0001f;
    keyCode = 0;  
}
  if (keyCode == 69) {  //E
    m2+=0.0001f;
    keyCode = 0;
  }  
  if (keyCode == 82) {  //R
    m2-=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 84) {  //T
    r1+=10;
    keyCode = 0;
  }
  if (keyCode == 89) {  //Y
    r1-=10;
    keyCode = 0;
  }
  if (keyCode == 85) {  //U
    r2+=10;
    keyCode = 0;
  }
  if (keyCode == 73) {  //I
    r2-=10;
    keyCode = 0;
  }
  
  //M
  if (keyCode == 65) {  //A
    M1+=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 83) {  //S
    M1-=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 68) {  //D
    M2+=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 70) {  //F
    M2-=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 71) {  //G
    R1+=10;
    keyCode = 0;
  }
  if (keyCode == 72) {  //H
    R1-=10;
    keyCode = 0;
  }
  if (keyCode == 74) {  //J
    R2+=10;
    keyCode = 0;
  }
  if (keyCode == 75) {  //K
    R2-=10;
    keyCode = 0;
  }
  
  //Mm
  if (keyCode == 97) {  //1
    Mm1+=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 98) {  //2
    Mm1-=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 99) {  //3
    Mm2+=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 100) {  //4
    Mm2-=0.0001f;
    keyCode = 0;
  }
  if (keyCode == 101) {  //5
    Rr1+=10;
    keyCode = 0;
  }
  if (keyCode == 102) {  //6
    Rr1-=10;
    keyCode = 0;
  }
  if (keyCode == 103) {  //7
    Rr2+=10;
    keyCode = 0;
  }
  if (keyCode == 104) {  //8
    Rr2-=10;
    keyCode = 0;
  }
  
  if (keyCode == 127) {
    canvas.clear();
    r1 = 250;  
    r2 = 250;
    m1 = 5;    
    m2 = 5;
    a1 = PI/2;
    a2 = PI/2;
    a1_v = -0.000f;
    a2_v = -0.000f;
    g = 0.98f;  
    R1 = 250;  
    R2 = 250;
    M1 = 5;    
    M2 = 5;
    A1 = PI/2;
    A2 = PI/2;
    A1_v = -0.000f;
    A2_v = -0.000f;
    Rr1 = 250;  
    Rr2 = 250;
    Mm1 = 5;    
    Mm2 = 5;
    Aa1 = PI/2;
    Aa2 = PI/2;
    Aa1_v = -0.000f;
    Aa2_v = -0.000f;
    a1_a = 0;
    a2_a = 0;
    A1_a = 0;
    A2_a = 0;
    Aa1_a = 0;
    Aa2_a = 0;
  flag = false;    
  }
  
  /*------------------draw the curve------------------*/
  if (keyCode == 121 || keyCode == 122 || keyCode == 123 ) {
    a1_v += a1_a;
    a2_v += a2_a;
    a1 += a1_v;
    a2 += a2_v;

    A1_v += A1_a;
    A2_v += A2_a;
    A1 += A1_v;
    A2 += A2_v;

    Aa1_v += Aa1_a;
    Aa2_v += Aa2_a;
    Aa1 += Aa1_v;
    Aa2 += Aa2_v;

    canvas.beginDraw();
    canvas.translate(cx, cy);


    if (frameCount > 1 && flag == true) {
      canvas.stroke(200, 255, 255);
      canvas.line(px2, py2, x2, y2);
      if (frameCount > 1 && flag == true && (keyCode == 122 || keyCode == 123)) {
        canvas.stroke(255, 255, 200);
        canvas.line(Px2, Py2, X2, Y2);
        if (frameCount > 1 && flag == true && keyCode == 123) {
          canvas.stroke(255, 200, 255);
          canvas.line(Ppx2, Ppy2, Xx2, Yy2);
        }
      }
    }
    canvas.endDraw();

    px2 = x2;
    py2 = y2;
    Px2 = X2;
    Py2 = Y2;
    Ppx2 = Xx2;
    Ppy2 = Yy2;
    flag = true;
  }
}
  public void settings() {  size(1600, 900); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DoublePendulum" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
