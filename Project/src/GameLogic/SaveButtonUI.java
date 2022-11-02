package GameLogic;

import java.awt.Color;
import java.awt.Point;
import GameLogic.BuildingModeScene;

public class SaveButtonUI extends UIObject{

	public SaveButtonUI(String text, int x, int y) {
		super(text,x,y, 100,50);
		color = Color.orange;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		if(canBeClicked()) {
			System.out.println("clicked");
			LevelScene newScene = new LevelScene("level 1");
			newScene.saveGameObjects(game.getCurrScene().gameObjects);
			BuildingModeScene buildingModeScene = (BuildingModeScene) game.getCurrScene();
			buildingModeScene.resetBuiltObjects();
			
			game.getSceneManager().getScenes().add(newScene);
		}
		
		
	}

}
