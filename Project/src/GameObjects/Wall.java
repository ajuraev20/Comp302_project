package GameObjects;
import java.awt.Color;
import java.awt.Point;

import GameLogic.Game;

public class Wall extends GameObject{
	
	private static int WIDTH = 8, HEIGHT = 50;
	private int xDstFromMouse, yDstFromMouse;
	private boolean clickable;
	
	public Wall(String name, int x, int y, boolean clickable) {
		super(name, x, y, WIDTH, HEIGHT);
		this.color = Color.black;
		this.clickable = clickable;
		pressed = false;
		buildingMode = false;

		// TODO Auto-generated constructor stub
	}
	
	public Wall(String name, int x, int y, int width, int height, boolean clickable) {
		super(name, x, y, width, height);
		this.color = Color.black;
		this.clickable = clickable;
		pressed = false;
		buildingMode = false;
	}

	@Override
	public void onClick(Point p,  Game game) {
		// TODO Auto-generated method stub
		if(clickable) {
			if(buildingMode) {
				Wall wall = new Wall("wall", p.x - (p.x - rect.x), p.y - (p.y - rect.y), true);
				game.buildNewObject(wall);
			}else {
				if(!pressed) {
					xDstFromMouse = p.x - rect.x;
					yDstFromMouse = p.y - rect.y;
					pressed = true;
				}
				this.rect.x = p.x - xDstFromMouse;
				this.rect.y = p.y - yDstFromMouse;
			}
		}
		//this.rect.setLocation(p);
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	

	
	

}
