package GameLogic;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import GameObjects.GameObject;

public abstract class UIObject extends GameObject{

	protected final float delay = 250; //ms
	protected long lastClickTime;
	
	public UIObject(String text, int x, int y, int width, int height) {
		super(text, x, y, width, height);
		lastClickTime = System.currentTimeMillis();

	}
	
	protected boolean canBeClicked() {
		float timePassed =  (System.currentTimeMillis()-lastClickTime);
		
		//System.out.println(timePassed);

		if(timePassed >= delay) {
			lastClickTime = System.currentTimeMillis();
			return true;
		}else {
			return false;
		}
		
		
	}


}
