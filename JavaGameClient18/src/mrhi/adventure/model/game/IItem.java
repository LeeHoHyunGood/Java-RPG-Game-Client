package mrhi.adventure.model.game;

public class IItem {
	protected int item_idx;
	protected int chr_idx;
	protected int item_code;
	protected int item_type;
	protected String name;

	public IItem() {
		super();
	}
	public IItem(int item_idx, int chr_idx, int item_code, int item_type, String name) {
		super();
		this.item_idx = item_idx;
		this.chr_idx = chr_idx;
		this.item_code = item_code;
		this.item_type = item_type;
		this.name = name;
	}
	public int getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(int item_idx) {
		this.item_idx = item_idx;
	}
	public int getChr_idx() {
		return chr_idx;
	}
	public void setChr_idx(int chr_idx) {
		this.chr_idx = chr_idx;
	}
	public int getItem_code() {
		return item_code;
	}
	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}
	public int getItem_type() {
		return item_type;
	}
	public void setItem_type(int item_type) {
		this.item_type = item_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
