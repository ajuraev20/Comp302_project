package GUI;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import GameLogic.Game;
import GameObjects.GameObject;

public class MainGamePanel extends JPanel{
	
	public static final int WIDTH = 512, HEIGHT = 512;
	public Game game;
	
	public MainGamePanel(Game game) {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.game = game;

	}
	
	public void paintComponent( Graphics g) {
		super.paintComponent( g );
        Graphics2D g2 = (Graphics2D) g;
		
		for (GameObject obj : game.getGameObjects()) {
			g2.setColor(obj.color);
			g2.fillRect(obj.getRect().x, obj.getRect().y, obj.getRect().width, obj.getRect().height);
		}
		
	}

}
