import bitcraftlab.desktopmagic.*;

DesktopMagic desktop;
boolean muted;
int level;

void setup() {
    
  size(50, 400);
  noStroke();
  
  desktop = new DesktopMagic(this);
  desktop.hideBezel();
  //desktop.showDebug();
  level = 50;

}

void draw() {
  background(255);
  fill(muted ? #ff0000 : 100);
  float y = map(level, 0, 100, height, 0);
  rect(0, y, width, height - y);  
}

void desktopVolumeUp() {
  muted = false;
  level += 1;
}

void desktopVolumeDown() {
  muted = false;
  level -= 1; 
}

void desktopMute() {
  muted = !muted;
}
