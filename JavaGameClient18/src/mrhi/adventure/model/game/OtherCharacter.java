package mrhi.adventure.model.game;

import java.awt.Rectangle;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.packet.MyPacket;
import mrhi.adventure.model.vo.OtherCharacterVO;

public class OtherCharacter implements Observer {
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
	private int view_x;
	private int view_y;
	
	public OtherCharacter(OtherCharacterVO infoChar) {
		super();
		Client.getInstance().getGame().getMainCharacter().addObserver(this);
		this.chr_idx = infoChar.getChr_idx();
		this.chr_name = infoChar.getChr_name();
		this.chr_type = infoChar.getChr_type();
		this.chr_level = infoChar.getChr_level();
		this.chr_speed = infoChar.getChr_speed();
		this.direction = infoChar.getDirection();
		this.chr_job = infoChar.getChr_job();
		this.isWalking = infoChar.isWalking();
		this.isAttacking = infoChar.isAttacking(); 
		this.chr_mapid = infoChar.getChr_mapid();
		this.chr_x = infoChar.getChr_x();
		this.chr_y = infoChar.getChr_y();
		this.view_x = infoChar.getChr_x();
		this.view_y = infoChar.getChr_y();
		this.dead = infoChar.isDead();
	}
	
	public OtherCharacterVO getOtherCharacterVOforAttack() { 
		OtherCharacterVO ocharVO = new OtherCharacterVO();
		ocharVO.setChr_idx(this.chr_idx);
		ocharVO.setChr_name(this.chr_name);
		ocharVO.setChr_type(this.chr_type);
		return ocharVO;
	}
	
	public void update(OtherCharacterVO infoChar) {
		this.chr_type = infoChar.getChr_type();
		this.chr_level = infoChar.getChr_level();
		this.chr_speed = infoChar.getChr_speed();
		this.direction = infoChar.getDirection();
		this.isWalking = infoChar.isWalking();
		this.isAttacking = infoChar.isAttacking(); 
		this.chr_mapid = infoChar.getChr_mapid();
		this.chr_x = infoChar.getChr_x();
		this.chr_y = infoChar.getChr_y();
		this.dead = infoChar.isDead();
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void autoMove() {
		if(isWalking && !isAttacking)
		{
			switch(direction) {
			case 0:
				break;
			case 1:
				this.chr_y-=chr_speed;
				break;
			case 2:
				this.chr_x+=chr_speed;
				break;
			case 3:
				this.chr_y+=chr_speed;
				break;
			case 4:
				this.chr_x-=chr_speed;
				break;
			}
		}
		viewMove();
	}
	
	public void viewMove() {
		if(this.chr_y>view_y)
			view_y+=chr_speed;
		else if(this.chr_y<view_y)
			view_y-=chr_speed;
		
		if(this.chr_x>view_x)
			view_x+=chr_speed;
		else if(this.chr_x<view_x)
			view_x-=chr_speed;
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
	public int getChr_job() {
		return chr_job;
	}
	public void setChr_job(int chr_job) {
		this.chr_job = chr_job;
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
	public int getChr_mapid() {
		return chr_mapid;
	}
	public void setChr_mapid(int chr_mapid) {
		this.chr_mapid = chr_mapid;
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
	public int getView_x() {
		return view_x;
	}
	public void setView_x(int view_x) {
		this.view_x = view_x;
	}
	public int getView_y() {
		return view_y;
	}
	public void setView_y(int view_y) {
		this.view_y = view_y;
	}

	public void hurt(MainCharacter attacker) {
		Client.getInstance().getConnectionManager().send(new MyPacket(70, this.getOtherCharacterVOforAttack()));
	}

	public Rectangle getRange() { // 크기추가하면 레인지변경해야됨
		return new Rectangle(this.chr_x, this.chr_y, 64, 64); 
	}

	@Override
	public void update(Object obj) {
		MainCharacter attacker = (MainCharacter)obj;
		if(attacker.getRange().intersects(getRange()))
			this.hurt(attacker);
	}
}