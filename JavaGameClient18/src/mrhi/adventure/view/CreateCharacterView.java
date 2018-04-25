package mrhi.adventure.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.vo.CharacterVO;

public class CreateCharacterView extends BasicGameState{
	private Image img;
	private Image box;
	private Image sbox;
	private TextField nickField;
	private int select;
	private Font font;
	
	public CreateCharacterView(int state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
		img = new Image("image/selectView/createchar.png");
		box = new Image("image/selectView/box.png");
		sbox = new Image("image/selectView/sbox.png");
		font = new TrueTypeFont (new java.awt.Font ("Arial", java.awt.Font.BOLD, 26), false);
		nickField = new TextField(gameContainer, font, 300, 440, 205, 40);
		nickField.setBackgroundColor(Color.white);
		nickField.setBorderColor(Color.black);
		nickField.setTextColor(Color.black);
		nickField.setCursorVisible(true);
		nickField.setFocus(true);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		img.draw();
		
		for(int i=0; i<4; i++) {
			if(i==select-1)
				sbox.draw(70+i*170, 130);
			else
				box.draw(70+i*170, 130);
		}
		SelectCharacterView.warrior.draw(70+0*170, 130);
		SelectCharacterView.knight.draw(70+1*170, 130);
		SelectCharacterView.archer.draw(70+2*170, 130);
		SelectCharacterView.magician.draw(70+3*170, 130);
		
		nickField.render(gameContainer, g);
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int arg2) throws SlickException {
		nickField.setFocus(true);
		
		Input input = gameContainer.getInput();
		
		if(input.isMousePressed(0)) {
			if(70+0*170 < input.getMouseX() && 70+0*170+150 > input.getMouseX()&& 125 < input.getMouseY() && 406 > input.getMouseY()) {
				select = 1;
			} else if(70+1*170 < input.getMouseX() && 70+1*170+150 > input.getMouseX()&& 125 < input.getMouseY() && 420 > input.getMouseY()) {
				select = 2; 
			} else if(70+2*170 < input.getMouseX() && 70+2*170+150 > input.getMouseX()&& 125 < input.getMouseY() && 420 > input.getMouseY()) {
				select = 3;
			} else if(70+3*170 < input.getMouseX() && 70+3*170+150 > input.getMouseX()&& 125 < input.getMouseY() && 420 > input.getMouseY()) {
				select = 4;
			} else if(315 < input.getMouseX() && 505 > input.getMouseX()&& 500 < input.getMouseY() && 535 > input.getMouseY()) {
				CharacterVO charVO = new CharacterVO();
				charVO.setChr_name(nickField.getText());
				charVO.setChr_job(this.select);
				Client.getInstance().getGame().createCharacter(charVO);
				Client.getInstance().getGame().requestCharacterList();
				arg1.enterState(3, new FadeOutTransition(), new FadeInTransition());
			} else if(660 < input.getMouseX() && 710 > input.getMouseX()&& 10 < input.getMouseY() && 40 > input.getMouseY()) {
				nickField.setText("");
				arg1.enterState(3, new FadeOutTransition(), new FadeInTransition());
			}

		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}