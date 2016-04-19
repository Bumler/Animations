import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Explosion {
	
	int x,y,h,w;
	
	ArrayList<ArrayList<Ball>> explodeList = new ArrayList<ArrayList<Ball>>();
	ArrayList<Ball> test = new ArrayList<Ball>();
	
	
	public Explosion(int h, int w){
		this.h = h;
		this.w = w;
		for (int j = 0; j < 8; j ++){
			ArrayList<Ball> e = new ArrayList<Ball>();
			explodeList.add(e);
		}
	}
	
	public void explode (int xIn, int yIn){
		
		for (int j = 0; j < explodeList.size(); j++){
				for (int i = -100; i < 1; i+= 25){
					Ball b = new Ball(2, Color.RED, h,w,xIn + 0,yIn + i);
					explodeList.get(j).add(b);
				}
			}
	
		int numBalls = 25;
		int it = 0;
		System.out.println(it);
		//for the north and south orientations
			for (int i = -100; i < 1; i+= numBalls){
				Ball b = new Ball(2, Color.RED, h,w,xIn + 0,yIn + i);
				explodeList.get(it).add(b);
				explodeList.get(it + 4).add(b);
			}
			
		//for the north east and south west orientations
		it++;
		System.out.println(it);
			for (int i = -100; i < 1; i+= numBalls){
				Ball b = new Ball(2, Color.RED, h,w,xIn - i,yIn + i);
				explodeList.get(it).add(b);
				explodeList.get(it + 4).add(b);
			}
			
		//for the east and west orientations
		it++;
		System.out.println(it);
			for (int i = -100; i < 1; i+= numBalls){
				Ball b = new Ball(2, Color.RED, h,w,xIn + i,yIn + 0);
				explodeList.get(it).add(b);
				explodeList.get(it + 4).add(b);
			}
			
		//for the south east and north west orientations
		it++;
		System.out.println(it);
			for (int i = -100; i < 1; i+= numBalls){
				Ball b = new Ball(2, Color.RED, h,w,xIn + i,yIn + i);
				explodeList.get(it).add(b);
				explodeList.get(it + 4).add(b);
			}
	}
	
	public void step (){
		int xPos,yPos;
		int it = -1;
		
		xPos = 0;
		yPos = -10;
		it++;
		System.out.println(explodeList.get(it).size());
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos, yPos));
		}
		
		//this is for the north east column
		xPos = 10;
		yPos = -10;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
		
		//this is for the east column
		xPos = 10;
		yPos = 0;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
		
		//this is for the south east column
		xPos = 10;
		yPos = 10;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
		
		//this is for the south column
		xPos = 0;
		yPos = 10;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
		
		//this is for the south west column
		xPos = -10;
		yPos = 10;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
		
		//this is for the west column
		xPos = -10;
		yPos = 0;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
		
		//this is for the north west and final column
		xPos = -10;
		yPos = -10;
		it++;
		for (Ball b : explodeList.get(it)){
			b.move(new Point(xPos,yPos));
		}
	}
	
	public void render(Graphics g2d){
		for (int j = 0; j < explodeList.size(); j++){	
			for (Ball b : explodeList.get(j)){
				b.render(g2d);
			}
		}
	}
}
