import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel {

	int vx,vy,h,w;
	
	public Timer timer;
	private Explosion explo;
	Random rn = new Random();
	private Ball ball;
	private Brick test;
	boolean firstTime = true;
	Point p = new Point(vx, vy);
	
	/**
	 * Create the panel.
	 */
	public AnimationPanel() {
		
		test = new Brick (50,50, 20);
		
		timer = new Timer(20, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ball.move(p); 
				explo.step();
				//System.out.println(ball.getY());
				if (test.collide(ball.getPosition(),vy*5)){
					System.out.println("hit");
					vx = -vx;
					vy = -vy;
					p = new Point (vx, vy);
				}
				
				repaint();
			}}
				);
	}
	
	public void Start(){
		vx = rn.nextInt(10) - 5;
		vy = rn.nextInt(10) - 5;
		System.out.println(vy);
		p = new Point(vx,vy);
		timer.start();
	}
	
	public void Stop(){
		timer.stop();
	}
	
	public void explode(){
		explo.explode(ball.getX(), ball.getY());
	}
	
	public void moveLeft(){
		test.moveLeft();
	}
	
	public void moveRight(){
		test.moveRight();
	}

	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		h = getHeight();
		w = getWidth();
			
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, w-1, h-1);
		
		if (firstTime){
			ball = new Ball(5, Color.BLUE, this.getHeight(), this.getWidth());
			explo = new Explosion(h,w);
			firstTime = false;
		}
		test.render(g2d);
		ball.render(g2d);
		explo.render(g2d);
	}
}
