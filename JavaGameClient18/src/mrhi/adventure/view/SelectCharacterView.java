package mrhi.adventure.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.vo.CharacterVO;
import mrhi.adventure.model.vo.MapVO;

public class SelectCharacterView extends BasicGameState {
	private Image backimg;
	public static Image warrior;
	public static Image knight;
	public static Image archer;
	public static Image magician;
	public static Image deletebox;
	private Image box;
	private Image selectedbox;
	private int select;
	private boolean deleteCharacter = false;
	
	public SelectCharacterView(int state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame arg1) throws SlickException {
		backimg = new Image("image/selectView/selectcharback.png");
		warrior = new Image("image/selectView/warrior.png");
		knight = new Image("image/selectView/knight.png");
		archer = new Image("image/selectView/archer.png");
		magician = new Image("image/selectView/magician.png");
		deletebox = new Image("image/selectView/delete.png");
		box = new Image("image/selectView/selectcharbox.png");;
		selectedbox = new Image("image/selectView/selectcharshade.png");;
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame arg1, Graphics g) throws SlickException {
		backimg.draw();
		
		for(int i=0; i<3; i++) {
			if(i==select) {
				selectedbox.draw(90 + i*240, 100);
			} else {
				box.draw(90 + i*240, 100);
			}
		}
		
		g.setColor(Color.darkGray);
		for(int i=0; i<Client.getInstance().getGame().getCharacterList().size(); i++) {
			CharacterVO charVO = Client.getInstance().getGame().getCharacterList().get(i);
			if(charVO.getChr_job()==1) {
				warrior.draw(90 + i*240, 100);
			} else if(charVO.getChr_job()==2){
				knight.draw(90 + i*240, 100);
			} else if(charVO.getChr_job()==3){
				archer.draw(90 + i*240, 100);
			} else if(charVO.getChr_job()==4){
				magician.draw(90 + i*240, 100);
			}
			g.drawString("NAME  : " + charVO.getChr_name(), 100 + i*240, 350);
			g.drawString("LEVEL : " + charVO.getChr_level(), 100 + i*240, 370);
			g.drawString("Job   : " + charVO.getChr_job(), 100 + i*240, 390);
		}
		
		if(deleteCharacter) {
			deletebox.draw(250, 150);
			g.setColor(Color.red);
			g.drawString(Client.getInstance().getGame().getCharacterList().get(select).getChr_name(), 350, 180);
		}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = gameContainer.getInput();
		
		if(input.isMousePressed(0)) {
			if(deleteCharacter) {
				if(340 < input.getMouseX() && 380 > input.getMouseX() && 247 < input.getMouseY() && 270 > input.getMouseY()) {
					CharacterVO charVO = Client.getInstance().getGame().getCharacterList().get(select);
					Client.getInstance().getGame().deleteCharacter(charVO);
					Client.getInstance().getGame().getCharacterList().remove(charVO);
					System.out.println(select + " 삭제!");
					deleteCharacter=false;
				} else if(457 < input.getMouseX() && 500 > input.getMouseX() && 243 < input.getMouseY() && 271 > input.getMouseY()) {
					System.out.println("추ㅣ소");
					deleteCharacter=false;
				}
			} else if(90 < input.getMouseX() && 260 > input.getMouseX() && 90 < input.getMouseY() && 440 > input.getMouseY()) { 
				select = 0;
			} else if(330 < input.getMouseX() && 500 > input.getMouseX() && 90 < input.getMouseY() && 440 > input.getMouseY()) { 
				select = 1;
			} else if(575 < input.getMouseX() && 740 > input.getMouseX() && 90 < input.getMouseY() && 440 > input.getMouseY()) { 
				select = 2;
			} else if(172 < input.getMouseX() && 397 > input.getMouseX() && 490 < input.getMouseY() && 535 > input.getMouseY()) { 
				arg1.enterState(2, new FadeOutTransition(), new FadeInTransition());
			} else if(498 < input.getMouseX() && 597 > input.getMouseX() && 486 < input.getMouseY() && 541 > input.getMouseY()) {
				CharacterVO charVO = new CharacterVO();
				charVO.setChr_idx(Client.getInstance().getGame().getCharacterList().get(select).getChr_idx());
				Client.getInstance().requestCharacter(charVO);
				
				MapVO mapVO= new MapVO();
				mapVO.setMap_idx(1000);
				
				Client.getInstance().getGame().requestMap(mapVO);
				Client.getInstance().getGame().requestItemList();
				arg1.enterState(4, new FadeOutTransition(), new FadeInTransition());
				Client.getInstance().getGame().getItem();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Client.getInstance().getGame().startTimeElapse();
			}
			else if(663 < input.getMouseX() && 716 > input.getMouseX() && 10 < input.getMouseY() && 35 > input.getMouseY()) {
				Client.getInstance().setMyAccount(null);
				arg1.enterState(0, new FadeOutTransition(), new FadeInTransition());
			} else if((530 < input.getMouseX() && 650 > input.getMouseX() && 10 < input.getMouseY() && 35 > input.getMouseY())){
				deleteCharacter=true;
			}
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
