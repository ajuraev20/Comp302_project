package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
	
	private Scene currentScene;
	private List<Scene> scenes;
	
	public SceneManager() {
		scenes = new ArrayList<Scene>();
		scenes.add(new BuildingModeScene("building mode"));
		
		
		currentScene = scenes.get(0);
	}
	
	public Scene getCurrScene() {
		return currentScene;
	}
	
	public List<Scene> getScenes(){
		return scenes;
	}

}
