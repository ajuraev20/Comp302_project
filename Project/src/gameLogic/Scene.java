package gameLogic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import gameObjects.GameObject;
import gameObjects.TallWall;
import gameObjects.Wall;

public abstract class Scene {
	
	protected List<GameObject> gameObjects;
	protected List<UIObject> UIObjects;
	protected String name;
	protected Game game;
	
	protected BufferedImage floorTile;

	
	public Scene(String name, Game game) {
		this.name = name;
		this.game = game;
	}

	public abstract void renderScene(Graphics2D g2);

	protected void renderWalls(Graphics2D g2){
		gameObjects.stream().forEach(obj -> {
			if(obj.getClass() == Wall.class || obj.getClass() == TallWall.class) {
				obj.render(g2);
			}
		});
	}
	
	protected void renderNotWalls(Graphics2D g2) {
		gameObjects.stream().forEach(obj -> {
			if(obj.getClass() != Wall.class && obj.getClass() != TallWall.class) {
				obj.render(g2);
			}
		});
	}
	
	public void saveGameObjects(List<GameObject> gameObjects) {
		this.gameObjects = new ArrayList<>(gameObjects);
		removeBuildableObjects();
		freezeMoveableObjects();
		
		this.UIObjects = new ArrayList<>();
	}
	
	protected void loadImg(String url) {
		try {
				floorTile = ImageIO.read(getClass().getResource(url+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void removeBuildableObjects() {
		gameObjects = gameObjects.stream().filter(obj -> !obj.isBuildable()).collect(Collectors.toList());
	}
	
	private void freezeMoveableObjects() {
		gameObjects.stream().forEach(obj -> obj.setMoveable(false));
	}
	
	public String getName() {
		return name;
	}
}
