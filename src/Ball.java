import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball {
	
	private int x,y;
	private int radius;
	private int h,w;
	Color c;
	Point v;
	
	public Ball (int radiusIn, Color c, int height, int width){
		radius = radiusIn;
		x = radiusIn;
		y = radiusIn;
		
		this.c = c;
		
		h = height;
		w = width;
	}
	
	public Ball (int radiusIn, Color c, int height, int width, int startX, int startY){
		radius = radiusIn;
		
		this.c = c;
		
		h = height;
		w = width;
		
		x = startX;
		y = startY;
	}
	
	public void setVelocity(Point vIn){
		v = vIn;
	}
	
	public void move(){
		x += v.x;
		y += v.y;		//this detects collision		
		if((x<radius) || (x>w-radius)) 
		{flipX();}
	if((y<radius) || (y>h-radius)) 
		{flipY();}
	}
	
	public void flipX(){
		v.x = -(v.x);
	}
	
	public void flipY(){
		v.y = - (v.y);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int[] getPosition(){
		int[] cords = {x , y};
		return cords;
	}
	
	public void render(Graphics g2d){
		g2d.setColor(c);
		g2d.fillOval(x - radius, y - radius, 2*radius, 2*radius);
	}
}
