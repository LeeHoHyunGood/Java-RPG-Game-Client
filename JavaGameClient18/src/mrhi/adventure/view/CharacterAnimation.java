package mrhi.adventure.view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class CharacterAnimation {
	
	private Image stopUp;
	private Image stopLeft;
	private Image stopDown;
	private Image stopRight;
	
	private Animation spellUp;
	private Animation spellLeft;
	private Animation spellDown;
	private Animation spellRight;

	private Animation thrustUp;
	private Animation thrustLeft;
	private Animation thrustDown;
	private Animation thrustRight;
	
	private Animation walkUp;
	private Animation walkLeft;
	private Animation walkDown;
	private Animation walkRight;
	
	private Animation slashUp;
	private Animation slashLeft;
	private Animation slashDown;
	private Animation slashRight;
	
	private Animation shootUp;
	private Animation shootLeft;
	private Animation shootDown;
	private Animation shootRight;
	
	private Animation hurt;
	
	
	public CharacterAnimation(SpriteSheet sheet) {
		super();
		
		
		spellUp = new Animation();
		spellLeft = new Animation();
		spellDown = new Animation();
		spellRight = new Animation();

		thrustUp = new Animation();
		thrustLeft = new Animation();
		thrustDown = new Animation();
		thrustRight = new Animation();
		
		slashUp = new Animation();
		slashLeft = new Animation();
		slashDown = new Animation();
		slashRight = new Animation();
		
		shootUp = new Animation();
		shootLeft = new Animation();
		shootDown = new Animation();
		shootRight = new Animation();
		
		hurt = new Animation();
		
		walkUp = new Animation();
		walkLeft = new Animation();
		walkDown = new Animation();
		walkRight = new Animation();
		
		spellUp = new Animation();
		spellLeft = new Animation();
		spellDown = new Animation();
		spellRight = new Animation();
		
		stopUp = sheet.getSubImage(0, 8);
		stopLeft = sheet.getSubImage(0, 9);
		stopDown = sheet.getSubImage(0, 10);
		stopRight = sheet.getSubImage(0, 11);
		
		for(int i=0; i<7; i++) 
			spellUp.addFrame(sheet.getSprite(i, 0), 100);
		for(int i=0; i<7; i++) 
			spellLeft.addFrame(sheet.getSprite(i, 1), 100);
		for(int i=0; i<7; i++) 
			spellDown.addFrame(sheet.getSprite(i, 2), 100);
		for(int i=0; i<7; i++) 
			spellRight.addFrame(sheet.getSprite(i, 3), 100);
		
		for(int i=0; i<8; i++) 
			thrustUp.addFrame(sheet.getSprite(i, 4), 100);
		for(int i=0; i<8; i++) 
			thrustLeft.addFrame(sheet.getSprite(i, 5), 100);
		for(int i=0; i<8; i++) 
			thrustDown.addFrame(sheet.getSprite(i, 6), 100);
		for(int i=0; i<8; i++) 
			thrustRight.addFrame(sheet.getSprite(i, 7), 100);
		
		for(int i=0; i<9; i++) 
			walkUp.addFrame(sheet.getSprite(i, 8), 100);
		for(int i=0; i<9; i++) 
			walkLeft.addFrame(sheet.getSprite(i, 9), 100);
		for(int i=0; i<9; i++) 
			walkDown.addFrame(sheet.getSprite(i, 10), 100);
		for(int i=0; i<9; i++) 
			walkRight.addFrame(sheet.getSprite(i, 11), 100);
		
		for(int i=0; i<6; i++) 
			slashUp.addFrame(sheet.getSprite(i, 12), 100);
		for(int i=0; i<6; i++) 
			slashLeft.addFrame(sheet.getSprite(i, 13), 100);
		for(int i=0; i<6; i++) 
			slashDown.addFrame(sheet.getSprite(i, 14), 100);
		for(int i=0; i<6; i++) 
			slashRight.addFrame(sheet.getSprite(i, 15), 100);
		
		for(int i=0; i<13; i++) 
			shootUp.addFrame(sheet.getSprite(i, 16), 100);
		for(int i=0; i<13; i++) 
			shootLeft.addFrame(sheet.getSprite(i, 17), 100);
		for(int i=0; i<13; i++) 
			shootDown.addFrame(sheet.getSprite(i, 18), 100);
		for(int i=0; i<13; i++) 
			shootRight.addFrame(sheet.getSprite(i, 19), 100);
		
		for(int i=0; i<7; i++) 
			hurt.addFrame(sheet.getSprite(i, 20), 100);
	}

	public Image getStop(int direction) {
		switch(direction) {
		case 1:
			return stopUp;
		case 4:
			return stopLeft;
		case 3:
			return stopDown;
		case 2:
			return stopRight;
		}
		return null;
	}
	
	public Animation getWalk(int direction) {
		switch(direction) {
		case 1:
			return walkUp;
		case 4:
			return walkLeft;
		case 3:
			return walkDown;
		case 2:
			return walkRight;
		}
		return null;
	}
	
	public Animation getSpell(int direction) {
		switch(direction) {
		case 1:
			return spellUp;
		case 4:
			return spellLeft;
		case 3:
			return spellDown;
		case 2:
			return spellRight;
		}
		return null;
	}
	
	public Animation getThrust(int direction) {
		switch(direction) {
		case 1:
			return thrustUp;
		case 4:
			return thrustLeft;
		case 3:
			return thrustDown;
		case 2:
			return thrustRight;
		}
		return null;
	}

	public Animation getSlash(int direction) {
		switch(direction) {
		case 1:
			return slashUp;
		case 4:
			return slashLeft;
		case 3:
			return slashDown;
		case 2:
			return slashRight;
		}
		return null;
	}
	
	public Animation getShoot(int direction) {
		switch(direction) {
		case 1:
			return shootUp;
		case 4:
			return shootLeft;
		case 3:
			return shootDown;
		case 2:
			return shootRight;
		}
		return null;
	}
	
	public Image getStopUp() {
		return stopUp;
	}
	public void setStopUp(Image stopUp) {
		this.stopUp = stopUp;
	}
	public Image getStopLeft() {
		return stopLeft;
	}
	public void setStopLeft(Image stopLeft) {
		this.stopLeft = stopLeft;
	}


	public Image getStopDown() {
		return stopDown;
	}


	public void setStopDown(Image stopDown) {
		this.stopDown = stopDown;
	}


	public Image getStopRight() {
		return stopRight;
	}


	public void setStopRight(Image stopRight) {
		this.stopRight = stopRight;
	}


	public Animation getSpellUp() {
		return spellUp;
	}


	public void setSpellUp(Animation spellUp) {
		this.spellUp = spellUp;
	}


	public Animation getSpellLeft() {
		return spellLeft;
	}


	public void setSpellLeft(Animation spellLeft) {
		this.spellLeft = spellLeft;
	}


	public Animation getSpellDown() {
		return spellDown;
	}


	public void setSpellDown(Animation spellDown) {
		this.spellDown = spellDown;
	}


	public Animation getSpellRight() {
		return spellRight;
	}


	public void setSpellRight(Animation spellRight) {
		this.spellRight = spellRight;
	}


	public Animation getThrustUp() {
		return thrustUp;
	}


	public void setThrustUp(Animation thrustUp) {
		this.thrustUp = thrustUp;
	}


	public Animation getThrustLeft() {
		return thrustLeft;
	}


	public void setThrustLeft(Animation thrustLeft) {
		this.thrustLeft = thrustLeft;
	}


	public Animation getThrustDown() {
		return thrustDown;
	}


	public void setThrustDown(Animation thrustDown) {
		this.thrustDown = thrustDown;
	}


	public Animation getThrustRight() {
		return thrustRight;
	}


	public void setThrustRight(Animation thrustRight) {
		this.thrustRight = thrustRight;
	}


	public Animation getWalkUp() {
		return walkUp;
	}


	public void setWalkUp(Animation walkUp) {
		this.walkUp = walkUp;
	}


	public Animation getWalkLeft() {
		return walkLeft;
	}


	public void setWalkLeft(Animation walkLeft) {
		this.walkLeft = walkLeft;
	}


	public Animation getWalkDown() {
		return walkDown;
	}


	public void setWalkDown(Animation walkDown) {
		this.walkDown = walkDown;
	}


	public Animation getWalkRight() {
		return walkRight;
	}


	public void setWalkRight(Animation walkRight) {
		this.walkRight = walkRight;
	}


	public Animation getSlashUp() {
		return slashUp;
	}


	public void setSlashUp(Animation slashUp) {
		this.slashUp = slashUp;
	}
	public Animation getSlashLeft() {
		return slashLeft;
	}
	public void setSlashLeft(Animation slashLeft) {
		this.slashLeft = slashLeft;
	}
	public Animation getSlashDown() {
		return slashDown;
	}
	public void setSlashDown(Animation slashDown) {
		this.slashDown = slashDown;
	}
	public Animation getSlashRight() {
		return slashRight;
	}
	public void setSlashRight(Animation slashRight) {
		this.slashRight = slashRight;
	}
	public Animation getShootUp() {
		return shootUp;
	}
	public void setShootUp(Animation shootUp) {
		this.shootUp = shootUp;
	}
	public Animation getShootLeft() {
		return shootLeft;
	}
	public void setShootLeft(Animation shootLeft) {
		this.shootLeft = shootLeft;
	}
	public Animation getShootDown() {
		return shootDown;
	}
	public void setShootDown(Animation shootDown) {
		this.shootDown = shootDown;
	}
	public Animation getShootRight() {
		return shootRight;
	}
	public void setShootRight(Animation shootRight) {
		this.shootRight = shootRight;
	}
	public Animation getHurt() {
		return hurt;
	}
	public void setHurt(Animation hurt) {
		this.hurt = hurt;
	}
}
