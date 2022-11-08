package gameLogic;

import java.awt.Graphics2D;

import gameObjects.Door;
import gameObjects.GameObject;

public class LevelScene extends Scene{

	
	public LevelScene(String name, Game game) {
		super(name, game);
		loadImg("/images/wood_tile");
		
	}
	
	@Override
	public void renderScene(Graphics2D g2) {
		// TODO Auto-generated method stub
	
		
		for(int i = 0;i < 16; i++) {
			for(int j = 0;j < 16; j++) {
			g2.drawImage(floorTile, i*floorTile.getWidth()+55, j*floorTile.getWidth()+105, null);
			}
		}
		
		renderWalls(g2);
		renderNotWalls(g2);
		
	}
	
	public Door getDoor() {
		for(GameObject obj: gameObjects) {
			if(obj instanceof Door) {
				return (Door)obj;
			}
		}
		
		return null;
		
	}
	
	

}
