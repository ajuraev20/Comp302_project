package animations;

import java.awt.Graphics2D;

public abstract class Animation {
	
	protected boolean hasEnded;
	protected int x, y;
	
	public Animation(int x, int y) {
		this.x = x;
		this.y = y;
		hasEnded = false;
	}
	
	public abstract void render(Graphics2D g2);
	
	public boolean hasEnded() {
		return hasEnded;
	}
	
	protected abstract void animate();
	

}
