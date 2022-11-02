package GameLogic;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import GameObjects.GameObject;

public abstract class Scene {
	
	protected List<GameObject> gameObjects;
	protected List<UIObject> UIObjects;
	protected String name;
	
	public Scene(String name) {
		this.name = name;
	}

	public abstract void renderScene(Graphics2D g2);

	public void saveGameObjects(List<GameObject> gameObjects) {
		this.gameObjects = new ArrayList<>(gameObjects);
	}
}
