package GameObjects;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import GameLogic.Game;

public abstract class GameObject {
	
	public String name;
	protected Rectangle rect;
	protected boolean pressed;
	protected boolean buildingMode;


	public Color color;

	
	public GameObject(String name, int x, int y, int width, int height) {
		this.name = name;
		this.rect = new Rectangle(x,y, width, height);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setPosition(Rectangle p) {
		rect = p;
	}
	
	public void released() {
		pressed = false;
	}
	
	public void setBuildingMode(boolean buildingMode) {
		this.buildingMode = buildingMode;
	}
	
	public abstract void onClick(Point p, Game game);

}
