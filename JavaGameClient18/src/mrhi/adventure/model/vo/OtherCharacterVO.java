package mrhi.adventure.model.vo;

import java.io.Serializable;

public class OtherCharacterVO extends DataVO{
	private int chr_idx;
	private String chr_name;
	private int chr_type;
	private int chr_level;
	private int chr_speed;
	private int direction;
	private int chr_job;
	private boolean isWalking;
	private boolean isAttacking;
	private boolean dead;
	private int chr_mapid;
	private int chr_x;
	private int chr_y;
	//	private List<Equip> EquipList;

	public OtherCharacterVO() { }

	public boolean isWalking() {
		return isWalking;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public int getChr_job() {
		return chr_job;
	}

	public void setChr_job(int chr_job) {
		this.chr_job = chr_job;
	}

	public int getChr_idx() {
		return chr_idx;
	}

	public void setChr_idx(int chr_idx) {
		this.chr_idx = chr_idx;
	}

	public String getChr_name() {
		return chr_name;
	}

	public void setChr_name(String chr_name) {
		this.chr_name = chr_name;
	}

	public int getChr_type() {
		return chr_type;
	}

	public void setChr_type(int chr_type) {
		this.chr_type = chr_type;
	}

	public int getChr_level() {
		return chr_level;
	}

	public void setChr_level(int chr_level) {
		this.chr_level = chr_level;
	}

	public int getChr_speed() {
		return chr_speed;
	}

	public void setChr_speed(int chr_speed) {
		this.chr_speed = chr_speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getChr_x() {
		return chr_x;
	}

	public void setChr_x(int chr_x) {
		this.chr_x = chr_x;
	}

	public int getChr_y() {
		return chr_y;
	}

	public void setChr_y(int chr_y) {
		this.chr_y = chr_y;
	}

	public int getChr_mapid() {
		return chr_mapid;
	}

	public void setChr_mapid(int chr_mapid) {
		this.chr_mapid = chr_mapid;
	}


}
