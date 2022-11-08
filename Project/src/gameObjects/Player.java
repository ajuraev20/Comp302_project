package gameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import gameLogic.Game;
import gameLogic.LevelScene;
import gui.MainGamePanel;



public class Player extends GameObject {
	
	private int ms = 3;
	private int dx;
	private int dy;

	private static int WIDTH = 32, HEIGHT = 32;
	
	private long startTime;
	private long interval = 100;
	
	private BufferedImage[] currDirSprites;
	private int currDirSpriteIndex;
	private int lastDir;


	
	public Player(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT);
		this.dx = 0;
		this.dy = 0;

		this.color = Color.RED;
		this.loadImgs("/images/player",16);
		this.currSprite = sprites[0];
		currDirSprites = new BufferedImage[4];
		lastDir = 1;
		
		
		currDirSpriteIndex = 0;
		
		this.setBoundsToSprite(x, y);
		
		startTime = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
		
	}
	
	public void move(Game game) {
		rect.x += ms*dx;
		rect.y += ms*dy;

		changeDirSprites();
		
		if(System.currentTimeMillis() - startTime >= interval) {
			startTime = System.currentTimeMillis();			
			changeImg();
		}
		
		
		
		stayInFrame();
		checkDoorCollision(game);
		checkCollision(game.getGameObjects());
		
		
				
	}
	
	private void checkDoorCollision(Game game) {
		if(game.getCurrScene() instanceof LevelScene) {
			Door door = ((LevelScene)game.getCurrScene()).getDoor();
			if(door.isOpen() && game.playerIsClose(door)) {
				game.getSceneManager().moveToNextLevel();
			}

		}
	}
	
	private void changeDirSprites() {
		// TODO Auto-generated method stub
		if(dy > 0) {
			fillDirSprites(0);
			lastDir = 1;
		}else if(dy < 0) {
			fillDirSprites(4);
			lastDir = 5;
		}else if(dx < 0) {
			fillDirSprites(8);
			lastDir = 9;
		}else if(dx > 0) {
			fillDirSprites(12);
			lastDir = 13;
		}else if(dy == 0 && dx == 0) {
			for(int i = 0;i <= 3; i++) {
				currDirSprites[i] = sprites[lastDir];
			}
		}
	}
	
	private void fillDirSprites(int start) {
		for(int i = 0;i <= 3; i++) {
			currDirSprites[i] = sprites[start + i];
		}
		
	}

	protected void changeImg() {
		
		if(currDirSpriteIndex == 3) {
			currDirSpriteIndex = 0;
		}else {
			currDirSpriteIndex++;
		}
		
		currSprite = currDirSprites[currDirSpriteIndex];
		rect.width = currSprite.getWidth();
		rect.height = currSprite.getHeight();
	}
	
	private void checkCollision(List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		for(GameObject obj : gameObjects) {
			int rayWidth = 1;
			
			Rectangle botRay1 = new Rectangle(rect.x + rect.width/2,rect.y + rect.height,1,rayWidth);
			Rectangle topRay = new Rectangle(rect.x + rect.width/2,rect.y - rayWidth,1,rayWidth);
			
			Rectangle leftRay1 = new Rectangle(rect.x - rayWidth,rect.y + rect.height/2,rayWidth,1);
			Rectangle leftRay2 = new Rectangle(rect.x - rayWidth,rect.y,rayWidth,1);
			Rectangle leftRay3 = new Rectangle(rect.x - rayWidth,rect.y + rect.height,rayWidth,1);

			Rectangle rightRay1 = new Rectangle(rect.x + rect.width,rect.y + rect.height/2,rayWidth,1);
			Rectangle rightRay2= new Rectangle(rect.x + rect.width,rect.y,rayWidth,1);
			Rectangle rightRay3 = new Rectangle(rect.x + rect.width,rect.y + rect.height,rayWidth,1);


			//System.out.println(topRay);
			if(obj != this) {
				if(rightRay1.intersects(obj.getRect())) {
					rect.x = obj.getRect().x-rect.width;
					//System.out.println("1");
				}else if(leftRay1.intersects(obj.getRect())) {
					rect.x = obj.getRect().x + obj.getRect().width;
					//System.out.println("2");
				}else if(botRay1.intersects(obj.getRect())) {
					rect.y = obj.getRect().y - rect.height;
					//System.out.println("down");
				}else if(topRay.intersects(obj.getRect())) {
					rect.y = obj.getRect().y + obj.getRect().height;
					//System.out.println("4");
				}
			}
		}
		
	}

	private void stayInFrame() {
		// TODO Auto-generated method stub
		if(rect.x+rect.width > MainGamePanel.WIDTH) {
			rect.x = MainGamePanel.WIDTH-rect.width;
		}else if(rect.x < 0) {
			rect.x = 0;
		}
		
		if(rect.y+rect.height > MainGamePanel.HEIGHT) {
			rect.y = MainGamePanel.HEIGHT-rect.height;
		}else if(rect.y < 0) {
			rect.y = 0;
		}
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}

	@Override
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(color);
		int dst = 20;
		
		//g2.fillRect(getRect().x-dst/2, getRect().y-dst/2, getRect().width+dst, getRect().height+dst);
		
		g2.drawImage(currSprite, getRect().x, getRect().y,null);
	}
	

}
