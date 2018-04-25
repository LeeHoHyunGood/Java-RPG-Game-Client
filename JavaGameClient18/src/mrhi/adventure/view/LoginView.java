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
import mrhi.adventure.model.vo.AccountVO;

public class LoginView extends BasicGameState{
	private Image img;
	private TextField idField;
	private TextField pwField;
	private Font font;
	private int focus = 1;
	public LoginView(int state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
//		UnicodeFont uni = new UnicodeFont(new java.awt.Font ("Arial", java.awt.Font.BOLD, 26));
//		uni.addGlyphs(1, 65536);
		img = new Image("image/login.png");
		font = new TrueTypeFont (new java.awt.Font ("Arial", java.awt.Font.BOLD, 26), false);
		idField = new TextField(gameContainer, font, 500, 420, 205, 40);
		pwField = new TextField(gameContainer, font, 500, 470, 205, 40);
		idField.setBackgroundColor(Color.white);
		idField.setBorderColor(Color.black);
		idField.setTextColor(Color.black);
		idField.setCursorVisible(true);

		pwField.setBackgroundColor(Color.white);
		pwField.setBorderColor(Color.black);
		pwField.setTextColor(Color.black);
		pwField.setCursorVisible(true);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		img.draw();
		idField.render(gameContainer, g);
		pwField.render(gameContainer, g);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gameContainer.getInput();
		//		idField.setFocus(true);
		//		pwField.setFocus(true);
		if(input.isKeyPressed(Input.KEY_TAB)) {
			focus++;
			if (focus == 3)
				focus = 1;
		}
		
		if(focus==1) {
			idField.setFocus(true);
		} else {
			pwField.setFocus(true);
		}
		
		if(Client.getInstance().getMyAccount()!=null) {
			Client.getInstance().getGame().requestCharacterList();
			arg1.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}

		if(input.isMousePressed(0)) {
			if(idField.getX() < input.getMouseX() && idField.getX()+idField.getWidth() > input.getMouseX()
					&& idField.getY() < input.getMouseY() && idField.getY()+idField.getHeight() > input.getMouseY())
				focus = 1;
			else if(pwField.getX() < input.getMouseX() && pwField.getX()+pwField.getWidth() > input.getMouseX()
					&& pwField.getY() < input.getMouseY() && pwField.getY()+pwField.getHeight() > input.getMouseY())
				focus = 2;
			else if(440 < input.getMouseX() && 520 < input.getMouseY() && 440+100 > input.getMouseX() && 520+40 > input.getMouseY()) {
				AccountVO accountVO = new AccountVO();
				accountVO.setAcc_id(idField.getText());
				accountVO.setAcc_pw(pwField.getText());
				System.out.println(accountVO);
				Client.getInstance().getAccountManagement().login(accountVO);
				idField.setText("");
				pwField.setText("");
			}
			else if(570 < input.getMouseX() && 520 < input.getMouseY() && 570+130 > input.getMouseX() && 520+40 > input.getMouseY()) {
				arg1.enterState(1, new FadeOutTransition(), new FadeInTransition());
			}
			else if(80 < input.getMouseX() && 100+80 > input.getMouseX() 
					&& 540 < input.getMouseY() && 540+25 > input.getMouseY()) {
				arg1.enterState(5, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
