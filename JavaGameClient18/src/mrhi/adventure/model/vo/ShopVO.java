package mrhi.adventure.model.vo;

public class ShopVO extends DataVO{
	private int map_idx;
	private int item_idx;
	private int shop_idx;
	private final int shop_x = 2300;
	private final int shop_y = 170;
	
	public int getMap_idx() {
		return map_idx;
	}
	public void setMap_idx(int map_idx) {
		this.map_idx = map_idx;
	}
	public int getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(int item_idx) {
		this.item_idx = item_idx;
	}
	public int getShop_idx() {
		return shop_idx;
	}
	public void setShop_idx(int shop_idx) {
		this.shop_idx = shop_idx;
	}
	public int getShop_x() {
		return shop_x;
	}
	public int getShop_y() {
		return shop_y;
	}
}
