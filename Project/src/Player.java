import java.util.List;



public class Player extends GameObject {
	
	private int ms = 15;
	private int dx;
	private int dy;


	
	public Player(String name, int x, int y) {
		super(name, x, y);
		dx = 0;
		dy = 0;
		// TODO Auto-generated constructor stub
		
	}
	
	public void move() {
		rect.x += ms*dx;
		rect.y += ms*dy;

		
//		if(rect.x+rect.width > GamePanel.WIDTH) {
//			rect.x = GamePanel.WIDTH-rect.width;
//		}else if(rect.x < 0) {
//			rect.x = 0;
//		}
		
		
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	

}
