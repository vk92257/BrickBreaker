package brickbreaker;
import java.awt.Color;

import javax.swing.JFrame;
public class Main{

	public Main() {
		
		JFrame obj = new JFrame();
		GamePlay gamePlay = new GamePlay();
		
		obj.setBounds(10,10,710,600);
		obj.setTitle("brick Breaker");
		obj.setVisible(true);
		obj.setResizable(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}

}
