package mrhi.adventure.model.game;

public class IEquip extends IItem{
	private int item_level;
	private int item_job;
	private int item_def;
	private int item_mdef;
	private int item_power;
	private int item_mpower;
	private int item_str;
	private int item_dex;
	private int item_int;
	private int item_pro;
	private int item_hp;
	private int item_mp;
	
	public IEquip() {
		super();
	}

	public IEquip(int item_level, int item_job, int item_def, int item_mdef, int item_power, int item_mpower,
			int item_str, int item_dex, int item_int, int item_pro, int item_hp, int item_mp) {
		super();
		this.item_level = item_level;
		this.item_job = item_job;
		this.item_def = item_def;
		this.item_mdef = item_mdef;
		this.item_power = item_power;
		this.item_mpower = item_mpower;
		this.item_str = item_str;
		this.item_dex = item_dex;
		this.item_int = item_int;
		this.item_pro = item_pro;
		this.item_hp = item_hp;
		this.item_mp = item_mp;
	}

	public int getItem_level() {
		return item_level;
	}

	public void setItem_level(int item_level) {
		this.item_level = item_level;
	}

	public int getItem_job() {
		return item_job;
	}

	public void setItem_job(int item_job) {
		this.item_job = item_job;
	}

	public int getItem_def() {
		return item_def;
	}

	public void setItem_def(int item_def) {
		this.item_def = item_def;
	}

	public int getItem_mdef() {
		return item_mdef;
	}

	public void setItem_mdef(int item_mdef) {
		this.item_mdef = item_mdef;
	}

	public int getItem_power() {
		return item_power;
	}

	public void setItem_power(int item_power) {
		this.item_power = item_power;
	}

	public int getItem_mpower() {
		return item_mpower;
	}

	public void setItem_mpower(int item_mpower) {
		this.item_mpower = item_mpower;
	}

	public int getItem_str() {
		return item_str;
	}

	public void setItem_str(int item_str) {
		this.item_str = item_str;
	}

	public int getItem_dex() {
		return item_dex;
	}

	public void setItem_dex(int item_dex) {
		this.item_dex = item_dex;
	}

	public int getItem_int() {
		return item_int;
	}

	public void setItem_int(int item_int) {
		this.item_int = item_int;
	}

	public int getItem_pro() {
		return item_pro;
	}

	public void setItem_pro(int item_pro) {
		this.item_pro = item_pro;
	}

	public int getItem_hp() {
		return item_hp;
	}

	public void setItem_hp(int item_hp) {
		this.item_hp = item_hp;
	}

	public int getItem_mp() {
		return item_mp;
	}

	public void setItem_mp(int item_mp) {
		this.item_mp = item_mp;
	}
}
