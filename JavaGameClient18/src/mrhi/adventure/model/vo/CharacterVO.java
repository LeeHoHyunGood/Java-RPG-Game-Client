package mrhi.adventure.model.vo;

import java.io.Serializable;

public class CharacterVO extends DataVO {
	private int chr_idx;
	private int acc_idx;
	private String chr_name;
    private int chr_type;
	private int chr_job;
	private int chr_level;
	private int chr_exp;
	private int chr_money; // 나중에 long으로 바꾸자
	private int chr_str;
	private int chr_dex;
	private int chr_int;
	private int chr_pro;
	private int chr_hp;
	private int chr_mp;
	private int chr_maxHp;
	private int chr_maxMp;
	private int chr_speed;
	private int chr_statPoint;
	private int chr_skillPoint;
	private int chr_mapid;
	private int chr_x;
	private int chr_y;
	private int chr_grade;
	private int direction;
	private boolean isWalking;
	private boolean isAttacking;
	private boolean dead;

	//	map, user_grade;
	//	equipmentList(착용한거 안한거 구분), 단축키리스트, 스킬리스트, 퀘스트 리스트
	
	public CharacterVO() { }
	
	public OtherCharacterVO makeInfoCharacter() {
		OtherCharacterVO infoChar = new OtherCharacterVO();
		infoChar.setChr_idx(chr_idx);
		infoChar.setChr_level(chr_level);
		infoChar.setChr_name(chr_name);
		infoChar.setChr_job(chr_job);
		infoChar.setChr_x(chr_x);
		infoChar.setChr_y(chr_y);
		infoChar.setChr_speed(chr_speed);
		infoChar.setChr_type(chr_type);
		infoChar.setDirection(direction);
		infoChar.setChr_mapid(chr_mapid);
		infoChar.setWalking(isWalking);
		infoChar.setAttacking(isAttacking);
		infoChar.setDead(dead);
		return infoChar;
	}
	
	public void updateCharacter(OtherCharacterVO oChar) {
		if(this.chr_idx==oChar.getChr_idx()) {
			this.chr_level = oChar.getChr_level();
			this.chr_x = oChar.getChr_x();
			this.chr_y = oChar.getChr_y();
			this.chr_type = oChar.getChr_type();
			this.chr_speed = oChar.getChr_speed();
			this.direction = oChar.getDirection();
			this.chr_mapid = oChar.getChr_mapid();
			this.isWalking = oChar.isWalking();
			this.isAttacking = oChar.isAttacking();
			this.dead = oChar.isDead();
		}
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getDamage() {
		return chr_str + chr_dex + chr_int + chr_pro + chr_level;
	}

	public int getChr_speed() {
		return chr_speed;
	}
	public void setChr_speed(int chr_speed) {
		this.chr_speed = chr_speed;
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

	public int getChr_idx() {
		return chr_idx;
	}

	public void setChr_idx(int chr_idx) {
		this.chr_idx = chr_idx;
	}

	public int getAcc_idx() {
		return acc_idx;
	}

	public void setAcc_idx(int acc_idx) {
		this.acc_idx = acc_idx;
	}

	public String getChr_name() {
		return chr_name;
	}

	public void setChr_name(String chr_name) {
		this.chr_name = chr_name;
	}

	public int getChr_level() {
		return chr_level;
	}

	public void setChr_level(int chr_level) {
		this.chr_level = chr_level;
	}

	public int getChr_job() {
		return chr_job;
	}

	public void setChr_job(int chr_job) {
		this.chr_job = chr_job;
	}

	public int getChr_money() {
		return chr_money;
	}

	public void setChr_money(int chr_money) {
		this.chr_money = chr_money;
	}

	public int getChr_str() {
		return chr_str;
	}

	public void setChr_str(int chr_str) {
		this.chr_str = chr_str;
	}

	public int getChr_dex() {
		return chr_dex;
	}

	public void setChr_dex(int chr_dex) {
		this.chr_dex = chr_dex;
	}

	public int getChr_int() {
		return chr_int;
	}

	public void setChr_int(int chr_int) {
		this.chr_int = chr_int;
	}

	public int getChr_pro() {
		return chr_pro;
	}

	public void setChr_pro(int chr_pro) {
		this.chr_pro = chr_pro;
	}

	public int getChr_hp() {
		return chr_hp;
	}

	public void setChr_hp(int chr_hp) {
		this.chr_hp = chr_hp;
	}

	public int getChr_mp() {
		return chr_mp;
	}

	public void setChr_mp(int chr_mp) {
		this.chr_mp = chr_mp;
	}

	public int getChr_maxHp() {
		return chr_maxHp;
	}

	public void setChr_maxHp(int chr_maxHp) {
		this.chr_maxHp = chr_maxHp;
	}

	public int getChr_maxMp() {
		return chr_maxMp;
	}

	public void setChr_maxMp(int chr_maxMp) {
		this.chr_maxMp = chr_maxMp;
	}

	public int getChr_statPoint() {
		return chr_statPoint;
	}

	public void setChr_statPoint(int chr_statPoint) {
		this.chr_statPoint = chr_statPoint;
	}

	public int getChr_skillPoint() {
		return chr_skillPoint;
	}

	public void setChr_skillPoint(int chr_skillPoint) {
		this.chr_skillPoint = chr_skillPoint;
	}

	public int getChr_grade() {
		return chr_grade;
	}

	public void setChr_grade(int chr_grade) {
		this.chr_grade = chr_grade;
	}
	public int getChr_type() {
		return chr_type;
	}

	public void setChr_type(int chr_type) {
		this.chr_type = chr_type;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getChr_exp() {
		return chr_exp;
	}

	public void setChr_exp(int chr_exp) {
		this.chr_exp = chr_exp;
	}
	
	public boolean isWalking() {
		return isWalking;
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

	@Override
	public String toString() {
		return "CharacterVO [chr_idx=" + chr_idx + ", acc_idx=" + acc_idx + ", chr_name=" + chr_name + ", chr_type="
				+ chr_type + ", chr_job=" + chr_job + ", chr_level=" + chr_level + ", chr_exp=" + chr_exp
				+ ", chr_money=" + chr_money + ", chr_str=" + chr_str + ", chr_dex=" + chr_dex + ", chr_int=" + chr_int
				+ ", chr_pro=" + chr_pro + ", chr_hp=" + chr_hp + ", chr_mp=" + chr_mp + ", chr_maxHp=" + chr_maxHp
				+ ", chr_maxMp=" + chr_maxMp + ", chr_speed=" + chr_speed + ", chr_statPoint=" + chr_statPoint
				+ ", chr_skillPoint=" + chr_skillPoint + ", chr_mapid=" + chr_mapid + ", chr_x=" + chr_x + ", chr_y="
				+ chr_y + ", chr_grade=" + chr_grade + ", direction=" + direction + "]";
	}
}
