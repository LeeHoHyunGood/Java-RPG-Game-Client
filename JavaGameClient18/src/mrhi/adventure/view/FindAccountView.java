package mrhi.adventure.view;

import java.util.ArrayList;
import java.util.List;

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

public class FindAccountView extends BasicGameState{
	private Image image;
	private TextField idMailField;
	private TextField idNumberField;
	private TextField pwIdField;
	private TextField pwNumberField;
	private Font font;
	private int focus;

	public static List<AccountVO> idsList = new ArrayList<>();

	public FindAccountView(int state) {}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
		image = new Image("image/accountFind.png");
		font = new TrueTypeFont (new java.awt.Font ("Arial", java.awt.Font.BOLD, 20), false);
		idMailField = new TextField(gameContainer, font, 160, 170, 140, 35);
		idNumberField = new TextField(gameContainer, font, 160, 240, 140, 35);
		pwIdField = new TextField(gameContainer, font, 535, 170, 140, 35);
		pwNumberField = new TextField(gameContainer, font, 535, 240, 140, 35);

		idMailField.setBackgroundColor(Color.white);
		idMailField.setBorderColor(Color.black);
		idMailField.setTextColor(Color.black);
		idMailField.setCursorVisible(true);

		idNumberField.setBackgroundColor(Color.white);
		idNumberField.setBorderColor(Color.black);
		idNumberField.setTextColor(Color.black);
		idNumberField.setCursorVisible(true);

		pwIdField.setBackgroundColor(Color.white);
		pwIdField.setBorderColor(Color.black);
		pwIdField.setTextColor(Color.black);
		pwIdField.setCursorVisible(true);

		pwNumberField.setBackgroundColor(Color.white);
		pwNumberField.setBorderColor(Color.black);
		pwNumberField.setTextColor(Color.black);
		pwNumberField.setCursorVisible(true);

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		image.draw();
		idMailField.render(gameContainer, g);
		idNumberField.render(gameContainer, g);
		pwIdField.render(gameContainer, g);
		pwNumberField.render(gameContainer, g);
		
		for (int i = 0; i < idsList.size(); i++)
			if (idsList.size() <= 8) {
				g.drawString(idsList.get(i).getAcc_id(), 110, 330+i*30);
			} else {
				g.drawString(idsList.get(i).getAcc_id(), 150, 330+i*3*2);
			}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gameContainer.getInput();
		if (input.isKeyPressed(Input.KEY_TAB)) {
			focus++;
			if (focus == 5)
				focus = 1;
		}

		if (focus == 1) {
			idMailField.setFocus(true);
		} else if (focus == 2) {
			idNumberField.setFocus(true);
		} else if (focus == 3) {
			pwIdField.setFocus(true);
		} else if (focus == 4) {
			pwNumberField.setFocus(true);
		}

		if (input.isMousePressed(0)) {
			if (idMailField.getX() < input.getMouseX() && idMailField.getX()+idMailField.getWidth() > input.getMouseX()
					&& idMailField.getY() < input.getMouseY() && idMailField.getY()+idMailField.getHeight() > input.getMouseY()) {
				focus = 1;
			} else if (idNumberField.getX() < input.getMouseX() && idNumberField.getX()+idNumberField.getWidth() > input.getMouseX()
					&& idNumberField.getY() < input.getMouseY() && idNumberField.getY()+idNumberField.getHeight() > input.getMouseY()) {
				focus = 2;
			} else if (pwIdField.getX() < input.getMouseX() && pwIdField.getX()+pwIdField.getWidth() > input.getMouseX()
					&& pwIdField.getY() < input.getMouseY() && pwIdField.getY()+pwIdField.getHeight() > input.getMouseY()) {
				focus = 3;
			} else if (pwNumberField.getX() < input.getMouseX() && pwNumberField.getX()+pwNumberField.getWidth() > input.getMouseX()
					&& pwNumberField.getY() < input.getMouseY() && pwNumberField.getY()+pwNumberField.getHeight() > input.getMouseY()) {
				focus = 4;
			} else if (160 < input.getMouseX() && 160+100 > input.getMouseX()
					&& 210 < input.getMouseY() && 210+30 > input.getMouseY()) {
				System.out.println("id이메일보내기");
				AccountVO avo = new AccountVO();
				avo.setAcc_email(idMailField.getText());
				Client.getInstance().getAccountManagement().requestFindIDAuthEmail(avo);
			} else if (535 < input.getMouseX() && 535+100 > input.getMouseX()
					&& 210 < input.getMouseY() && 210+30 > input.getMouseY()) {
				System.out.println("pw이메일보내기");
				AccountVO avo = new AccountVO();
				avo.setAcc_id(pwIdField.getText());
				Client.getInstance().getAccountManagement().requestFindPWAuthEmail(avo);
			} else if (160 < input.getMouseX() && 160+110 > input.getMouseX()
					&& 280 < input.getMouseY() && 280+30 > input.getMouseY()) {
				if (idMailField.getText() != "" && idNumberField.getText() != "") {
					System.out.println("id인증번호 보내기");
					AuthenticationVO authVO = new AuthenticationVO();
					authVO.setAuthNumber(Integer.parseInt(idNumberField.getText()));
					authVO.setEmail(idMailField.getText());
					Client.getInstance().getAccountManagement().requestFindIDAuth(authVO);
					idsList.removeAll(idsList);
				}
			} else if (535 < input.getMouseX() && 535+100 > input.getMouseX()
					&& 280 < input.getMouseY() && 280+30 > input.getMouseY()) {
				if (pwNumberField.getText() != "") {
					System.out.println("pw인증번호 보내기");
					AuthenticationVO authVO = new AuthenticationVO();
					authVO.setId(pwIdField.getText());
					authVO.setAuthNumber(Integer.parseInt(pwNumberField.getText()));
					Client.getInstance().getAccountManagement().requestFindPWAuth(authVO);
				}
			} else if (555 < input.getMouseX() && 555+50 > input.getMouseX()
					&& 425 < input.getMouseY() && 425+35 > input.getMouseY() && ChangePWView.staticAVO!=null) {
				arg1.enterState(6, new FadeOutTransition(), new FadeInTransition());
				pwIdField.setText(""); pwNumberField.setText("");
			} else if (365 < input.getMouseX() && 365+70 > input.getMouseX()
					&& 515 < input.getMouseY() && 515+50 > input.getMouseY()) {
				arg1.enterState(0, new FadeOutTransition(), new FadeInTransition());
				idMailField.setText(""); idNumberField.setText("");
				pwIdField.setText(""); pwNumberField.setText("");
			}
		}

//		if (GameView.certification && idNumberField.getText() != "" && idMailField.getText() != "") {
//			AccountVO avo = new AccountVO();
//			avo.setAcc_email(idMailField.getText());
//			Client.getInstance().getAccountManagement().findID(avo);
//			GameView.certification = false;
//			idMailField.setText(""); idNumberField.setText("");
//		}

	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

}
