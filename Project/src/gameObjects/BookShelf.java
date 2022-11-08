package gameObjects;

import java.awt.Graphics2D;
import java.awt.Point;

import animations.BuildableObjClickAnim;
import gameLogic.Game;

public class BookShelf extends BuildableGameObject{

	public BookShelf(int x, int y) {
		super("bookshelf");
		
		loadImg("/images/book-shelf");
		
		setBoundsToSprite(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Game game) {
		// TODO Auto-generated method stub
		if(clickable) {
			if(buildable) {
				BookShelf bookShelf= new BookShelf(p.x - (p.x - rect.x), p.y - (p.y - rect.y));
				bookShelf.setClickable(true);
				bookShelf.setMoveable(true);
				
				game.buildNewObject(bookShelf);
			}else {
				if(moveable) {
					if(!pressed) {
					xDstFromMouse = p.x - rect.x;
					yDstFromMouse = p.y - rect.y;
					pressed = true;
					}
					this.rect.x = p.x - xDstFromMouse;
					this.rect.y = p.y - yDstFromMouse;
				}else {
					this.animations.add(new BuildableObjClickAnim(this.rect.x,this.rect.y,this.rect));

					if(hasKey) {
						game.foundKey();
						
						hasKey = false;

					}
				}
				
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
		removeEndedAnims();
		renderAnims(g2);
		g2.drawImage(currSprite, rect.x, rect.y, null);
		
	}

}
