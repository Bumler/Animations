import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import sun.audio.*;
import java.io.*;
//import javazoom.jl.player.Player;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel {

	int vx,vy,h,w;
	
	public Timer timer;
	//private Explosion explo;
	Random rn = new Random();
	//private Ball ball;
	private Brick paddle = new Brick (120, 285, 35, Color.BLUE);
	private Brick floor = new Brick (120, 312, 122, Color.RED);
	boolean firstTime = true;
	Point p = new Point(vx, vy);
	ArrayList<Brick> brickList = new ArrayList<Brick>();
	boolean pause = false;
	int pauseCount = 0;
	int lives = 3;
	int level = 1;
	int time = 0;
	boolean firstStart = true;
	
	InputStream test; 
	AudioStream BGM = null;
	
	MainApp f;
	
	//this a count of how many bricks have been destroyed
	int cleared = 0;
	
	private Ball ball = new Ball(5, Color.BLUE, 314, 240);
	private Explosion explo = new Explosion(314, 240);
	
	/**
	 * Create the panel.
	 */
	public AnimationPanel(MainApp f) {
		this.f = f;
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
				if (pause){
					pauseCount++;
					if (pauseCount >= 10){
						setPause(false);
					}
				}
				
				if (time == 6280){
					music();
				}
				
				else{
					ball.move(); 
					explo.step();
				if (paddle.collide(ball.getPosition(),(Math.abs(vy)/2))){
					ball.flipY();
				}
				
				if (floor.collide(ball.getPosition(),(Math.abs(vy)/2))){
					ball.setPosition(125, 75);
					ball.flipY();
					setPause(true);
					lives--;
					if(lives > 0){
						f.setLives(lives);
					}
					else{
						f.setLevel("You Lose, loser");
						timer.stop();
					}
				}
				
				for (Brick br : brickList){
					if (br.collide(ball.getPosition(),(Math.abs(vy)/2))){
						ball.flipY();
						explode();
						br.destroy();
						cleared++;
					}
				}
				
				if (cleared == 12){
					nextLevel();
					level++;
					if (level <= 5){
					f.setLevel(level);
					}
					
					else{
						explode();
						f.setLevel("You Win");
					}
				}
				time++;
				repaint();
			}}}
				);
	}
	
	//http://stackoverflow.com/questions/3541670/my-app-throws-java-io-ioexception-could-not-create-audiodata-object
	public void Start(){
		if (firstStart){
			music();
			vx = rn.nextInt(6) - 3;
			while (vx == 0){
				vx = rn.nextInt(6) - 3;
			}
			vy = 5;
			//System.out.println(vy);
			p = new Point(vx,vy);
			ball.setVelocity(p);
			firstStart = false;
		}
		timer.start();
	}
	
	public void music(){
        AudioPlayer MGP = AudioPlayer.player;
        AudioData MD;
        
        if (BGM != null)
        {
            AudioPlayer.player.stop();
            AudioPlayer.player.stop(BGM);
            System.out.println("stopping");
        }

        ContinuousAudioDataStream loop = null;

        try
        {
            test = new FileInputStream("./Earthbound.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
	}
	
	public void nextLevel(){
		ball.setPosition(125, 75);
		vx = rn.nextInt(8) - 3;
		while (vx == 0){
			vx = rn.nextInt(6) - 3;
		}
		vy += 5;
		p = new Point(vx,vy);
		ball.setVelocity(p);
		
		cleared = 0;
		createBricks();
		setPause(true);
		//music();
	}
	
	public void setPause(boolean tf){
		pauseCount = 0;
		if(tf){
			pause = true;
		}
		else{
			pause = false;
		}
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
	
	public void reset(){
		firstStart = true;
		paddle = new Brick (120, 285, 35, Color.BLUE);
		firstTime = true;
		p = new Point(vx, vy);
		brickList = new ArrayList<Brick>();
		pause = false;
		pauseCount = 0;
		lives = 3;
		level = 1;
		time = 0;
		
		
		Start(); 
	}
	
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		h = getHeight();
		w = getWidth();
		
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
		floor.render(g2d);
		for (Brick br : brickList){
			br.render(g2d);
		}
		explo.render(g2d);
	}
}
