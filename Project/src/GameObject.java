import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject {
	
	public String name;
	protected Rectangle rect;
	protected boolean pressed;

	public Color color;

	
	public GameObject(String name, int x, int y, int width, int height) {
		this.name = name;
		this.rect = new Rectangle(x,y, width, height);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setPosition(Rectangle p) {
		rect = p;
	}
	
	public void released() {
		pressed = false;
	}
	
	public abstract void onClick(Point p);

}
