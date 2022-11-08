package gameLogic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gameObjects.BookShelf;
import gameObjects.BuildableGameObject;
import gameObjects.Desk;
import gameObjects.Door;
import gameObjects.GameObject;
import gameObjects.Player;
import gameObjects.TallWall;
import gameObjects.Wall;

public class BuildingModeScene extends Scene{
	
	public int levelNum;
	private Map<Integer, Integer> levelMinObjMap;
	
	
	public BuildingModeScene(String name, Game game) {
		super(name, game);
		UIObjects = new ArrayList<UIObject>();
		levelNum = 0;
		
		loadImg("/images/wood_tile");

//		Wall wall = new Wall("wall", 640,150);
//		wall.setBuildable(true);
//		wall.setClickable(true);
//		wall.setMoveable(false);
//		gameObjects.add(wall);
		
		resetBuiltObjects();
		initLevelObjCntMap();
		
		//gameObjects.add(new Player("player", 100,120));
		
		SaveButtonUI saveButton = new SaveButtonUI("SAVE", 600,20, this);
		
		UIObjects.add(saveButton);

		buildWalls();
		
	}
	
	private void initLevelObjCntMap() {
		levelMinObjMap = new HashMap<Integer,Integer>();
		levelMinObjMap.put(1, 2);
		levelMinObjMap.put(2, 2);
		levelMinObjMap.put(3, 2);
		levelMinObjMap.put(4, 2);
		levelMinObjMap.put(5, 2);
		levelMinObjMap.put(6, 2);



	}
	
	public void resetBuiltObjects() {
		gameObjects = new ArrayList<GameObject>();

		gameObjects.add(new Player("player", 100,200));

		Desk desk = new Desk(640, 200);
		desk.setBuildable(true);
		desk.setClickable(true);
		gameObjects.add(desk);
		
		BookShelf bookShelf = new BookShelf(640, 300);
		bookShelf.setBuildable(true);
		bookShelf.setClickable(true);
		gameObjects.add(bookShelf);
		
		Door door = new Door(350,196-48);
		door.setClickable(false);
		gameObjects.add(door);
		
		levelNum += 1;
		
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
		
		for(int i = 0;i < 16; i++) {
			for(int j = 0;j < 16; j++) {
			g2.drawImage(floorTile, i*floorTile.getWidth()+55, j*floorTile.getWidth()+105, null);
			}
		}
		//g2.drawImage(floorTile, 55, 105, null);
		
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.BOLD, 40));
		g2.drawString("Building Mode", 275, 50);
		
		g2.drawRect(600, 100, 150, 400);
		
		g2.drawString("Level " + levelNum, 250, 90);
		
		renderWalls(g2);
		renderNotWalls(g2);
		
		for (UIObject obj : UIObjects) {
			obj.render(g2);
			
		}
		
		
		
		//renderRays(g2);
		
	}
	
	private void renderRays(Graphics2D g2) {
		g2.setColor(Color.blue);
		Rectangle rect = getPlayer().getRect();
		int rayWidth = 1;

		g2.drawRect(rect.x + rect.width/2,rect.y + rect.height,1,rayWidth);
		g2.drawRect(rect.x,rect.y + rect.height,1,rayWidth);
		g2.drawRect(rect.x + rect.width,rect.y + rect.height,1,rayWidth);
		
		
		g2.drawRect(rect.x + rect.width/2,rect.y - rayWidth,1,rayWidth);
		g2.drawRect(rect.x - rayWidth,rect.y + rect.height/2,rayWidth,1);
		
		
		g2.drawRect(rect.x + rect.width,rect.y + rect.height/2,rayWidth,1);
		g2.drawRect(rect.x + rect.width,rect.y,rayWidth,1);
		g2.drawRect(rect.x + rect.width,rect.y + rect.height,rayWidth,1);

	}
	
	private void buildWalls() {
		Wall wallWest = new Wall("wall",50,100,5,517);
		Wall wallEast = new Wall("wall",562,100,5,522);
		//Wall wallNorth = new Wall("wall",50,100,517,50);
		Wall wallSouth = new Wall("wall",50,617,517,5);
		//TallWall wallNorth = new TallWall(50,100);
		
		for(int i=0;i<16;i++) {
			gameObjects.add(new TallWall(TallWall.WIDTH*i + 50, 100));
		}
		
		gameObjects.add(wallWest);
		gameObjects.add(wallEast);
		//gameObjects.add(wallNorth);
		gameObjects.add(wallSouth);
	}
	
	public void saveLevel() {
		
		if(levelNum < 2) {
			//System.out.println(getStaticObjCnt());
			//levelMinObjMap.get(levelNum)
			if(getStaticObjCnt() >= levelMinObjMap.get(levelNum)) {
				addLeveltoSceneManager();
			}else {
				System.out.println("place more objs");
			}
			
		}else {	
			//game.getSceneManager().getScenes().stream().forEach(s -> System.out.println(s.getName()));
			if(getStaticObjCnt() >= levelMinObjMap.get(levelNum)) {
				addLeveltoSceneManager();
				game.getSceneManager().setCurrScene(1);

			}else {
				System.out.println("place more objs");
			}
		}
		
	}
	
	private void addLeveltoSceneManager() {
		placeKeyRandomly();
		
		LevelScene newScene = new LevelScene("level "+levelNum, game);
		newScene.saveGameObjects(gameObjects);
		
		game.getSceneManager().getScenes().add(newScene);
		resetBuiltObjects();
	}
	
	private void placeKeyRandomly() {
		int rand = (int) Math.floor(Math.random()*(gameObjects.size()));
		GameObject obj = gameObjects.get(rand);
		
		while( !(obj instanceof BuildableGameObject) || obj.isBuildable()) {
			//System.out.println("object "+obj.name+" "+!(obj instanceof BuildableGameObject)+" buildable "+obj.isBuildable());
			rand = (int) Math.floor(Math.random()*(gameObjects.size()));
			obj = gameObjects.get(rand);
		}
		//System.out.println("object "+obj.name+" "+!(obj instanceof BuildableGameObject)+" buildable "+obj.isBuildable());

		BuildableGameObject randomBuildable = (BuildableGameObject) obj;
		//System.out.println(randomBuildable.name);
		randomBuildable.placeKey();
		
		
	}
	
	private int getStaticObjCnt() {
		int cnt = 0;
		
		for(GameObject obj : gameObjects) {
			if(obj instanceof BuildableGameObject && !obj.isBuildable()) {
				cnt++;
			}
		}
		return cnt;
		
	}

}
