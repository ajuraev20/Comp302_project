package gameLogic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import gameLogic.BuildingModeScene;

public class SaveButtonUI extends UIObject{

	BuildingModeScene buildingModeScene;
	
	public SaveButtonUI(String text, int x, int y, BuildingModeScene buildingModeScene) {
		super(text,x,y, 100,50);
		this.buildingModeScene = buildingModeScene;
		color = Color.orange;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		if(canBeClicked()) {
			//System.out.println("clicked");
			
			buildingModeScene.saveLevel();
		}
		
		
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(color);
		g2.fillRect(getRect().x, getRect().y, getRect().width, getRect().height);
		g2.setColor(Color.black);
		g2.drawString(name, getRect().x, getRect().y+50);
	}

}
