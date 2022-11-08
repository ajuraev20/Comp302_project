package gameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import gameLogic.Game;

public class Wall extends GameObject{
	
	private static int WIDTH = 8, HEIGHT = 50;
	
	public Wall(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT);
		this.color = Color.black;
		buildable = false;


		// TODO Auto-generated constructor stub
	}
	
	public Wall(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		this.color = Color.black;
		buildable = false;
	}

	@Override
	public void onClick(Point p,  Game game) {
		// TODO Auto-generated method stub
//		if(clickable) {
//			if(buildable) {
//				Wall wall = new Wall("wall", p.x - (p.x - rect.x), p.y - (p.y - rect.y));
//				wall.setClickable(true);
//				wall.setMoveable(true);
//				
//				game.buildNewObject(wall);
//			}else {
//				if(moveable) {
//					if(!pressed) {
//						xDstFromMouse = p.x - rect.x;
//						yDstFromMouse = p.y - rect.y;
//						pressed = true;
//					}
//					this.rect.x = p.x - xDstFromMouse;
//					this.rect.y = p.y - yDstFromMouse;
//				}
//				
//			}
//		}
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(color);
		g2.fillRect(getRect().x, getRect().y, getRect().width, getRect().height);
	}
	
	
	

	
	

}
