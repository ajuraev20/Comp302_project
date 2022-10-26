import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject {
	
	public String name;
	protected Rectangle rect;
	
	public GameObject(String name, int x, int y) {
		this.name = name;
		this.rect = new Rectangle(x,y);
	}
	
	public Rectangle getPosition() {
		return rect;
	}
	
	public void setPosition(Rectangle p) {
		rect = p;
	}

}
