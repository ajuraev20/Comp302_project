package gameObjects;

import java.awt.Graphics2D;
import java.awt.Point;

import gameLogic.Game;

public class Door extends GameObject{

	private boolean isOpen;
	private int currSpriteInd;
	
	private long startTime;
	private long interval = 10;
	
	public Door(int x, int y) {
		super("door");
		this.loadImgs("/images/door",4);
		
		currSpriteInd = 0;
		this.currSprite = sprites[currSpriteInd];
		
		
		setBoundsToSprite(x, y);
		isOpen = false;
		startTime = System.currentTimeMillis();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void openDoor(Boolean open) {
		isOpen = open;
		
	}
	protected void changeImg() {
		
		if(currSpriteInd != 3) {
			currSpriteInd++;
		}
		
		currSprite = sprites[currSpriteInd];
		rect.width = currSprite.getWidth();
		rect.height = currSprite.getHeight();
	}
	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		if(isOpen && System.currentTimeMillis() - startTime >= interval) {
			startTime = System.currentTimeMillis();			
			changeImg();
		}
		
		g2.drawImage(currSprite, rect.x, rect.y, null);

	}

}
