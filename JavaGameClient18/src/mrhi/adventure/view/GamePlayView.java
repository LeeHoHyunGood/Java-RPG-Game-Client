package mrhi.adventure.view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.game.Chat;
import mrhi.adventure.model.game.JItem;
import mrhi.adventure.model.game.MainCharacter;
import mrhi.adventure.model.game.Mob;
import mrhi.adventure.model.game.OtherCharacter;
import mrhi.adventure.model.vo.ChatVO;
import mrhi.adventure.model.vo.ItemVO;

public class GamePlayView extends BasicGameState {
	private TiledMap grassMap;//땅
	private Animation mobAni;
	private Map<String, CharacterAnimation> cAnimationMap;
	private Map<String, DirectionAnimation> dAnimationMap;
	private Map<String, Image> imageMap;
	private boolean showInventory = false, showMenu = false;
	private boolean showSpell = false, showStat = false, showShop = false;
	private boolean focusTextField = true;
	private TextField textField;

	private Font defaultFont;
	private int viewX, viewY;


	public GamePlayView(int state) {
		super();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		cAnimationMap = new HashMap<>();
		dAnimationMap = new HashMap<>();
		imageMap = new HashMap<>();
	}


	public void renderCharacter(OtherCharacter otherChar, Graphics g) {

		String charJob = null;
		String weaponMotion;
		String charMotion;

		int x = viewX - (Client.getInstance().getGame().getMainCharacter().getX()-otherChar.getView_x());
		int y = viewY - (Client.getInstance().getGame().getMainCharacter().getY()-otherChar.getView_y());

		if(otherChar.getChr_job()==1) {
			charJob = "warrior";
		} else if(otherChar.getChr_job()==2) {
			charJob = "knight";
		} else if(otherChar.getChr_job()==3) {
			charJob = "archer";
		} else if(otherChar.getChr_job()==4) {
			charJob = "magician";
		} else if(otherChar.getChr_job()==5) {
			charJob = "programmer";
		}

		charMotion = String.format("%s%s.png", charJob, otherChar.getChr_type());
		weaponMotion = String.format("%s.png", charJob);

		if(otherChar.isDead()) {
			if(this.cAnimationMap.get(charMotion).getHurt().getFrame()==5)
				this.cAnimationMap.get(charMotion).getHurt().getImage(5).draw(x, y);
			else
				this.cAnimationMap.get(charMotion).getHurt().draw(x, y);
		} else if(otherChar.isAttacking()) {
			if(otherChar.getChr_job()==1) {
				this.cAnimationMap.get(charMotion).getSlash(otherChar.getDirection()).draw(x, y);
			} else if(otherChar.getChr_job()==2) {
				this.cAnimationMap.get(charMotion).getThrust(otherChar.getDirection()).draw(x, y);
			} else if(otherChar.getChr_job()==3) {
				this.cAnimationMap.get(charMotion).getShoot(otherChar.getDirection()).draw(x, y);
			} else if(otherChar.getChr_job()==4) {
				this.cAnimationMap.get(charMotion).getSpell(otherChar.getDirection()).draw(x, y);
			} else if(otherChar.getChr_job()==5) {
				//this.cAnimationMap.get(charmotion).getSlash(otherChar.getChr_job());
			}

			if(otherChar.getChr_job()==3) 
				this.dAnimationMap.get(weaponMotion).getAnimation(otherChar.getDirection()).draw(x, y);
			else
				this.dAnimationMap.get(weaponMotion).getAnimation(otherChar.getDirection()).draw(x-64, y-64);

		} else if(otherChar.isWalking()) {
			this.cAnimationMap.get(charMotion).getWalk(otherChar.getDirection()).draw(x, y);
		} else {
			this.cAnimationMap.get(charMotion).getStop(otherChar.getDirection()).draw(x, y);
		}
		g.drawString(otherChar.getChr_name(), x+32-otherChar.getChr_name().length()*4, y+64);

	}

	public void renderMainCharacter(MainCharacter mainChar, Graphics g) {

		String charJob = null;
		String charMotion;
		String weaponMotion;
		int x = viewX;
		int y = viewY;

		if(mainChar.getJob()==1) {
			charJob = "warrior";
		} else if(mainChar.getJob()==2) {
			charJob = "knight";
		} else if(mainChar.getJob()==3) {
			charJob = "archer";
		} else if(mainChar.getJob()==4) {
			charJob = "magician";
		} else if(mainChar.getJob()==5) {
			charJob = "programmer";
		}

		charMotion = String.format("%s%s.png", charJob, mainChar.getType());
		weaponMotion = String.format("%s.png", charJob);

		if(mainChar.isDead()) {
			if(this.cAnimationMap.get(charMotion).getHurt().getFrame()==5)
				this.cAnimationMap.get(charMotion).getHurt().getImage(5).draw(x, y);
			else
				this.cAnimationMap.get(charMotion).getHurt().draw(x, y);
		} else if(mainChar.isAttacking()) {
			if(mainChar.getJob()==1) {
				this.cAnimationMap.get(charMotion).getSlash(mainChar.getDirection()).draw(x, y);
			} else if(mainChar.getJob()==2) {
				this.cAnimationMap.get(charMotion).getThrust(mainChar.getDirection()).draw(x, y);
			} else if(mainChar.getJob()==3) {
				this.cAnimationMap.get(charMotion).getShoot(mainChar.getDirection()).draw(x, y);
			} else if(mainChar.getJob()==4) {
				this.cAnimationMap.get(charMotion).getSpell(mainChar.getDirection()).draw(x, y);
			} else if(mainChar.getJob()==5) {
				//this.cAnimationMap.get(charmotion).getSlash(otherChar.getChr_job());
			}

			if(mainChar.getJob()==3) 
				this.dAnimationMap.get(weaponMotion).getAnimation(mainChar.getDirection()).draw(x, y);
			else
				this.dAnimationMap.get(weaponMotion).getAnimation(mainChar.getDirection()).draw(x-64, y-64);

		} else if(mainChar.isWalking()) {
			this.cAnimationMap.get(charMotion).getWalk(mainChar.getDirection()).draw(x, y);
		} else {
			this.cAnimationMap.get(charMotion).getStop(mainChar.getDirection()).draw(x, y);
		}
		g.setColor(Color.black);
		g.drawString(mainChar.getName(), x+32-mainChar.getName().length()*4, y+64);
	}



	public void renderInterface(GameContainer gameContainer, Graphics g) {
		imageMap.get("gameinterface.png").draw();
		imageMap.get("hp_bar.png").draw(107, 17, (int)(160*((double)Client.getInstance().getGame().getMainCharacter().getHp()/Client.getInstance().getGame().getMainCharacter().getMaxHp())), 15);
		imageMap.get("mp_bar.png").draw(107, 34, 160, 15);

		if(Client.getInstance().getGame().getMainCharacter().getWeapon()!=null) {
			imageMap.get(Client.getInstance().getGame().getMainCharacter().getWeapon().getItem_idx()+ ".png").draw(32, 32);
		}
		g.setColor(Color.white);
		g.drawString(Client.getInstance().getGame().getMainCharacter().getHp()+"/"+Client.getInstance().getGame().getMainCharacter().getMaxHp(), 120, 16);
		g.drawString(Client.getInstance().getGame().getMainCharacter().getMp()+"/"+Client.getInstance().getGame().getMainCharacter().getMaxMp(), 120, 33);

		g.setColor(Color.black);
		g.drawString("level : " + Client.getInstance().getGame().getMainCharacter().getLevel(), 30, 160);
		g.drawString("exp   : " + Client.getInstance().getGame().getMainCharacter().getExp(), 30, 180);

		if(showInventory) {
			g.setColor(Color.white);
			imageMap.get("inventory.png").draw(440, 0);
			for(int i=0; i<Client.getInstance().getGame().getMainCharacter().getItemList().size(); i++) {
				JItem item = Client.getInstance().getGame().getMainCharacter().getItemList().get(i);
				imageMap.get(item.getItem_idx()+".png").draw(475+49*(i%6), 85+49*(i/6));//+50*i
			}
			g.drawString(Client.getInstance().getGame().getMainCharacter().getMoney()+"", 690, 490);
		} 

		if (showShop) {
			g.setColor(Color.white);
			imageMap.get("shop.png").draw(25, 10);
			for (int i = 0; i < Client.getInstance().getGame().getMainCharacter().getItemVOList().size(); i++) {
				ItemVO itemVO = Client.getInstance().getGame().getMainCharacter().getItemVOList().get(i);
				imageMap.get(itemVO.getItem_idx()+".png").draw(40+69*(i%5), 70+69*(i/5));
				g.drawString(itemVO.getItem_value()+"", 40+69*(i%5), 100+69*(i/5));
			}
			//			Client.getInstance().getGame().getMainCharacter().
		}

		if(showSpell) {
			imageMap.get("spell.png").draw(440, 0);
		}

		if(showMenu) {
			imageMap.get("MENU.png").draw(300, 100);
		}

		if(showStat) {
			imageMap.get("stat.png").draw(25, 10);
			g.setColor(Color.white);
			g.drawString("NAME  : " + Client.getInstance().getGame().getMainCharacter().getName(), 25+30, 25+60);
			g.drawString("JOB   : " + Client.getInstance().getGame().getMainCharacter().getJob(), 25+30, 25+80);
			g.drawString("TYPE  : " + Client.getInstance().getGame().getMainCharacter().getType(), 25+30, 25+100);
			g.drawString("LEVEL : " + Client.getInstance().getGame().getMainCharacter().getLevel(), 25+30, 25+120);
			g.drawString("MaxHp : " + Client.getInstance().getGame().getMainCharacter().getMaxHp(), 25+30, 25+140);
			g.drawString("HP    : " + Client.getInstance().getGame().getMainCharacter().getHp(), 25+30, 25+160);
			g.drawString("MaxMp : " + Client.getInstance().getGame().getMainCharacter().getMaxMp(), 25+30, 25+180);
			g.drawString("MP    : " + Client.getInstance().getGame().getMainCharacter().getMp(), 25+30, 25+200);
			g.drawString("Speed : " + Client.getInstance().getGame().getMainCharacter().getSpeed(), 25+30, 25+220);

			g.drawString("STR   : " + Client.getInstance().getGame().getMainCharacter().getStr(), 25+30, 25+260);
			g.drawString("DEX   : " + Client.getInstance().getGame().getMainCharacter().getDex(), 25+30, 25+280);
			g.drawString("INT   : " + Client.getInstance().getGame().getMainCharacter().getInt(), 25+30, 25+300);
			g.drawString("PRO   : " + Client.getInstance().getGame().getMainCharacter().getPro(), 25+30, 25+320);

			g.drawString("+", 25+180, 25+260);
			g.drawString("+", 25+180, 25+280);
			g.drawString("+", 25+180, 25+300);
			g.drawString("+", 25+180, 25+320);


			g.drawString("Stat  Point : " + Client.getInstance().getGame().getMainCharacter().getStatPoint(), 25+30, 25+380);
			g.drawString("Skill Point : " + Client.getInstance().getGame().getMainCharacter().getSkillPoint(), 25+30, 25+400);
		}

		int gap = 0;
		if(focusTextField) {
			gap = 20;
			textField.render(gameContainer, g);
		}

		imageMap.get("chat.png").draw(175, 440-gap);
		List<Chat> chatList = Client.getInstance().getGame().getCurrentMap().getChatList();
		int size;
		if(chatList.size()>5)
			size = 5;
		else
			size = chatList.size();
		g.setColor(Color.black);
		for(int i=0; i<size; i++) {
			g.drawString(chatList.get(chatList.size()-1-i).getName() + " : " + chatList.get(chatList.size()-1-i).getText(), 190, 520-i*20-gap);
		}
	}

	public void renderMap() {
		int startX;
		int startY;

		if(Client.getInstance().getGame().getMainCharacter().getX()<370)
			startX = 0;
		else if(Client.getInstance().getGame().getMainCharacter().getX()>Client.getInstance().getGame().getCurrentMap().getMap_width()-430)
			startX = Client.getInstance().getGame().getCurrentMap().getMap_width()/10-80;
		else
			startX = Client.getInstance().getGame().getMainCharacter().getX()/10-37;

		if(Client.getInstance().getGame().getMainCharacter().getY()<270)
			startY = 0;
		else if(Client.getInstance().getGame().getMainCharacter().getY()>Client.getInstance().getGame().getCurrentMap().getMap_height()-330)
			startY = Client.getInstance().getGame().getCurrentMap().getMap_height()/10-60;
		else
			startY = Client.getInstance().getGame().getMainCharacter().getY()/10-27;

		grassMap.render(0, 0, startX, startY, 80, 60);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		renderMap();
		renderMainCharacter(Client.getInstance().getGame().getMainCharacter(), g);
		for(OtherCharacter otherChar : Client.getInstance().getGame().getCurrentMap().getOtherCharList())
		{
			if(otherChar.getChr_idx()!=Client.getInstance().getGame().getMainCharacter().getChrIdx())
				renderCharacter(otherChar, g);
		}
		renderItem();
		renderMob(g);
		renderInterface(gameContainer, g);
	}

	private void renderItem() {
		for(int i=0; i<Client.getInstance().getGame().getCurrentMap().getItemList().size(); i++) {
			JItem item = Client.getInstance().getGame().getCurrentMap().getItemList().get(i);
			this.imageMap.get(item.getItem_idx()+".png").draw(
					viewX - (Client.getInstance().getGame().getMainCharacter().getX()-item.getX()),
					viewY - (Client.getInstance().getGame().getMainCharacter().getY()-item.getY()));
		}

	}

	private void renderMob(Graphics g) {
		for(int i=0; i<Client.getInstance().getGame().getCurrentMap().getMobList().size(); i++)
		{
			Mob mob = Client.getInstance().getGame().getCurrentMap().getMobList().get(i);
			if(Client.getInstance().getGame().getMainCharacter().getX()-400-30 < mob.getX() &&  
					Client.getInstance().getGame().getMainCharacter().getX()+400+30 > mob.getX() &&
					Client.getInstance().getGame().getMainCharacter().getY()-300-30 < mob.getY() &&  
					Client.getInstance().getGame().getMainCharacter().getY()+300+30 > mob.getY()) {
				mobAni.draw(viewX - (Client.getInstance().getGame().getMainCharacter().getX()-mob.getX()),
						viewY - (Client.getInstance().getGame().getMainCharacter().getY() - mob.getY()));
			}
		}
	}

	//	public void loadItemImage(Map<String, Image> imageMap, String source) {
	//		File dir = new File(source);
	//		File[] fileList = dir.listFiles();
	//
	//		for (int i = 0; i < fileList.length; i++) {
	//			File file = fileList[i];
	//			if (file.isFile()) {
	//				try {
	//					imageMap.put(file.getName(), new Image(source + "/" + file.getName()));
	//				} catch (SlickException e) {
	//					e.printStackTrace();
	//				}
	//			} else if (file.isDirectory()) {
	//				loadItemImage(imageMap, source + "/" + file.getName());
	//			}
	//		}
	//	}

	public void loadCAnimation(Map<String, CharacterAnimation> animationMap, String source) {

		File dir = new File(source);
		File[] fileList = dir.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			if (file.isFile()) {
				SpriteSheet sheet = null;
				try {
					sheet = new SpriteSheet(source + "/" + file.getName(), 64, 64);
				} catch (SlickException e) {
					e.printStackTrace();
				}
				animationMap.put(file.getName(), new CharacterAnimation(sheet));
			} else if (file.isDirectory()) {
				loadCAnimation(animationMap, source + "/" + file.getName());
			}
		}
	}


	public void loadImage(Map<String, Image> imageMap, String source) {

		File dir = new File(source);
		File[] fileList = dir.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			if (file.isFile()) {
				try {
					imageMap.put(file.getName(), new Image(source + "/" + file.getName()));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			} else if (file.isDirectory()) {
				loadImage(imageMap, source + "/" + file.getName());
			}
		}
	}

	private void loadResource() throws SlickException {
		grassMap = new TiledMap("data/map5.tmx");
		this.loadCAnimation(cAnimationMap, "image/charmotion");
		this.loadImage(imageMap, "image/interface");
		this.loadImage(imageMap, "image/item");

		SpriteSheet sheet = new SpriteSheet("image/weapon/knight.png", 192, 192);
		dAnimationMap.put("knight.png", new DirectionAnimation(sheet));
		sheet = new SpriteSheet("image/weapon/warrior.png", 192, 192);
		dAnimationMap.put("warrior.png", new DirectionAnimation(sheet));
		sheet = new SpriteSheet("image/weapon/magician.png", 192, 192);
		dAnimationMap.put("magician.png", new DirectionAnimation(sheet));
		sheet = new SpriteSheet("image/weapon/archer.png", 64, 64);
		dAnimationMap.put("archer.png", new DirectionAnimation(sheet));

		//		this.animationMap.put(key, value);
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
		gameContainer.getInput().clearKeyPressedRecord();

		this.loadResource();
		defaultFont = new TrueTypeFont (new java.awt.Font ("Arial", java.awt.Font.BOLD, 15), false);
		textField = new TextField(gameContainer, defaultFont, 175, 520, 455, 25);
		gameContainer.setMouseCursor(imageMap.get("cursor.png"), 0,0); 
		SpriteSheet mobAnimation = new SpriteSheet("image/combat_dummy/BODY_animation.png", 64, 64);
		mobAni = new Animation(mobAnimation, 100);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int arg2) throws SlickException {
		if(Client.getInstance().getGame().getMainCharacter().getX()<370) {
			viewX=Client.getInstance().getGame().getMainCharacter().getX();
		} else if(Client.getInstance().getGame().getMainCharacter().getX()>Client.getInstance().getGame().getCurrentMap().getMap_width()-430) {
			viewX= 370+Client.getInstance().getGame().getMainCharacter().getX()-(Client.getInstance().getGame().getCurrentMap().getMap_width()-430);
		} else {
			viewX=370;
		}
		if(Client.getInstance().getGame().getMainCharacter().getY()<270) {
			viewY=Client.getInstance().getGame().getMainCharacter().getY();
		} else if(Client.getInstance().getGame().getMainCharacter().getY()>Client.getInstance().getGame().getCurrentMap().getMap_height()-330) {
			viewY=270+Client.getInstance().getGame().getMainCharacter().getY()-(Client.getInstance().getGame().getCurrentMap().getMap_height()-330);
		} else {
			viewY=270;
		}

		Input input = gameContainer.getInput();

		if(focusTextField) {
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				focusTextField = false;
				textField.setFocus(false);
				if(!textField.getText().equals("")) {
					Client.getInstance().getGame().sendChat(new ChatVO(Client.getInstance().getGame().getMainCharacter().getName(), textField.getText()));
					textField.setText("");
				}
				input.clearKeyPressedRecord();
			}
		} else {
			//인벤토리며 추가적인  창 오픈할때 쓰는 키이벤트
			if(input.isKeyPressed(Input.KEY_ENTER)) {
				focusTextField = true;
				textField.setFocus(true);
			} else if(input.isKeyPressed(Input.KEY_K)) {
				if(showSpell) { showSpell = false; }
				else { showSpell = true; }
			} else if(input.isKeyPressed(Input.KEY_I)){
				if(showInventory) { showInventory = false; }
				else { showInventory = true; }
			} else if(input.isKeyPressed(Input.KEY_ESCAPE)) {
				if(showMenu) { showMenu = false; }
				else { showMenu = true; }
			} else if(input.isKeyPressed(Input.KEY_Z)) {
				Client.getInstance().getGame().getMainCharacter().gainItem();
			} else if(input.isKeyPressed(Input.KEY_S)) {
				if(showStat) { showStat = false; }
				else { showStat = true; }
			} else if (Client.getInstance().getGame().getCurrentMap().getShopVO().getShop_x()-140
					<= Client.getInstance().getGame().getMainCharacter().getX()
					&& Client.getInstance().getGame().getCurrentMap().getShopVO().getShop_x()+140
					>= Client.getInstance().getGame().getMainCharacter().getX()
					&& Client.getInstance().getGame().getCurrentMap().getShopVO().getShop_y()-160
					<= Client.getInstance().getGame().getMainCharacter().getY()
					&& Client.getInstance().getGame().getCurrentMap().getShopVO().getShop_y()+160
					>= Client.getInstance().getGame().getMainCharacter().getY() && input.isKeyPressed(Input.KEY_SPACE)) {
				if(showShop) { showShop = false; }
				else { showShop = true; }
				if(showInventory) { showInventory = false; }
				else { showInventory = true; }
			} else if (input.isKeyPressed(Input.KEY_SPACE)) {
				input.isKeyPressed(0);
			}

			if(!Client.getInstance().getGame().getMainCharacter().isAttacking())
			{
				if(input.isKeyPressed(Input.KEY_A)){
					Client.getInstance().getGame().getMainCharacter().attack();
					Client.getInstance().getGame().notifyPosition();
				} else if(input.isKeyPressed(Input.KEY_UP)) 
					walk(1);
				else if(input.isKeyPressed(Input.KEY_DOWN)) 
					walk(3);
				else if(input.isKeyPressed(Input.KEY_LEFT)) 
					walk(4);
				else if(input.isKeyPressed(Input.KEY_RIGHT)) 
					walk(2);
			}
		}


		if(showMenu) {
			if(input.isMousePressed(0)) {
				if(326 < input.getMouseX() && 495 > input.getMouseX() && 168 < input.getMouseY() && 196 > input.getMouseY())
					arg1.enterState(3, new FadeOutTransition(), new FadeInTransition());
				else if(327 < input.getMouseX() && 495 > input.getMouseX() && 225 < input.getMouseY() && 252 > input.getMouseY())
					System.out.println("두번쨰");
				else if(327 < input.getMouseX() && 495 > input.getMouseX() && 282 < input.getMouseY() && 312 > input.getMouseY())
					System.out.println("세번쨰");
				else if(327 < input.getMouseX() && 495 > input.getMouseX() && 341 < input.getMouseY() && 368 > input.getMouseY())
					System.exit(0);
			}
		} else if(showStat) {
			if(input.isMousePressed(0)) {
				if(205 < input.getMouseX() && 215 > input.getMouseX() && 285 < input.getMouseY() && 300 > input.getMouseY())
					Client.getInstance().getGame().getMainCharacter().upStat(1);
				else if(205 < input.getMouseX() && 215 > input.getMouseX() && 305 < input.getMouseY() && 320 > input.getMouseY())
					Client.getInstance().getGame().getMainCharacter().upStat(2);
				else if(205 < input.getMouseX() && 215 > input.getMouseX() && 325 < input.getMouseY() && 340 > input.getMouseY())
					Client.getInstance().getGame().getMainCharacter().upStat(3);
				else if(205 < input.getMouseX() && 215 > input.getMouseX() && 345 < input.getMouseY() && 360 > input.getMouseY())
					Client.getInstance().getGame().getMainCharacter().upStat(4);
			}
		} else if(showInventory) {
			if(input.isMousePressed(0)) {
				if(475 < input.getMouseX() && 800 > input.getMouseX() && 85 < input.getMouseY() && 600 > input.getMouseY()) {
					int x = input.getMouseX()-475;
					int y = input.getMouseY()-85;
					x = x/49 + y/49*6;
					if(x<=48 && Client.getInstance().getGame().getMainCharacter().getItemList().size()>x)
						Client.getInstance().getGame().getMainCharacter().equipWeapon(Client.getInstance().getGame().getMainCharacter().getItemList().get(x));
				}
			}
			if (showShop) {
				if(input.isMousePressed(1)) {
					if(475 < input.getMouseX() && 800 > input.getMouseX() && 85 < input.getMouseY() && 600 > input.getMouseY()) {
						int x = input.getMouseX()-475;
						int y = input.getMouseY()-85;
						x = x/49 + y/49*6;
						if(x<=48 && Client.getInstance().getGame().getMainCharacter().getItemList().size()>x) {
							Client.getInstance().getGame().getMainCharacter().saleItem(Client.getInstance().getGame().getMainCharacter().getItemList().get(x));
						}
					}
					if(40 < input.getMouseX() && 365 > input.getMouseX() && 70 < input.getMouseY() && 585 > input.getMouseY()) {
						int x = input.getMouseX() - 40;
						int y = input.getMouseY() - 70;
						x = x/60 + y/60*5;
						if(x<=14 && Client.getInstance().getGame().getMainCharacter().getItemVOList().size()>x) {
							Client.getInstance().getGame().buyItems(Client.getInstance().getGame().getMainCharacter().getItemVOList().get(x));
						}
					}
				}
				input.isMousePressed(0);
			}
		} else { 
			if(input.isMousePressed(0)) 
				if(30 < input.getMouseX() && 79 > input.getMouseX() && 30 < input.getMouseY() && 49 > input.getMouseY())
					Client.getInstance().getGame().getMainCharacter().unEquipWeapon();
		}
	}

	private void walk(int direction) {
		if (!showShop) {
			Client.getInstance().getGame().getMainCharacter().walkingStart();
			Client.getInstance().getGame().getMainCharacter().setDirection(direction);
			Client.getInstance().getGame().notifyPosition();
		}
	}

	@Override
	public int getID() {
		return 4;
	}

	@Override
	public void keyReleased(int key, char c) {
		if(key == Input.KEY_A){

		} else if(key == Input.KEY_UP && Client.getInstance().getGame().getMainCharacter().getDirection()==1) {
			Client.getInstance().getGame().getMainCharacter().walkingStop();
		} else if(key == Input.KEY_DOWN && Client.getInstance().getGame().getMainCharacter().getDirection()==3) {
			Client.getInstance().getGame().getMainCharacter().walkingStop();
		} else if(key == Input.KEY_LEFT && Client.getInstance().getGame().getMainCharacter().getDirection()==4) {
			Client.getInstance().getGame().getMainCharacter().walkingStop();
		} else if(key == Input.KEY_RIGHT && Client.getInstance().getGame().getMainCharacter().getDirection()==2) {
			Client.getInstance().getGame().getMainCharacter().walkingStop();
		}
		Client.getInstance().getGame().notifyPosition();
	}

}
