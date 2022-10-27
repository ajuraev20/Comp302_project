package GUI;
import javax.swing.JFrame;

import GameLogic.Game;

public class GameFrame extends JFrame{

		private MainGamePanel mainGamePanel;
		private Game game;
	
	
	public GameFrame(Game game) {
		super("Escape from Koc");
		
		this.game = game;
		
		mainGamePanel = new MainGamePanel(game);
		this.add(mainGamePanel);
		
		this.setVisible(true);
		this.setResizable(false);
		pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void update() {
		
		mainGamePanel.repaint();
	}
}
	