package mrhi.adventure.model.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.vo.ItemVO;
import mrhi.adventure.model.vo.MapVO;
import mrhi.adventure.model.vo.MobVO;
import mrhi.adventure.model.vo.OtherCharacterVO;
import mrhi.adventure.model.vo.ShopVO;

public class GameMap {
	private int map_idx;
	private int map_width;
	private int map_height;
	private int[][] tile;
	private List<OtherCharacter> otherCharList;
	private List<Mob> mobList;
	private List<JItem> itemList;
	private List<Potal> potalList;
	private List<Chat> chatList;
	private ShopVO shopVO;
	
	public GameMap() {
		super();
	}
	
	public GameMap(MapVO mapVO){
		this.map_height = mapVO.getMap_height();
		this.map_width = mapVO.getMap_width();
		this.map_idx = mapVO.getMap_idx();
		this.otherCharList = new LinkedList<>();
		this.mobList = new LinkedList<>();
		this.itemList = new LinkedList<>();
		this.potalList = new ArrayList<>();
		this.chatList = new LinkedList<>();
		this.shopVO = new ShopVO();
		
		for(OtherCharacterVO infoChar : mapVO.getOtherCharList())
			if(infoChar.getChr_idx()!=Client.getInstance().getGame().getMainCharacter().getChrIdx())
				this.otherCharList.add(new OtherCharacter(infoChar));
		
		for(MobVO mobVO : mapVO.getMobList())
			this.mobList.add(new Mob(mobVO));
		
		for(ItemVO item : mapVO.getItemList()) {
			this.itemList.add(new JItem(item));
		}
	}

	public GameMap(int map_idx, int map_width, int map_height) {
		super();
		this.map_idx = map_idx;
		this.map_width = map_width;
		this.map_height = map_height;
		this.otherCharList = new LinkedList<>();
		this.mobList = new LinkedList<>();
		this.potalList = new ArrayList<>();
		this.chatList = new LinkedList<>();
		this.shopVO = new ShopVO();
	}
	
//	public MapVO getMapVO() {
//		MapVO mapVO = new MapVO();
//		
//		mapVO.setInfoCharList(otherCharList);
//		mapVO.setMap_height(map_height);
//		mapVO.setMap_idx(map_idx);
//		mapVO.setMap_width(map_width);
////		mapVO.setMobList(mobList);
//		mapVO.setPotalList(potalList);
//		
//		return mapVO;
//		
//	}

	public int getMap_idx() {
		return map_idx;
	}
	public ShopVO getShopVO() {
		return shopVO;
	}

	public void setShopVO(ShopVO shopVO) {
		this.shopVO = shopVO;
	}

	public void setMap_idx(int map_idx) {
		this.map_idx = map_idx;
	}
	public int getMap_width() {
		return map_width;
	}
	public void setMap_width(int map_width) {
		this.map_width = map_width;
	}
	public int getMap_height() {
		return map_height;
	}
	public void setMap_height(int map_height) {
		this.map_height = map_height;
	}
	public int[][] getTile() {
		return tile;
	}
	public void setTile(int[][] tile) {
		this.tile = tile;
	}
	public List<OtherCharacter> getOtherCharList() {
		return otherCharList;
	}
	public void setInfoCharList(List<OtherCharacter> otherCharList) {
		this.otherCharList = otherCharList;
	}
	public List<Mob> getMobList() {
		return mobList;
	}
	public void setMobList(List<Mob> mobList) {
		this.mobList = mobList;
	}
	public List<Potal> getPotalList() {
		return potalList;
	}
	public void setPotalList(List<Potal> potalList) {
		this.potalList = potalList;
	}
	public List<JItem> getItemList() {
		return itemList;
	}
	public List<Chat> getChatList() {
		return chatList;
	}
}
