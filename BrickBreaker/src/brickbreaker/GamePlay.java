package brickbreaker;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

public class GamePlay extends JPanel implements ActionListener , KeyListener {
	private boolean play= false;
	private int score= 0;
	private int totalBricks=21;
	private Timer timer ;
	private int delay = 8;
	int playerX = 310;
	int ballX= 120;
	int ballY= 350;
	int ballXdir= -1;
	int ballYdir= -2; 
	
	private MapGenerator map  ;
	
	
	public GamePlay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
@Override
	public void paint(Graphics g) {
		
		
		// backgroound	
		g.setColor(Color.black);
		g.fillRect(0,0,692,592);
		
		// drawing brics 
		map.draw((Graphics2D)g);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(690, 0, 3, 592);
		
		// setting score bar 
				g.setColor(Color.white);
				g.setFont(new Font("serif",Font.BOLD,25));
				g.drawString(" Score : "+score,570,30);
		
			// total bricks 
				g.setColor(Color.white);
				g.setFont(new Font("serif",Font.BOLD,25));
				g.drawString( "TOTAL BRICKS : "+map.bricks,30,30);
		
				
				
				//you won 

				if (map.bricks <= 0 || totalBricks <= 0 ) {
					
					play = false;
					ballXdir=0;
					ballYdir=0;
					g.setColor(Color.RED);
					g.setFont(new Font("serif",Font.BOLD,35));
					g.drawString("YOU WIN  " , 190, 300);
					
					g.setFont(new Font("serif", Font.BOLD,20));
					g.drawString(" PRESS ENTER TO RESTART  " , 230, 350);
				}
				
				
				if (ballY>570) {
					play = false;
					ballXdir=0;
					ballYdir=0;
					g.setColor(Color.RED);
					g.setFont(new Font("serif",Font.BOLD,35));
					g.drawString("GAME OVER (: " , 190, 300);
					
					g.setFont(new Font("serif", Font.BOLD,20));
					g.drawString(" PRESS ENTER TO RESTART  " , 230, 350);
				}
				
				// paddels
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		// ball 
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
		g.dispose();
		
		
		g.dispose();

}


@Override
public void keyReleased(KeyEvent arg0) {
}

@Override
public void keyTyped(KeyEvent arg0) {
}

@Override
	public void actionPerformed(ActionEvent t) {
		
		//System.out.println("run");
		timer.start();
		
		
		if(play) {
		
		ballX  +=  ballXdir;
		ballY  +=  ballYdir;
		if(ballX<0) {
			
			ballXdir  =  -ballXdir;
		}
		if(ballY<0) {
		
			ballYdir = -ballYdir;
		}
		if(ballX>=670) {
			ballXdir =-ballXdir;
		}
		
		if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,8)) )
		{
			ballYdir =  -ballYdir; 
		}
		
	A :	for (int i = 0; i < map.map.length; i++) {
			for (int j = 0; j <map.map[0].length; j++) {
				if (map.map[i][j]>0) {
					int brickX= j* map.brickWidth + 80 ;
					int brickY = i * map.brickHeight +50;
					int brickHeight = map.brickHeight;
					int brickWidth = map.brickWidth;
					Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
					Rectangle ballRect = new Rectangle(ballX,ballY,20,20);
					Rectangle brickRect = rect;
					if(ballRect.intersects(brickRect)) {
						map.setBrickValue(0, i, j);
						totalBricks--;
						map.bricks--;
						score +=5;
						
						
						if (ballX+19<=brickRect.x ||  ballX + 1>= brickRect.x + brickRect.width) {
							ballXdir = - ballXdir;
						}	
						else { 
							ballYdir = - ballYdir;
						}
						break A;
					}
				}
			}
		}
		
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			
			if(playerX>=590) {
				playerX=590;
			}
			else {
				moveRight();
			}
		}
	if(e.getKeyCode()==KeyEvent.VK_LEFT) {
		
		if(playerX<=10) {
			playerX=10;
		}
		else {
			moveLeft();
		}
	}

	if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		if (!play) {
			play= true;
			ballXdir=-1;
			ballYdir=-2;
			playerX = 310;
			ballX= 120;
			ballY= 350;
			score = 0;
			totalBricks=21;
			map= new MapGenerator(3,7);
			repaint();
		}
	}
	
	}
public void moveRight() {
	  play = true;
	playerX+=20;
}
public void moveLeft() {
	play = true;
	playerX-=20;
}
	
	
	
}
