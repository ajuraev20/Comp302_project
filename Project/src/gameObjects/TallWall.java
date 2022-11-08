package gameObjects;

import java.awt.Graphics2D;
import java.awt.Point;

import gameLogic.Game;

public class TallWall extends GameObject{

	public static final int WIDTH = 32, HEIGHT = 96;
	
	public TallWall(int x, int y) {
		super("tall wall");
		
		loadImg("/images/tallWall");
		setBoundsToSprite(x, y);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.drawImage(currSprite, rect.x, rect.y, null);

	}
	
	

}
