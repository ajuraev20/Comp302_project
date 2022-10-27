import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;




public class Game {
	
	private static Timer timer;
	private List<GameObject> gameObjects;
	private GameFrame mainFrame;
	private GameObject selectedGameObject;

	public Game() {
		buildGame();

		
		mainFrame = new GameFrame(this);
		GameKeyListener listener = new GameKeyListener();
		mainFrame.addKeyListener(listener);
		mainFrame.getComponent(0).addMouseListener(new GameMouseListener());;
		
		
		startTimer();
		
		
	}
	
	private void buildGame() {
		Player player = new Player("player", 100, 100);
		
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(player);
		
		Wall wall1 = new Wall("wall",200,200);
		
		gameObjects.add(wall1);
	}
	
	private void tick() {
		mainFrame.update();
		getPlayer().move(gameObjects);
		moveSelectedObject();
		
		
	}
	
	private void moveSelectedObject() {
		if(selectedGameObject != null) {	
			selectedGameObject.onClick(getMouseLoc());;
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
	
	public List<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	
	public Player getPlayer() {
		for(GameObject obj:gameObjects) {
			if(obj.name.equals("player")) return (Player) obj;
		}
		return null;
	}
	
	private GameObject getClickedObject(Point p) {
		for(GameObject obj : gameObjects) {
			if(obj.getRect().contains(p)) {
				System.out.println(obj.name);
				return obj;
			}
		}
		
		return null;
	}
	
	private void clearSelectedObj() {
		if(selectedGameObject != null) 	selectedGameObject.released();
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
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				getPlayer().setDx(1);
			}else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				getPlayer().setDy(-1);
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				getPlayer().setDy(1);
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
