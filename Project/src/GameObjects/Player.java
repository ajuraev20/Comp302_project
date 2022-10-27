package GameObjects;
import java.awt.Color;
import java.awt.Point;
import java.util.List;

import GUI.MainGamePanel;



public class Player extends GameObject {
	
	private int ms = 5;
	private int dx;
	private int dy;

	private static int WIDTH = 20, HEIGHT = 20;


	
	public Player(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT);
		this.dx = 0;
		this.dy = 0;

		this.color = Color.BLACK;
		// TODO Auto-generated constructor stub
		
	}
	
	public void move(List<GameObject> gameObjects) {
		rect.x += ms*dx;
		rect.y += ms*dy;

		stayInFrame();
		checkCollision(gameObjects);
		
		
		
		
	}
	
	private void checkCollision(List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		for(GameObject obj : gameObjects) {
			if(obj != this && this.rect.intersects(obj.getRect())) {
				if(rect.x+rect.width > obj.getRect().x && dx > 0) {
					rect.x = obj.getRect().x-rect.width;
				}else if(rect.x < obj.getRect().x + obj.getRect().width && dx < 0) {
					rect.x = obj.getRect().x + obj.getRect().width;
				}
				
				else if(rect.y+rect.height > obj.getRect().y && dy > 0) {
					rect.y = obj.getRect().y - rect.height;
				}else if(rect.y < obj.getRect().y + obj.getRect().height && dy < 0) {
					rect.y = obj.getRect().y + obj.getRect().height;
				}
			}
		}
		
	}

	private void stayInFrame() {
		// TODO Auto-generated method stub
		if(rect.x+rect.width > MainGamePanel.WIDTH) {
			rect.x = MainGamePanel.WIDTH-rect.width;
		}else if(rect.x < 0) {
			rect.x = 0;
		}
		
		if(rect.y+rect.height > MainGamePanel.HEIGHT) {
			rect.y = MainGamePanel.HEIGHT-rect.height;
		}else if(rect.y < 0) {
			rect.y = 0;
		}
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}

	@Override
	public void onClick(Point p) {
		// TODO Auto-generated method stub
		
	}
	

}
