package mrhi.adventure.model.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.vo.CharacterVO;
import mrhi.adventure.model.vo.IntegerVO;
import mrhi.adventure.model.vo.ItemVO;
import mrhi.adventure.model.vo.OtherCharacterVO;

public class MainCharacter implements Observee {

	private List<Observer> observers = new LinkedList<>();
	private List<Observer> removeObList = new LinkedList<>();
	private List<JItem> itemList = new LinkedList<>();
	private List<ItemVO> itemVOList = new ArrayList<>();
	private JItem weapon = null;
	private CharacterVO characterVO;

	private int power = 0;
	private int mpower = 0;
	private int def = 0;
	private int mdef = 0;

	public MainCharacter() { }

	public MainCharacter(CharacterVO characterVO) {
		super();
		this.characterVO = characterVO;
	}

	public void updateState(CharacterVO charVO) {
		this.characterVO.setChr_level(charVO.getChr_level());
		this.characterVO.setChr_exp(charVO.getChr_exp());
		this.characterVO.setChr_statPoint(charVO.getChr_statPoint());
		this.characterVO.setChr_skillPoint(charVO.getChr_skillPoint());
	}

	public void updateStat(CharacterVO charVO) {
		this.characterVO.setChr_str(charVO.getChr_str());
		this.characterVO.setChr_dex(charVO.getChr_dex());
		this.characterVO.setChr_int(charVO.getChr_int());
		this.characterVO.setChr_pro(charVO.getChr_pro());
	}

	public void gainItem() {
		if(itemList.size()<48) {
			for(JItem item : Client.getInstance().getGame().getCurrentMap().getItemList()) {
				if(this.getBounds().contains(new Point(item.getX(), item.getY()))){
					Client.getInstance().getGame().gatherItem(item.getItemVO());
					break;
				}
			}
		}
	}

	public void equipWeapon(JItem item) {
		unEquipWeapon();
		if(characterVO.getChr_job()==item.getItem_job()) {
			itemList.remove(item);
			this.weapon = item;
		}
	}

	public void unEquipWeapon() {
		if(this.weapon!=null) {
			itemList.add(this.weapon);
			this.weapon = null;
		}
	}

	public void saleItem(JItem item) {
		ItemVO itemVO = new ItemVO();
		itemVO.setOwn_idx(item.getOwn_idx());
		itemVO.setItem_idx(item.getItem_idx());
		itemVO.setItem_value(item.getItem_value());
		Client.getInstance().getGame().saleItem(itemVO);
	}
	
	public void upStat(int type) {
		if(characterVO.getChr_statPoint()>0) {
			characterVO.setChr_statPoint(characterVO.getChr_statPoint()-1);
			IntegerVO integerVO = new IntegerVO(type);
			Client.getInstance().getGame().upStat(integerVO);
		}
	}
	
	public void upMoney(CharacterVO charVO) {
		this.characterVO.setChr_money(charVO.getChr_money());
	}
	
	public void downMoney(CharacterVO charVO) {
		this.characterVO.setChr_money(charVO.getChr_money());
	}
	
	public int getStr() {
		return characterVO.getChr_str();
	}

	public int getDex() {
		return characterVO.getChr_dex();
	}	

	public int getInt() {
		return characterVO.getChr_int();
	}	

	public int getPro() {
		return characterVO.getChr_pro();
	}	

	public void setStr(int value) {
		characterVO.setChr_str(value);
	}
	public void setDex(int value) {
		characterVO.setChr_dex(value);
	}
	public void setInt(int value) {
		characterVO.setChr_int(value);
	}
	public void setPro(int value) {
		characterVO.setChr_pro(value);
	}
	public int getStatPoint() {
		return characterVO.getChr_statPoint();
	}
	public int getSkillPoint() {
		return characterVO.getChr_skillPoint();
	}

	public boolean isDead() {
		return characterVO.isDead();
	}

	public void setDead(boolean dead) {
		characterVO.setDead(dead);
	}

	public void setHp(int hp) {
		characterVO.setChr_hp(hp);
	}

	public void minusHp(int damage) {
		characterVO.setChr_hp(characterVO.getChr_hp()-damage);
	}

	public int getHp() {
		return characterVO.getChr_hp();
	}

	public int getMaxHp() {
		return characterVO.getChr_maxHp();
	}

	public int getMp() {
		return characterVO.getChr_mp();
	}

	public int getMaxMp() {
		return characterVO.getChr_maxMp();
	}

	public int getLevel() {
		return characterVO.getChr_level();
	}

	public int getExp() {
		return characterVO.getChr_exp();
	}

	public int getMoney() {
		return characterVO.getChr_money();
	}

	public int getJob() {
		return characterVO.getChr_job();
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMpower() {
		return mpower;
	}

	public void setMpower(int mpower) {
		this.mpower = mpower;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getMdef() {
		return mdef;
	}

	public void setMdef(int mdef) {
		this.mdef = mdef;
	}

	public void setMapid(int idx) {
		characterVO.setChr_mapid(idx);
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public void setMap(GameMap mvo) {
		setMapid(mvo.getMap_idx());
	}

	public int getChrIdx()
	{
		return characterVO.getChr_idx();
	}

	public OtherCharacterVO makeInfoCharacter() {
		OtherCharacterVO infoChar = characterVO.makeInfoCharacter();
		return infoChar;
	}

	public int getX(){
		return characterVO.getChr_x();
	}

	public int getY(){
		return characterVO.getChr_y();
	}

	public void setDirection(int direction) {
		characterVO.setDirection(direction);
	}

	public int getDirection() {
		return characterVO.getDirection();
	}

	public int getSpeed() {
		return characterVO.getChr_speed();
	}

	public int getType() {
		return characterVO.getChr_type();
	}


	public void attack() {
		if(!characterVO.isDead()) {
			if(!characterVO.isAttacking())
				new AttackThread().start();
		}
	}

	public void autoMove() {
		if(characterVO.isWalking() && !characterVO.isAttacking() && !characterVO.isDead()) {
			int nextX = 0;
			int nextY = 0;
			switch(characterVO.getDirection()) {
			case 1:
				nextY = characterVO.getChr_y()-characterVO.getChr_speed();
				break;
			case 2:
				nextX = characterVO.getChr_x()+characterVO.getChr_speed();
				break;
			case 3:
				nextY = characterVO.getChr_y()+characterVO.getChr_speed();
				break;
			case 4:
				nextX = characterVO.getChr_x()-characterVO.getChr_speed();
				break;
			}
			if(0 < nextX && nextX < Client.getInstance().getGame().getCurrentMap().getMap_width()-64)
				characterVO.setChr_x(nextX);
			if(0 < nextY && nextY < Client.getInstance().getGame().getCurrentMap().getMap_height()-64)
				characterVO.setChr_y(nextY);

		}
	}

	public CharacterVO getCharacterVO() {
		return characterVO;
	}

	public void setCharacterVO(CharacterVO characterVO) {
		this.characterVO = characterVO;
	}

	public Rectangle getRange() {
		Rectangle retRect = null;

		switch(getJob()) {
		case 1:
			switch(this.getDirection())
			{
			case 1:
				retRect = new Rectangle(getX(), getY()-64, 64, 64);
				break;
			case 2:
				retRect = new Rectangle(getX()+64, getY(), 64, 64);
				break;
			case 3:
				retRect = new Rectangle(getX(), getY()+64, 64, 64);
				break;
			case 4:
				retRect = new Rectangle(getX()-64, getY(), 64, 64);
				break;
			}
			break;
		case 2:
			switch(this.getDirection())
			{
			case 1:
				retRect = new Rectangle(getX(), getY()-64, 64, 128);
				break;
			case 2:
				retRect = new Rectangle(getX()+64, getY(), 128, 64);
				break;
			case 3:
				retRect = new Rectangle(getX(), getY()+64, 64, 128);
				break;
			case 4:
				retRect = new Rectangle(getX()-64, getY(), 128, 64);
				break;
			}
			break;
		case 3:
			switch(this.getDirection())
			{
			case 1:
				retRect = new Rectangle(getX(), getY()-64*10, 64, 64*10);
				break;
			case 2:
				retRect = new Rectangle(getX()+64, getY(), 64*10, 64);
				break;
			case 3:
				retRect = new Rectangle(getX(), getY()+64, 64, 64*10);
				break;
			case 4:
				retRect = new Rectangle(getX()-64*10, getY(), 64*10, 64);
				break;
			}
			break;
		case 4:
			retRect = new Rectangle(getX()-64*2, getY()-64*2, 64*4, 64*4);
			break;
		}
		return retRect;
	}

	public List<Observer> getRemoveObList() {
		return removeObList;
	}

	public void removeNextObserver(Observer obs) {
		removeObList.add(obs);
	}

	public JItem getWeapon() {
		return weapon;
	}

	public void setWeapon(JItem weapon) {
		this.weapon = weapon;
	}

	public List<ItemVO> getItemVOList() {
		return itemVOList;
	}

	public void setItemVOList(List<ItemVO> itemVOList) {
		this.itemVOList = itemVOList;
	}

	public List<JItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<JItem> itemList) {
		this.itemList = itemList;
	}

	public void setRemoveObList(List<Observer> removeObList) {
		this.removeObList = removeObList;
	}

	public void walkingStart() {
		this.characterVO.setWalking(true);
	}

	public void walkingStop() {
		this.characterVO.setWalking(false);
	}

	public boolean isWalking() {
		return this.characterVO.isWalking();
	}

	public void attackStart() {
		this.characterVO.setAttacking(true);
	}

	public void attackStop() {
		this.characterVO.setAttacking(false);
	}

	public boolean isAttacking() {
		return this.characterVO.isAttacking();
	}

	public String getName() {
		return characterVO.getChr_name();
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 64, 64);
	}

	@Override
	public void addObserver(Observer obs) {
		observers.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		observers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		observers.removeAll(removeObList);
		for(Observer obs : observers)
		{
			obs.update(this);
		}

	}

	class AttackThread extends Thread {
		@Override
		public void run() {
			try {
				attackStart();
				int time=0;
				switch(getJob()) {
				case 1:
					time = 600;
					break;
				case 2:
					time = 800;
					break;
				case 3:
					time = 1300;
					break;
				case 4:
					time = 700;
					break;
				}
				Thread.sleep(time);
				notifyObservers();
				attackStop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
