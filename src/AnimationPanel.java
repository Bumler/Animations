import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel {

	int vx,vy,h,w;
	
	public Timer timer;
	//private Explosion explo;
	Random rn = new Random();
	//private Ball ball;
	private Brick paddle = new Brick (125, 285, 35, Color.BLUE);
	boolean firstTime = true;
	Point p = new Point(vx, vy);
	ArrayList<Brick> brickList = new ArrayList<Brick>();
	
	//this a count of how many bricks have been destroyed
	int cleared = 0;
	
	private Ball ball = new Ball(5, Color.BLUE, 314, 245);
	private Explosion explo = new Explosion(314, 245);
	
	/**
	 * Create the panel.
	 */
	public AnimationPanel() {
		
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				paddle.moveTo(x);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		
		//paddle = new Brick (50,50, 20);
		
		timer = new Timer(50, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ball.move(); 
				explo.step();
				if (paddle.collide(ball.getPosition(),(Math.abs(vy)/2))){
					System.out.println("hit");
					ball.flipY();
				}
				
				for (Brick br : brickList){
					if (br.collide(ball.getPosition(),(Math.abs(vy)/2))){
						System.out.println("hit");
						ball.flipY();
						explode();
						br.destroy();
						cleared++;
					}
				}
				
				if (cleared == 12){
					nextLevel();
				}
				repaint();
			}}
				);
	}
	
	public void Start(){
		vx = rn.nextInt(6) - 3;
		while (vx == 0){
			vx = rn.nextInt(6) - 3;
		}
		vy = 5;
		System.out.println(vy);
		p = new Point(vx,vy);
		ball.setVelocity(p);
		timer.start();
	}
	
	public void nextLevel(){
		ball.setPosition(125, 180);
		vx = rn.nextInt(6) - 3;
		while (vx == 0){
			vx = rn.nextInt(6) - 3;
		}
		vy += 5;
		p = new Point(vx,vy);
		ball.setVelocity(p);
		
		cleared = 0;
		createBricks();
	}
	
	public void Stop(){
		timer.stop();
	}
	
	public void explode(){
		explo.explode(ball.getX(), ball.getY());
	}
	
	public void moveLeft(){
		paddle.moveLeft();
	}
	
	public void moveRight(){
		paddle.moveRight();
	}

	public void createBricks(){
		for (int i = 15; i <= 55; i += 20){
			for (int j = 30; j <= 210; j+= 60){
				Brick br = new Brick(j, i, 20);
				brickList.add(br);
			}
		}
	}
	
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		h = getHeight();
		w = getWidth();
		
		System.out.println(cleared);
		
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, w-1, h-1);
		
		if (firstTime){
			//we could set height and width diagonally here if we'd like
			createBricks();		
			ball.setPosition(125, 150);
			
			firstTime = false;
		}
		paddle.render(g2d);
		ball.render(g2d);
		for (Brick br : brickList){
			br.render(g2d);
		}
		explo.render(g2d);
	}
}
