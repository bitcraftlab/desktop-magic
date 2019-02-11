import bitcraftlab.desktopmagic.*;

DesktopMagic md;

void setup() {
  size(400,400);
  smooth();
  
  md = new DesktopMagic(this);
  
  PFont font = createFont("",40);
  textFont(font);
}

void draw() {
  background(0);
  fill(255);
}
