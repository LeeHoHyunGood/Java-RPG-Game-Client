package mrhi.adventure.model.game;

import java.awt.Rectangle;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.packet.MyPacket;
import mrhi.adventure.model.vo.MobVO;

public class Mob implements Observer{
	private int mob_idx;
	private int gen_idx;
	private String mob_name;
	private int mob_level;
	private int mob_hp;
	private int mob_exp;
	private int mob_speed;
	private int x;
	private int y;
	
	public Mob() {
		super();
		Client.getInstance().getGame().getMainCharacter().addObserver(this);
	}
	
	public Mob(MobVO mobVO) {
		this();
		this.mob_idx = mobVO.getMob_idx();
		this.gen_idx = mobVO.getGen_idx();
		this.mob_name = mobVO.getMob_name();
		this.mob_hp = mobVO.getMob_hp();
		this.mob_exp = mobVO.getMob_exp();
		this.mob_speed = mobVO.getMob_speed();
		this.mob_level = mobVO.getMob_level();
		this.x = mobVO.getX();
		this.y = mobVO.getY();
	}
	
	public MobVO getMobVO() {
		MobVO mobVO = new MobVO();
		mobVO.setGen_idx(gen_idx);
		mobVO.setMob_hp(mob_hp);;
		mobVO.setMob_exp(mob_exp);
		mobVO.setMob_level(mob_level);
		mobVO.setMob_idx(mob_idx);
		mobVO.setMob_name(mob_name);
		mobVO.setMob_speed(mob_speed);
		mobVO.setX(gen_idx);
		mobVO.setY(y);
		return mobVO;
	}
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

	public void hurt(MainCharacter attacker) {
		Client.getInstance().getConnectionManager().send(new MyPacket(40, this.getMobVO()));
	}

	public Rectangle getRange() { // 크기추가하면 레인지변경해야됨
		return new Rectangle(x, y, 64, 64); 
	}

	@Override
	public void update(Object obj) {
		MainCharacter attacker = (MainCharacter)obj;
		if(attacker.getRange().intersects(getRange()))
			this.hurt(attacker);
	}
}
