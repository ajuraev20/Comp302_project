package animations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BuildableObjClickAnim extends Animation{
	
	private int maxWidth;
	private int currWidth;
	private Rectangle rect;
	
	public BuildableObjClickAnim(int x, int y, Rectangle targetRect) {
		super(x,y);
		rect = new Rectangle(x,y, targetRect.width, targetRect.height);
		
		maxWidth = 1;
		currWidth = 0;
	}

	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		animate();
		
		
		g2.setColor(Color.white);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		
	}


	@Override
	protected void animate() {
		// TODO Auto-generated method stub
		if(currWidth < maxWidth) {
			currWidth++;
		}else {
			hasEnded = true;
		}
		int dWidth = 1;
		
		rect.x -= dWidth;
		rect.y -= dWidth;
		rect.width += dWidth * 2;
		rect.height += dWidth * 2;
	}

}
