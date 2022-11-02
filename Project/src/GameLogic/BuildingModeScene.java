package GameLogic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameObjects.GameObject;
import GameObjects.Player;
import GameObjects.Wall;

public class BuildingModeScene extends Scene{
	
	public int levelCount;
	
	public BuildingModeScene(String name) {
		super(name);
		gameObjects = new ArrayList<GameObject>();
		UIObjects = new ArrayList<UIObject>();
		levelCount = 1;
		
		Wall wall = new Wall("wall", 640,150, true);
		wall.setBuildingMode(true);
		gameObjects.add(wall);
		
		gameObjects.add(new Player("player", 100,120));
		
		SaveButtonUI saveButton = new SaveButtonUI("SAVE", 600,20);
		
		UIObjects.add(saveButton);

		buildWalls();
		
	}
	
	public void resetBuiltObjects() {
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(new Player("player", 100,120));

		Wall wall = new Wall("wall", 640,150, true);
		wall.setBuildingMode(true);
		gameObjects.add(wall);
		
		levelCount += 1;
		
		buildWalls();
	}
	
	public Player getPlayer() {
		for(GameObject obj:gameObjects) {
			if(obj.name.equals("player")) return (Player) obj;
		}
		return null;
	}
	
	@Override
	public void renderScene(Graphics2D g2) {
		// TODO Auto-generated method stub
		
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.BOLD, 40));
		g2.drawString("Building Mode", 275, 50);
		
		g2.drawRect(600, 100, 150, 400);
		
		g2.drawString("Level " + levelCount, 250, 90);
		
		for (GameObject obj : gameObjects) {
			g2.setColor(obj.color);
			g2.fillRect(obj.getRect().x, obj.getRect().y, obj.getRect().width, obj.getRect().height);
		}
		
		for (UIObject obj : UIObjects) {
			g2.setColor(obj.color);
			g2.fillRect(obj.getRect().x, obj.getRect().y, obj.getRect().width, obj.getRect().height);
			g2.setColor(Color.black);
			g2.drawString(obj.name, obj.getRect().x, obj.getRect().y+50);
		}
		
		renderRays(g2);
		
		

		
	}
	
	private void renderRays(Graphics2D g2) {
		g2.setColor(Color.blue);
		Rectangle rect = getPlayer().getRect();
		int rayWidth = 5;

		g2.drawRect(rect.x + rect.width/2,rect.y + rect.height,1,rayWidth);
		g2.drawRect(rect.x + rect.width/2,rect.y - rayWidth,1,rayWidth);
		g2.drawRect(rect.x - rayWidth,rect.y + rect.height/2,rayWidth,1);
		g2.drawRect(rect.x + rect.width,rect.y + rect.height/2,rayWidth,1);
	}
	
	private void buildWalls() {
		Wall wallWest = new Wall("wall",50,100,5,400, false);
		Wall wallEast = new Wall("wall",500,100,5,400, false);
		Wall wallNorth = new Wall("wall",50,100,450,5, false);
		Wall wallSouth = new Wall("wall",50,495,450,5, false);

		
		gameObjects.add(wallWest);
		gameObjects.add(wallEast);
		gameObjects.add(wallNorth);
		gameObjects.add(wallSouth);
	}

}
