package mrhi.adventure.view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;


public class DirectionAnimation {
	private Animation up;
	private Animation down;
	private Animation left;
	private Animation right;
	
	public DirectionAnimation(SpriteSheet sheet) {
		super();
		
		up = new Animation();
		left = new Animation();
		down = new Animation();
		right = new Animation();
		
		for(int i=0; i<sheet.getHorizontalCount(); i++) {
			up.addFrame(sheet.getSprite(i, 0), 100);
		}
		
		for(int i=0; i<sheet.getHorizontalCount(); i++) {
			left.addFrame(sheet.getSprite(i, 1), 100);
		}
		
		for(int i=0; i<sheet.getHorizontalCount(); i++) {
			down.addFrame(sheet.getSprite(i, 2), 100);
		}
		
		for(int i=0; i<sheet.getHorizontalCount(); i++) {
			right.addFrame(sheet.getSprite(i, 3), 100);
		}
	}
	
	public Animation getAnimation(int direction) {
		switch(direction) {
		case 1:
			return up;
		case 4:
			return left;
		case 3:
			return down;
		case 2:
			return right;
		}
		return null;
	}
	
	public Animation getUp() {
		return up;
	}
	public Animation getDown() {
		return down;
	}
	public Animation getLeft() {
		return left;
	}
	public Animation getRight() {
		return right;
	}
}
