package mrhi.adventure.model;

import java.util.ArrayList;
import java.util.List;

import mrhi.adventure.model.game.GameMap;
import mrhi.adventure.model.game.JItem;
import mrhi.adventure.model.game.MainCharacter;
import mrhi.adventure.model.game.OtherCharacter;
import mrhi.adventure.model.packet.MyPacket;
import mrhi.adventure.model.vo.CharacterVO;
import mrhi.adventure.model.vo.ChatVO;
import mrhi.adventure.model.vo.IntegerVO;
import mrhi.adventure.model.vo.ItemVO;
import mrhi.adventure.model.vo.MapVO;
import mrhi.adventure.model.vo.MobVO;

public class Game {
	
	private GameMap currentMap; //게임맵 그리기
	private MainCharacter mainCharacter; //캐릭터 
//	private GameHandler gameHandler;
	private List<CharacterVO> CharacterList; 
	
	public Game() {	
		CharacterList = new ArrayList<CharacterVO>();
	}
	
	public void startTimeElapse() {
		TimeElapseRun te = new TimeElapseRun();
		Thread thread = new Thread(te);
		thread.start();
	}
	
	public void upStat(IntegerVO integerVO) {
		System.out.println("스탯올리기 요청 패킷 전송!");
		Client.getInstance().getConnectionManager().send(new MyPacket(80, integerVO));
	}
	
	public void requestCharacterList() {
		System.out.println("캐릭터리스트 요청 패킷 전송!");
		Client.getInstance().getConnectionManager().send(new MyPacket(28, null));
	}
	
	public void createCharacter(CharacterVO charVO) {
		System.out.println("케릭터 생성요청 패킷 전송!");
		Client.getInstance().getConnectionManager().send(new MyPacket(20, charVO));
	}
	
	public void requestCharacter(CharacterVO charVO) {
		System.out.println("케릭터 요청 패킷 전송!");
		Client.getInstance().getConnectionManager().send(new MyPacket(22, charVO));
	}
	
	public void deleteCharacter(CharacterVO charVO) {
		System.out.println("케릭터 삭제요청 패킷 전송!");
		Client.getInstance().getConnectionManager().send(new MyPacket(21, charVO));
	}
	
	public void requestMap(MapVO map) {
		System.out.println("맵 요청 패킷 전송!");
		Client.getInstance().getConnectionManager().send(new MyPacket(30, map));
	}
	
	public void notifyPosition() {
		Client.getInstance().getConnectionManager().send(new MyPacket(24, mainCharacter.makeInfoCharacter()));
	}
	
	public void killMob(MobVO mobVO) {
		Client.getInstance().getConnectionManager().send(new MyPacket(40, mobVO));
	}
	
	public void gatherItem(ItemVO itemVO) {
		Client.getInstance().getConnectionManager().send(new MyPacket(44, itemVO));
	}
	
	public void sendChat(ChatVO chatVO) {
		Client.getInstance().getConnectionManager().send(new MyPacket(50, chatVO));
	}
	
	public void requestItemList() {
		Client.getInstance().getConnectionManager().send(new MyPacket(60, null));
	}
	
	public void saleItem(ItemVO itemVO) {
		Client.getInstance().getConnectionManager().send(new MyPacket(49, itemVO));
	}
	
	public void buyItems(ItemVO itemVO) {
		Client.getInstance().getConnectionManager().send(new MyPacket(47, itemVO));
	}
	
	public void getItem() {
		Client.getInstance().getConnectionManager().send(new MyPacket(48, null));
	}
	
	public GameMap getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(GameMap currentMap) {
		this.currentMap = currentMap;
	}
	
	public MainCharacter getMainCharacter() {
		return mainCharacter;
	}
	
	public void setMainCharacter(MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public List<CharacterVO> getCharacterList() {
		return CharacterList;
	}

	public void setCharacterList(List<CharacterVO> characterList) {
		CharacterList = characterList;
	}

	public class TimeElapseRun implements Runnable {

		@Override
		public void run() {
			while(true)
			{
				mainCharacter.autoMove();
				for(OtherCharacter oc :currentMap.getOtherCharList())
				{
					if(mainCharacter.getChrIdx()!= oc.getChr_idx())
						oc.autoMove();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		}
		
	}
}
