package mrhi.adventure.model;

import java.io.IOException;
import java.net.Socket;

import mrhi.adventure.control.ConnectionHandler;
import mrhi.adventure.control.ConnectionManager;
import mrhi.adventure.model.vo.AccountVO;
import mrhi.adventure.model.vo.CharacterVO;

public class Client {
	
	private static Client theInstance = new Client();
	private Game game;//게임클래스
	private ConnectionManager connectionManager; //연결하는 소켓클래스
	private AccountManagement accountManagement = new AccountManagement();//로그인 클래스
	private AccountVO myAccount;//회원가입 클래스
	
	private Client() {
		this.game = new Game();
	}
	
	public void login(AccountVO avo)
	{
		this.accountManagement.login(avo);
	}
	
	public void register(AccountVO avo)
	{
		this.accountManagement.register(avo);
	}
	
	public void createCharacter(CharacterVO charVO)
	{
		this.game.createCharacter(charVO);
	}
	
	public void requestCharacter(CharacterVO charVO)
	{
		this.game.requestCharacter(charVO);
	}
	
	public void connect()
	{
		try {
			this.connectionManager = new ConnectionManager(new Socket("192.168.0.79", 21212));
			System.out.println("연결성공!");
			
			ConnectionHandler ch = new ConnectionHandler(connectionManager);
			Thread cThread = new Thread(ch);
			cThread.start();
			
		} catch (IOException e) {
			System.out.println("서버가 열려있지 않습니다.");
			e.printStackTrace();
		}
	}
	
	public static Client getInstance() {
		return theInstance;
	}
	
	public AccountManagement getAccountManagement() {
		return accountManagement;
	}

	public void setAccountManagement(AccountManagement accountManagement) {
		this.accountManagement = accountManagement;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public AccountVO getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(AccountVO myAccount) {
		this.myAccount = myAccount;
	}
	
}
