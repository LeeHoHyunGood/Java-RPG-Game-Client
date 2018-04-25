package mrhi.adventure.model.vo;


public class MobVO extends DataVO {
	private int mob_idx;
	private int gen_idx;
	private String mob_name;
	private int mob_level;
	private int mob_hp;
	private int mob_exp;
	private int mob_speed;
	private int x;
	private int y;
	
	public int getMob_idx() {
		return mob_idx;
	}
	public void setMob_idx(int mob_idx) {
		this.mob_idx = mob_idx;
	}
	public int getGen_idx() {
		return gen_idx;
	}
	public void setGen_idx(int gen_idx) {
		this.gen_idx = gen_idx;
	}
	public String getMob_name() {
		return mob_name;
	}
	public void setMob_name(String mob_name) {
		this.mob_name = mob_name;
	}
	public int getMob_level() {
		return mob_level;
	}
	public void setMob_level(int mob_level) {
		this.mob_level = mob_level;
	}
	public int getMob_hp() {
		return mob_hp;
	}
	public void setMob_hp(int mob_hp) {
		this.mob_hp = mob_hp;
	}
	public int getMob_exp() {
		return mob_exp;
	}
	public void setMob_exp(int mob_exp) {
		this.mob_exp = mob_exp;
	}
	public int getMob_speed() {
		return mob_speed;
	}
	public void setMob_speed(int mob_speed) {
		this.mob_speed = mob_speed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}