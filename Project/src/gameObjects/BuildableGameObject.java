package gameObjects;

import java.awt.Point;

import gameLogic.Game;

public abstract class BuildableGameObject extends GameObject{

	protected int xDstFromMouse, yDstFromMouse;
	protected boolean hasKey;

	
	public BuildableGameObject(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		pressed = false;
		hasKey = false;

		// TODO Auto-generated constructor stub
	}
	
	public BuildableGameObject(String name) {
		super(name);
		pressed = false;
		hasKey = false;
		
	}
	
	public void placeKey() {
		hasKey = true;
	}

	


	
	

}
