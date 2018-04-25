package mrhi.adventure.model.vo;

import java.util.ArrayList;
import java.util.List;

public class MapVO extends DataVO{
	private int map_idx;
	private int map_type;
	private String map_name;
	private int map_width;
	private int map_height;
	private List<OtherCharacterVO> otherCharList = new ArrayList<>(); //맵으로 들어오는 캐릭터 리스트
	private List<MobVO> mobList = new ArrayList<>();           //몬스터 리스트
	private List<ItemVO> itemList = new ArrayList<>();

	public MapVO() {
		super();
	}

	public int getMap_idx() {
		return map_idx;
	}

	public void setMap_idx(int map_idx) {
		this.map_idx = map_idx;
	}

	public int getMap_type() {
		return map_type;
	}

	public void setMap_type(int map_type) {
		this.map_type = map_type;
	}

	public String getMap_name() {
		return map_name;
	}

	public void setMap_name(String map_name) {
		this.map_name = map_name;
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

	public List<OtherCharacterVO> getOtherCharList() {
		return otherCharList;
	}

	public void setOtherCharList(List<OtherCharacterVO> otherCharList) {
		this.otherCharList = otherCharList;
	}

	public List<MobVO> getMobList() {
		return mobList;
	}

	public void setMobList(List<MobVO> mobList) {
		this.mobList = mobList;
	}

	public List<ItemVO> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemVO> itemList) {
		this.itemList = itemList;
	}
}
