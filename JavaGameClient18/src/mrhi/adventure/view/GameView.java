package mrhi.adventure.view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import mrhi.adventure.model.Client;

public class GameView extends StateBasedGame {

	public static final String gamename = "Adventure";
	public static final int login = 0;
	public static final int register = 1;
	public static final int selectChar = 2;
	public static final int createChar = 3;
	public static final int play = 4;
	public static final int findRegister = 5;
	public static final int changePW = 6;
	
	public static boolean certification;
	
	public GameView(String str) {
		super(str);
		this.addState(new LoginView(login));
		this.addState(new RegisterView(register));
		this.addState(new SelectCharacterView(selectChar));
		this.addState(new CreateCharacterView(createChar));
		this.addState(new GamePlayView(play));
		this.addState(new FindAccountView(findRegister));
		this.addState(new ChangePWView(changePW));
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.enterState(login);
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			Client client = Client.getInstance();
			client.connect();
			appgc = new AppGameContainer(new GameView(gamename));
			appgc.setDisplayMode(800, 600, false);
//			appgc.setTargetFrameRate(60);
			appgc.start();
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
