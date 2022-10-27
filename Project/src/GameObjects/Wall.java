package GameObjects;
import java.awt.Color;
import java.awt.Point;

public class Wall extends GameObject{
	
	private static int WIDTH = 15, HEIGHT = 50;
	private int xDstFromMouse, yDstFromMouse;
	
	public Wall(String name, int x, int y) {
		super(name, x, y, WIDTH, HEIGHT);
		this.color = Color.RED;
		pressed = false;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p) {
		// TODO Auto-generated method stub
		if(!pressed) {
			xDstFromMouse = p.x - rect.x;
			yDstFromMouse = p.y - rect.y;
			pressed = true;
		}
		
		this.rect.x = p.x - xDstFromMouse;
		this.rect.y = p.y - yDstFromMouse;

		//this.rect.setLocation(p);
	}
	
	

}
