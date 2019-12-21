package brickbreaker;
import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	int map[][];
	int brickWidth;
	int brickHeight;
	Random r ;
	public  int bricks = 0 ;
	

	public MapGenerator(int row, int colum) {
		r = new Random();
		map = new int[row][colum];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = r.nextInt(2);
			
				if(map[i][j]>0) {
				bricks++;
			}
			
			}
		}
		brickWidth = 540 / colum;
		brickHeight = 150 / row;

	}

	public void draw(Graphics2D g2d) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]>0) {
					g2d.setColor(Color.white);
					g2d.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					g2d.setStroke(new BasicStroke(3));
					g2d.setColor(Color.black);
					g2d.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				
				}
			}
		}
	}
	public void setBrickValue(int value,int row , int colum)
	{
		map[row][colum]= value;
	}
}
