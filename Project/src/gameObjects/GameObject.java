package gameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import animations.Animation;
import gameLogic.Game;

public abstract class GameObject {
	
	public String name;
	protected Rectangle rect;
	protected boolean pressed;
	protected boolean buildable;
	protected boolean moveable;
	protected boolean clickable;
	
	protected BufferedImage currSprite;
	protected BufferedImage[] sprites;

	protected List<Animation> animations;



	public Color color;

	
	public GameObject(String name, int x, int y, int width, int height) {
		this.name = name;
		this.rect = new Rectangle(x,y, width, height);
		
		this.animations = new ArrayList<Animation>();
	}
	
	public GameObject(String name) {
		this.name = name;
		this.animations = new ArrayList<Animation>();
	}
	
	protected void loadImg(String url) {
		try {
				currSprite = ImageIO.read(getClass().getResource(url+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void loadImgs(String url, int imgCnt) {
		sprites = new BufferedImage[imgCnt];
		try {
			for(int i=0;i<imgCnt;i++) {
				sprites[i] = ImageIO.read(getClass().getResource(url+i+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void setBoundsToSprite(int x, int y) {
		this.rect = new Rectangle(x, y, currSprite.getWidth(), currSprite.getHeight());
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	protected void renderAnims(Graphics2D g2) {
		animations.stream().forEach(anim -> anim.render(g2));
	}
	
	protected void removeEndedAnims() {
		animations = animations.stream().filter(anim -> !anim.hasEnded()).collect(Collectors.toList());
	}
	public boolean isMoveable() {
		return moveable;
	}
	
	public boolean isClickable() {
		return clickable;
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}
	
	public boolean isBuildable() {
		return buildable;
	}
	
	public void setPosition(Rectangle p) {
		rect = p;
	}
	
	public void release() {
		pressed = false;
	}
	
	public void setBuildable(boolean buildingMode) {
		this.buildable = buildingMode;
	}
	
	public abstract void onClick(Point p, Game game);
	
	public abstract void render(Graphics2D g2);

}
