import java.awt.Graphics2D;

public class Brick {
	//creates a brick that extends evenly from a center point (x,y) based off the width
	int x,y,width;
	public Brick(int xIn, int yIn, int widthIn){
		x = xIn;
		y = yIn;
		
		width = widthIn;
	}
	
	public boolean collide (int[] cords, int jump){
		System.out.println(cords[1]+ " "+y+" "+jump);
		if (cords[1] <= y + jump && cords[1] >= y -jump){
			if (cords[0] >= x - width && cords[0] <= x+ width){
				return true;
			}
		}
		return false;
	}
	
	public void moveLeft(){
		x -= 20;
	}
	
	public void moveRight(){
		x += 20;
	}
	
	public void moveTo(int xIn){
		x = xIn;
	}
	
	public void render(Graphics2D g2d){
		g2d.drawLine(x-width, y, x+width, y);
	}
}
