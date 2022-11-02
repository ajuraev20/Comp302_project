package GameObjects;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import GUI.MainGamePanel;
import GameLogic.Game;



public class Player extends GameObject {
	
	private int ms = 4;
	private int dx;
	private int dy;

	private static int WIDTH = 20, HEIGHT = 20;


	
	public Player(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT);
		this.dx = 0;
		this.dy = 0;

		this.color = Color.RED;
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
			int rayWidth = 1;
			
			Rectangle botRay = new Rectangle(rect.x + rect.width/2,rect.y + rect.height,1,rayWidth);
			Rectangle topRay = new Rectangle(rect.x + rect.width/2,rect.y - rayWidth,1,rayWidth);
			Rectangle leftRay = new Rectangle(rect.x - rayWidth,rect.y + rect.height/2,rayWidth,1);
			Rectangle rightRay = new Rectangle(rect.x + rect.width,rect.y + rect.height/2,rayWidth,1);

			//System.out.println(topRay);
			if(obj != this) {
				if(rightRay.intersects(obj.getRect())) {
					rect.x = obj.getRect().x-rect.width;
					System.out.println("1");
				}else if(leftRay.intersects(obj.getRect())) {
					rect.x = obj.getRect().x + obj.getRect().width;
					System.out.println("2");
				}else if(botRay.intersects(obj.getRect())) {
					rect.y = obj.getRect().y - rect.height;
					System.out.println("down");
				}else if(topRay.intersects(obj.getRect())) {
					rect.y = obj.getRect().y + obj.getRect().height;
					System.out.println("4");
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
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		
	}
	

}
