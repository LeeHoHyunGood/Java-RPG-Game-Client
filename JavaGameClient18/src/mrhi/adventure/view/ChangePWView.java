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

public class ChangePWView extends BasicGameState{
	private Image image;
	private TextField pwField;
	private TextField confirmPWField;
	private Font font;

	private int focus;
	
	public static AccountVO staticAVO;

	public ChangePWView(int state) {}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
		image = new Image("image/changePW.png");
		font = new TrueTypeFont (new java.awt.Font ("Arial", java.awt.Font.BOLD, 20), false);
		pwField = new TextField(gameContainer, font, 300, 203, 310, 40);
		confirmPWField = new TextField(gameContainer, font, 300, 306, 310, 40);

		pwField.setBackgroundColor(Color.white);
		pwField.setBorderColor(Color.black);
		pwField.setTextColor(Color.black);
		pwField.setCursorVisible(true);

		confirmPWField.setBackgroundColor(Color.white);
		confirmPWField.setBorderColor(Color.black);
		confirmPWField.setTextColor(Color.black);
		confirmPWField.setCursorVisible(true);

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		image.draw();
		pwField.render(gameContainer, g);
		confirmPWField.render(gameContainer, g);

	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int delta) throws SlickException {
		Input input = gameContainer.getInput();
		if (input.isKeyPressed(Input.KEY_TAB)) {
			focus++;
			if (focus == 3)
				focus = 1;
		}

		if (focus == 1) {
			pwField.setFocus(true);
		} else if (focus == 2) {
			confirmPWField.setFocus(true);
		}

		if (input.isMousePressed(0)) {
			if (pwField.getX() < input.getMouseX() && pwField.getX()+pwField.getWidth() > input.getMouseX()
					&& pwField.getY() < input.getMouseY() && pwField.getY()+pwField.getHeight() > input.getMouseY()) {
				focus = 1;
			} else if (confirmPWField.getX() < input.getMouseX() && confirmPWField.getX()+confirmPWField.getWidth() > input.getMouseX()
					&& confirmPWField.getY() < input.getMouseY() && confirmPWField.getY()+confirmPWField.getHeight() > input.getMouseY()) {
				focus = 2;
			} else if (265 < input.getMouseX() && 265+70 > input.getMouseX()
					&& 402 < input.getMouseY() && 402+40 > input.getMouseY()) {
				if (pwField.getText().equals(confirmPWField.getText())) {
					System.out.println("변경완료");
					AccountVO avo = new AccountVO();
					avo.setAcc_id(staticAVO.getAcc_id());
					avo.setAcc_pw(pwField.getText());
					Client.getInstance().getAccountManagement().changePassword(avo);
					arg1.enterState(5, new FadeOutTransition(), new FadeInTransition());
					pwField.setText(""); confirmPWField.setText("");
					staticAVO = null;
				}
			} else if (450 < input.getMouseX() && 450+70 > input.getMouseX()
					&& 402 < input.getMouseY() && 402+40 > input.getMouseY()) {
				arg1.enterState(5, new FadeOutTransition(), new FadeInTransition());
				pwField.setText(""); confirmPWField.setText("");
			}
		}

	}

	@Override
	public int getID() {
		return 6;
	}

}
