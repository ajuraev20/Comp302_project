package gameLogic;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import gameObjects.GameObject;
import gameObjects.Player;
import gameObjects.Wall;
import gui.GameFrame;

public class Game {
	
	private static Timer timer;
	private GameFrame mainFrame;
	private GameObject selectedGameObject;
	private SceneManager sceneManager;

	public Game() {		
		sceneManager = new SceneManager(this);

		mainFrame = new GameFrame(this);

		GameKeyListener listener = new GameKeyListener();
		mainFrame.addKeyListener(listener);
		mainFrame.getComponent(0).addMouseListener(new GameMouseListener());
		
		
		startTimer();
		
		
	}
	
	private void tick() {
		mainFrame.render();
		
		
		getPlayer().move(this);
		moveSelectedObject();
		
		
	}
	
	private void moveSelectedObject() {
		if(selectedGameObject != null) {	
			selectedGameObject.onClick(getMouseLoc(), this);;
		}
	}
	
	private Point getMouseLoc() {
		return new Point(MouseInfo.getPointerInfo().getLocation().x -mainFrame.getComponent(0).getLocationOnScreen().x,
				MouseInfo.getPointerInfo().getLocation().y -mainFrame.getComponent(0).getLocationOnScreen().y);
	}
	
	private void startTimer() {
		
		timer = new Timer(10, new ActionListener() {
		       public void actionPerformed(ActionEvent e) // will run when the timer fires
		 	   {
		    	   tick();
		 	   }
		    	
		    });
		
		timer.start();
	}
	
	public Scene getCurrScene() {
		return sceneManager.getCurrScene();
	}
	
	public SceneManager getSceneManager() {
		return sceneManager;
	}
	
	public List<GameObject> getGameObjects() {
		return getCurrScene().gameObjects;
	}
	
	public boolean isColliding(GameObject obj) {
		for(GameObject obj1 : getCurrScene().gameObjects){
			if (obj != null && obj1.getRect().intersects(obj.getRect()) && !obj1.isBuildable()
					&& obj1 != obj) {
				return true;
				}
		}		
		return false;
	}
	
	public void buildNewObject(GameObject obj) {
		GameObject newObj = obj;
		newObj.setBuildable(false);
		
		selectedGameObject = newObj;
		getGameObjects().add(newObj);
		
	}
	
	public Player getPlayer() {
		for(GameObject obj:getCurrScene().gameObjects) {
			if(obj.name.equals("player")) return (Player) obj;
		}
		return null;
	}
	
	public boolean playerIsClose(GameObject obj) {
		int maxDst = 20;
		Rectangle playerRect = getPlayer().getRect();
		Rectangle biggerRect = new Rectangle(playerRect.x-maxDst/2, playerRect.y-maxDst/2, 
				playerRect.width+maxDst, playerRect.height+maxDst);
		
		return biggerRect.intersects(obj.getRect());
	}
	
	private GameObject getClickedObject(Point p) {
		
		if(getCurrScene().getClass() == BuildingModeScene.class) {
			for(GameObject obj : getCurrScene().gameObjects) {
				if(obj.getRect().contains(p) && obj.isClickable()) {
					//System.out.println(obj.name);
					return obj;
				}
			}
		}else {
			for(GameObject obj : getCurrScene().gameObjects) {
				if(obj.getRect().contains(p) && playerIsClose(obj) && obj.isClickable()) {
					//System.out.println(obj.name);
					return obj;
				}
			}
		}
		
		for(UIObject obj : getCurrScene().UIObjects) {
			if(obj.getRect().contains(p)) {
				//System.out.println(obj.name);
				return obj;
			}
		}
		
		return null;
	}
	
	public void foundKey() {
		// TODO Auto-generated method stub
		//System.out.println("Found the key");
		((LevelScene) getCurrScene()).getDoor().openDoor(true);
		
	}
	
	private void removeCollidingObj() {
		if(isColliding(selectedGameObject)) {
			getSceneManager().getCurrScene().gameObjects.remove(selectedGameObject);
		}
		
	}
	
	private void clearSelectedObj() {
		
		if(selectedGameObject != null) 	selectedGameObject.release();
		selectedGameObject = null;
	}
	
	private class GameKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				getPlayer().setDx(-1);
				getPlayer().setDy(0);
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				getPlayer().setDx(1);
				getPlayer().setDy(0);
			}else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				getPlayer().setDy(-1);
				getPlayer().setDx(0);
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				getPlayer().setDy(1);
				getPlayer().setDx(0);
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				getPlayer().setDx(0);
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				getPlayer().setDx(0);
			}else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				getPlayer().setDy(0);
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				getPlayer().setDy(0);
			}
		}
		
	}

	private class GameMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			selectedGameObject = getClickedObject(getMouseLoc());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			removeCollidingObj();
			clearSelectedObj();

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	
}
