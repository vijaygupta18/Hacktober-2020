import g4p_controls.*;

import javax.swing.JOptionPane;


ArrayList<PImage> tileset = new ArrayList();
ArrayList<Integer> bombpos = new ArrayList(); 
boolean end;
int c = 0,
    ls = 8,
    li = 8,
    nbomb = 10;

Tile face = new Tile(155,40); 
Tile btn = new Tile(120,350); 


Tile board[][];

void setup() {
  
  PImage im = loadImage("images/15.png");
  im.resize(112,40);
  board = new Tile [8][8];
  
  createGUI();
  loadtiles();
  setgame();

}
 
void draw() {
  background(#cdcecc);
  for(int i=0;i<=li-1;i++){
    for(int j=0;j<=ls-1;j++){
      board[i][j].getcontent();
    }  
   }
   
    face.getcontent();
    if(c == (li*ls) - bombpos.size()/2){
      face.setcontent(tileset.get(14));
      end = true;
    }
     
    
}

void mouseClicked(){
int mx = mouseX;
int my = mouseY;
int count = 0;
Tile t;
PImage edit;

if(!end){
  
  
  for(int i=0;i<=li-1;i++){
  for(int j=0;j<=ls-1;j++){
    
    t = board[i][j];
    
    if(t.collision(mx,my)){
      
       if(mouseButton == LEFT){
         
            if(t.bomb){
            
             t.setcontent(tileset.get(9));
             gameover();
             
            }else{
              if(!t.show){
                
                if(t.bombcount !=0)
                  c++;
                 show(i, j);
                 count = t.bombcount;
                 t.setcontent(tileset.get(count));
              }
            
            }
          
       }else{
          if(mouseButton == RIGHT && !t.show){
              if(!t.flag){
                t.setcontent(tileset.get(11));
                t.flag = true;
              }else{
                t.setcontent(tileset.get(10));
                t.flag = false;
        } 
       }
      }
      break;
     }
    }
   }
}
}
 
void loadtiles(){
  PImage img;
 for(int i=0;i<=14;i++){
      img = loadImage("images/"+i+".png");
        img.resize(32, 32);
        tileset.add(img);
 }
}


void create(){
  int x = 45; 
  int y = 80; 
  
   for(int i=0;i<=li-1;i++){
    for(int j=0;j<=ls-1;j++){
      
       Tile t = new Tile(x,y);
       t.setcontent(tileset.get(10));
        board[i][j]= t;
        x+=32;
           if(x>=(32*ls)+45)
            x = 45;
    }
    y+=32;
  }
  random_bomb();
}

void random_bomb(){
  ArrayList<String> points = new ArrayList();
  int i=0;
  int j=0;
  int count=0, k=0;
  String point;
  
  while(count<nbomb){
    i = (int) random(0,li-1);
    j  = (int) random (0,ls-1);
    point = i+","+j;
    if(!points.contains(point)){
      count+=1;
      points.add(point);
      bombpos.add(i);
      bombpos.add(j);
      board[i][j].bomb = true;
    }
  }
    
 
}

Tile search(int x, int y){
Tile t;
for(int i=0;i<=li-1;i++){
  for(int j=0;j<=ls-1;j++){
    t = board[i][j];
    if(t.collision(x,y)){
      return t;
    }
  }
}
return null;
}

 public void show(int i, int j){
   
        if(i < 0 || j < 0 || i > board.length-1 || j > board[0].length-1 || board[i][j].show || board[i][j].bomb){
          return;
  }else{
         if(!board[i][j].show && board[i][j].bombcount != 0){
                return;
      }
           board[i][j].show  = true;
           c++;
           board[i][j].setcontent(tileset.get(board[i][j].bombcount));
           show(i+1,j) ;   show(i-1,j);
           show(i,j+1);    show(i,j-1);
           show(i+1,j+1);  show(i+1,j-1) ;
           show(i-1,j+1);  show(i-1,j-1);

    }
}
            
void Minescount(int i, int j){
       int count = 0; 

       for(int r = i-1; r < i+2; r++){
           for(int c = j-1; c < j+2; c++){
               if(!(r < 0 || c < 0 || r > board.length-1 || c > board[r].length-1 ) && (board[r][c].bomb && !board[r][c].show )) 
                   count++;        
           }
        }
        board[i][j].bombcount = count;
    }
    
void mines(){
for(int i=0;i<=li-1;i++){
  for(int j=0;j<=ls-1;j++){
    Minescount(i,j);
  }
 }
}

void showbomb(){
  int x,y;
for(int i=0;i<bombpos.size();i+=2){
  x = bombpos.get(i);
  y = bombpos.get(i+1);
  board[x][y].setcontent(tileset.get(9));
}
}

void setgame(){
 int x, y;
  bombpos.clear();
  x = 45+((ls*32)+32);
  y = 120+(li*32);
  face.x0 = x/2 -16;
  face.setcontent(tileset.get(12));
  surface.setSize(x,y);
  create();
  mines();
  c = 0;
  end = false;
}

void gameover(){
showbomb();
face.setcontent(tileset.get(13));
end = true;
}
