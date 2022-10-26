import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;




public class Game {
	
	private static Timer timer;
	private List<GameObject> gameObjects;
	private GameFrame mainFrame;

	public Game() {
		buildGame();

		
		mainFrame = new GameFrame(this);
		GameKeyListener listener = new GameKeyListener();
		mainFrame.addKeyListener(listener);
		
		timer = new Timer(60, new ActionListener() {
		       public void actionPerformed(ActionEvent e) // will run when the timer fires
		 	   {
		    	   tick();
		 	   }
		    	
		    });
		
		timer.start();
	}
	
	private void buildGame() {
		Player player = new Player("player", 400, 400);
		
		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(player);
	}
	
	private void tick() {
		mainFrame.update();
		getPlayer().move();
		
		
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
				System.out.println("up");
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

}
