package gameLogic;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
	
	private List<Scene> scenes;
	private int currentScene;
	
	
	public SceneManager(Game game) {
		scenes = new ArrayList<Scene>();
		scenes.add(new BuildingModeScene("building mode", game));
		
		
		currentScene = 0;
	}
	
	public Scene getCurrScene() {
		return scenes.get(currentScene);
	}
	
	public void setCurrScene(int scene) {
		currentScene = scene;
	}
	
	public Scene getScene(String sceneName) {
		
		return scenes.stream().filter(s -> s.getName().equals(sceneName)).findFirst().orElse(null);
		
	}
	
	public void moveToNextLevel() {
		
		if(currentScene <= 5) {
			currentScene++;
		}else {
			System.out.println("you won!");
		}
	}
	
	public List<Scene> getScenes(){
		return scenes;
	}

}
