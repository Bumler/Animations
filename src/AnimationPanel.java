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
	boolean firstTime = true;
	Point p = new Point(vx, vy);
	
	/**
	 * Create the panel.
	 */
	public AnimationPanel() {
		
		timer = new Timer(1, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Henry is a cool guy");
				ball.move(p); 
				explo.step();
				
				repaint();
			}}
				);
	}
	
	public void Start(){
		vx = rn.nextInt(10) - 5;
		vy = rn.nextInt(10) - 5;
		p = new Point(vx,vy);
		timer.start();
	}
	
	public void Stop(){
		timer.stop();
	}
	
	public void explode(){
		explo.explode(ball.getX(), ball.getY());
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
		ball.render(g2d);
		explo.render(g2d);
	}
}
