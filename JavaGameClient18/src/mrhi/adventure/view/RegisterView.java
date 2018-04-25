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
import mrhi.adventure.model.vo.AuthenticationVO;

public class RegisterView extends BasicGameState {

	private Image img;
	private Font font;
	private TextField idField;
	private TextField nickField;
	private TextField pwField;
	private TextField pwcField;
	private TextField nameField;
	private TextField hpField;
	private TextField emailField;
	private TextField authField;

	private int focus;

	public RegisterView(int state) {}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
		img = new Image("image/register.png");
		font = new TrueTypeFont (new java.awt.Font ("Arial", java.awt.Font.BOLD, 26), false);
		idField = new TextField(gameContainer, font, 285, 120, 250, 40);
		nickField = new TextField(gameContainer, font, 285, 170, 250, 40);
		pwField = new TextField(gameContainer, font, 285, 220, 250, 40);
		pwcField = new TextField(gameContainer, font, 285, 270, 250, 40);
		nameField = new TextField(gameContainer, font, 285, 320, 250, 40);
		hpField = new TextField(gameContainer, font, 285, 370, 250, 40);
		emailField = new TextField(gameContainer, font, 285, 420, 250, 40);
		authField = new TextField(gameContainer, font, 285, 470, 250, 40);

		idField.setBackgroundColor(Color.white);
		idField.setBorderColor(Color.black);
		idField.setTextColor(Color.black);
		idField.setCursorVisible(true);

		nickField.setBackgroundColor(Color.white);
		nickField.setBorderColor(Color.black);
		nickField.setTextColor(Color.black);
		nickField.setCursorVisible(true);		

		pwField.setBackgroundColor(Color.white);
		pwField.setBorderColor(Color.black);
		pwField.setTextColor(Color.black);
		pwField.setCursorVisible(true);

		pwcField.setBackgroundColor(Color.white);
		pwcField.setBorderColor(Color.black);
		pwcField.setTextColor(Color.black);
		pwcField.setCursorVisible(true);

		nameField.setBackgroundColor(Color.white);
		nameField.setBorderColor(Color.black);
		nameField.setTextColor(Color.black);
		nameField.setCursorVisible(true);

		hpField.setBackgroundColor(Color.white);
		hpField.setBorderColor(Color.black);
		hpField.setTextColor(Color.black);
		hpField.setCursorVisible(true);

		emailField.setBackgroundColor(Color.white);
		emailField.setBorderColor(Color.black);
		emailField.setTextColor(Color.black);
		emailField.setCursorVisible(true);

		authField.setBackgroundColor(Color.white);
		authField.setBorderColor(Color.black);
		authField.setTextColor(Color.black);
		authField.setCursorVisible(true);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		img.draw();
		idField.render(gameContainer, g);
		nickField.render(gameContainer, g);
		pwField.render(gameContainer, g);
		pwcField.render(gameContainer, g);
		nameField.render(gameContainer, g);
		hpField.render(gameContainer, g);
		emailField.render(gameContainer, g);
		authField.render(gameContainer, g);

		//		g.drawRect(544, 432, 133, 32);
		//		g.drawRect(460, 536, 158, 31);
		//		g.drawRect(633, 537, 72, 31);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gameContainer.getInput();
		if(input.isKeyPressed(Input.KEY_TAB)) {
			focus++;
			if (focus == 9)
				focus = 1;
		}

		if(focus==1) {
			idField.setFocus(true);
		} else if(focus==2) {
			nickField.setFocus(true);
		} else if(focus==3) {
			pwField.setFocus(true);
		} else if(focus==4) {
			pwcField.setFocus(true);
		} else if(focus==5) {
			nameField.setFocus(true);
		} else if(focus==6) {
			hpField.setFocus(true);
		} else if(focus==7) {
			emailField.setFocus(true);
		} else if(focus==8) {
			authField.setFocus(true);
		}

		if(input.isMousePressed(0)) {
			if(idField.getX() < input.getMouseX() && idField.getX()+idField.getWidth() > input.getMouseX()
					&& idField.getY() < input.getMouseY() && idField.getY()+idField.getHeight() > input.getMouseY())
				focus = 1;
			else if(nickField.getX() < input.getMouseX() && nickField.getX()+nickField.getWidth() > input.getMouseX()
					&& nickField.getY() < input.getMouseY() && nickField.getY()+nickField.getHeight() > input.getMouseY())
				focus = 2;
			else if(pwField.getX() < input.getMouseX() && pwField.getX()+pwField.getWidth() > input.getMouseX()
					&& pwField.getY() < input.getMouseY() && pwField.getY()+pwField.getHeight() > input.getMouseY())
				focus = 3;
			else if(pwcField.getX() < input.getMouseX() && pwcField.getX()+pwcField.getWidth() > input.getMouseX()
					&& pwcField.getY() < input.getMouseY() && pwcField.getY()+pwcField.getHeight() > input.getMouseY())
				focus = 4;
			else if(nameField.getX() < input.getMouseX() && nameField.getX()+nameField.getWidth() > input.getMouseX()
					&& nameField.getY() < input.getMouseY() && nameField.getY()+nameField.getHeight() > input.getMouseY())
				focus = 5;
			else if(hpField.getX() < input.getMouseX() && hpField.getX()+hpField.getWidth() > input.getMouseX()
					&& hpField.getY() < input.getMouseY() && hpField.getY()+hpField.getHeight() > input.getMouseY())
				focus = 6;
			else if(emailField.getX() < input.getMouseX() && emailField.getX()+emailField.getWidth() > input.getMouseX()
					&& emailField.getY() < input.getMouseY() && emailField.getY()+emailField.getHeight() > input.getMouseY())
				focus = 7;
			else if(authField.getX() < input.getMouseX() && authField.getX()+authField.getWidth() > input.getMouseX()
					&& authField.getY() < input.getMouseY() && authField.getY()+authField.getHeight() > input.getMouseY())
				focus = 8;
			else if(544 < input.getMouseX() && 544+133 > input.getMouseX()
					&& 432 < input.getMouseY() && 432+32 > input.getMouseY()) {
				System.out.println("메일보내기~!");
				AccountVO accountVO = new AccountVO();
				accountVO.setAcc_email(emailField.getText());
				Client.getInstance().getAccountManagement().requestRegisterAuthEmail(accountVO);
			} else if(544 < input.getMouseX() && 544+133 > input.getMouseX()
					&& 480 < input.getMouseY() && 480+32 > input.getMouseY()) {
				if (authField.getText() != "") {
					System.out.println("인증번호확인요청");
					AuthenticationVO authVO = new AuthenticationVO();
					authVO.setAuthNumber(Integer.parseInt(authField.getText()));
					authVO.setEmail(emailField.getText());
					Client.getInstance().getAccountManagement().requestRegisterAuth(authVO);
				}
			} else	if(460 < input.getMouseX() && 460+158 > input.getMouseX()
					&& 536 < input.getMouseY() && 536+31 > input.getMouseY()){
				if(GameView.certification)
				{
					if(pwField.getText().equals(pwcField.getText())) {
						AccountVO accountVO = new AccountVO();
						accountVO.setAcc_id(idField.getText());
						accountVO.setAcc_hp(hpField.getText());
						accountVO.setAcc_email(emailField.getText());
						accountVO.setAcc_name(nameField.getText());
						accountVO.setAcc_pw(pwField.getText());
						Client.getInstance().getAccountManagement().register(accountVO);
						GameView.certification = false;
						arg1.enterState(0, new FadeOutTransition(), new FadeInTransition());
						idField.setText(""); nickField.setText(""); pwField.setText("");
						pwcField.setText(""); nameField.setText(""); hpField.setText("");
						emailField.setText(""); authField.setText("");
					}
				}
			}
			else if(633 < input.getMouseX()  && 633+72 > input.getMouseX() && 537 < input.getMouseY() && 537+31 > input.getMouseY()) {
				arg1.enterState(0, new FadeOutTransition(), new FadeInTransition());
				idField.setText(""); nickField.setText(""); pwField.setText("");
				pwcField.setText(""); nameField.setText(""); hpField.setText("");
				emailField.setText(""); authField.setText("");
			}
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}

