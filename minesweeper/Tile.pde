
class Tile{
int x0,y0,x1,y1;
int bombcount;
PImage content;
boolean show,
        bomb,
        flag;

Tile(int x,int y){
    x0 = x;
    y0 = y;
    x1 = x0 + 32;
    y1 = y0 + 32; 
    show = false;
    bomb = false;
    flag = false;
  }
  
void setcontent(PImage im){
  content = im;
}

void getcontent(){
image(content,x0,y0);
}

Boolean collision(int mx,int my){
  
  if(mx>=x0 && mx<=x1 && my>=y0 && my<=y1 ){
      return true;
  }
  
 return false;
}

}
