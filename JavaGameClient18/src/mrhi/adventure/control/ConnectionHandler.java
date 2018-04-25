package mrhi.adventure.control;

import java.io.IOException;
import java.util.List;

import mrhi.adventure.model.Client;
import mrhi.adventure.model.MyLog;
import mrhi.adventure.model.game.Chat;
import mrhi.adventure.model.game.GameMap;
import mrhi.adventure.model.game.JItem;
import mrhi.adventure.model.game.MainCharacter;
import mrhi.adventure.model.game.Mob;
import mrhi.adventure.model.game.OtherCharacter;
import mrhi.adventure.model.packet.MyPacket;
import mrhi.adventure.model.vo.AccountVO;
import mrhi.adventure.model.vo.AuthenticationVO;
import mrhi.adventure.model.vo.CharacterVO;
import mrhi.adventure.model.vo.ChatVO;
import mrhi.adventure.model.vo.IntegerVO;
import mrhi.adventure.model.vo.ItemVO;
import mrhi.adventure.model.vo.MapVO;
import mrhi.adventure.model.vo.MobVO;
import mrhi.adventure.model.vo.OtherCharacterVO;
import mrhi.adventure.view.ChangePWView;
import mrhi.adventure.view.FindAccountView;
import mrhi.adventure.view.GameView;

public class ConnectionHandler implements Runnable{

private ConnectionManager connectionManager;
	
	public ConnectionHandler(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public void run() {
		AccountVO avo = null;
		CharacterVO cvo = null;
		OtherCharacterVO ic = null;
		MapVO mapvo = null;
		MobVO mobvo = null;
		ItemVO itemVO = null;
		ChatVO chatVO = null;
		IntegerVO ivo = null;
		AuthenticationVO authVO = null;
		List<OtherCharacter> olist = null;
		List<Mob> mlist = null;
		List<JItem> ilist = null;
		List<Chat> clist = null;
		List<ItemVO> itemVOList = null;
		
		while(true)
		{
			MyPacket packet = null;
			try {
				packet = (MyPacket)connectionManager.receive();
			} catch (ClassNotFoundException | IOException e) {
				System.exit(0);
				e.printStackTrace();
			}
			switch(packet.getType())
			{
			case 13:
				MyLog.log("AccountVO 패킷도착!");
				avo = (AccountVO)packet.getSubObject();
				Client.getInstance().setMyAccount(avo);
				break;
				
			case 142: // 뷰랑 컨트롤러 분리
				avo = (AccountVO)packet.getSubObject();
				System.out.println("hihi");
				FindAccountView.idsList.add(avo);
				break;
				
			case 152: // 뷰랑 컨트롤러 분리
				System.out.println("비번 인증번호 받기");
				avo = (AccountVO)packet.getSubObject();
				System.out.println(avo.getAcc_id());
				ChangePWView.staticAVO = avo;
				break;
				
			case 162: // 뷰랑 컨트롤러 분리
				System.out.println("가입인증번호 받기");
				authVO = (AuthenticationVO)packet.getSubObject();
				GameView.certification = authVO.isConfirm();
				break;
				
			case 23:
				MyLog.log("CharacterVO 패킷도착!");
				cvo = (CharacterVO)packet.getSubObject();
				System.out.println(cvo);
				Client.getInstance().getGame().setMainCharacter(new MainCharacter(cvo));;
				break;
				
			case 25:
				olist = Client.getInstance().getGame().getCurrentMap().getOtherCharList();
				ic = (OtherCharacterVO)packet.getSubObject();
				for(int i=0; i<olist.size(); i++)
				{
					if(olist.get(i).getChr_idx()==ic.getChr_idx())
					{
						olist.get(i).update(ic);
					}
				}
				break;
				
			case 26:
				MyLog.log("InfoCharacter 패킷도착!");
				ic = (OtherCharacterVO)packet.getSubObject();
				if(ic.getChr_idx()!=Client.getInstance().getGame().getMainCharacter().getChrIdx())
					Client.getInstance().getGame().getCurrentMap().getOtherCharList().add(new OtherCharacter(ic));
				break;
				
			case 27:
				MyLog.log("InfoCharacter 패킷도착!");
				olist = Client.getInstance().getGame().getCurrentMap().getOtherCharList();
				ic = (OtherCharacterVO)packet.getSubObject();
				for(OtherCharacter otherChar : olist)
				{
					if(otherChar.getChr_idx()==ic.getChr_idx()) {
						olist.remove(otherChar);
						break;
					}
				}
				break;
				
			case 29:
				MyLog.log("CharacterVO 패킷도착! - 리스트에 추가하자");
				cvo = (CharacterVO)packet.getSubObject();
				if(!isContainCharVO(Client.getInstance().getGame().getCharacterList(), cvo)) {
					Client.getInstance().getGame().getCharacterList().add(cvo);
				}
				break;
				
			case 31:
				MyLog.log("MapVO 패킷도착!");
				mapvo = (MapVO)packet.getSubObject();
				GameMap gameMap = new GameMap(mapvo);
				Client.getInstance().getGame().setCurrentMap(gameMap);
				Client.getInstance().getGame().getMainCharacter().setMap(gameMap);
				break;
				
			case 41:
				MyLog.log("MobVO 누가 죽였대!");
				mlist = Client.getInstance().getGame().getCurrentMap().getMobList();
				mobvo = (MobVO)packet.getSubObject();
				for(Mob mob : mlist)
				{
					if(mob.getGen_idx()==mobvo.getGen_idx())
					{
						mlist.remove(mob);
						Client.getInstance().getGame().getMainCharacter().removeNextObserver(mob);
						break;
					}
				}
				break;
				
			case 42:
				mlist = Client.getInstance().getGame().getCurrentMap().getMobList();
				mobvo = (MobVO)packet.getSubObject();
				mlist.add(new Mob(mobvo));
				break;
				
			case 43:
				System.out.println("item DROp!");
				ilist = Client.getInstance().getGame().getCurrentMap().getItemList();
				itemVO = (ItemVO)packet.getSubObject();
				ilist.add(new JItem(itemVO));
				break;
				
			case 45:
				System.out.println("item 사라졌다");
				ilist = Client.getInstance().getGame().getCurrentMap().getItemList();
				itemVO = (ItemVO)packet.getSubObject();
				for(JItem item : ilist) {
					if(item.getGen_idx()==itemVO.getGen_idx()) {
						ilist.remove(item);
						break;
					}
				}
				break;
				
			case 47:
				System.out.println("산아이템 리스트추가");
				ilist = Client.getInstance().getGame().getMainCharacter().getItemList();
				itemVO = (ItemVO)packet.getSubObject();
				ilist.add(new JItem(itemVO));
				Client.getInstance().getConnectionManager().send(new MyPacket(64, itemVO));
				break;
				
			case 48:
				System.out.println("아이템가져옴!");
				itemVOList = Client.getInstance().getGame().getMainCharacter().getItemVOList();
				itemVO = (ItemVO)packet.getSubObject();
				itemVOList.add(itemVO);
				break;
				
			case 49:
				System.out.println("지운아이템 리스트에서 지우자");
				ilist = Client.getInstance().getGame().getMainCharacter().getItemList();
				itemVO = (ItemVO)packet.getSubObject();
				for (JItem item : ilist) {
					if (item.getOwn_idx() == itemVO.getOwn_idx()) {
						ilist.remove(item);
						break;
					}
				}
				Client.getInstance().getConnectionManager().send(new MyPacket(63, itemVO));
				break;
				
			case 51:
				clist = Client.getInstance().getGame().getCurrentMap().getChatList();
				chatVO = (ChatVO)packet.getSubObject();
				if(clist.size()>30)
					clist.remove(0);
				clist.add(new Chat(chatVO));
				break;
				
			case 61:
				ilist = Client.getInstance().getGame().getMainCharacter().getItemList();
				itemVO = (ItemVO)packet.getSubObject();
				ilist.add(new JItem(itemVO));
				System.out.println(itemVO.getItem_name());
				break;
				
			case 62:
				MyLog.log("CharacterVO 경험치 레벨!");
				cvo = (CharacterVO)packet.getSubObject();
				Client.getInstance().getGame().getMainCharacter().updateState(cvo);
				break;
				
			case 63:
				MyLog.log("돈올라라라라");
				cvo = (CharacterVO)packet.getSubObject();
				Client.getInstance().getGame().getMainCharacter().upMoney(cvo);
				System.out.println(cvo.getChr_money());
				break;
				
			case 64:
				MyLog.log("돈내려가라라라");
				cvo = (CharacterVO)packet.getSubObject();
				Client.getInstance().getGame().getMainCharacter().downMoney(cvo);
				System.out.println(cvo.getChr_money());
				break;
				
			case 71:
				ivo = (IntegerVO)packet.getSubObject();
				Client.getInstance().getGame().getMainCharacter().minusHp(ivo.getValue());
				break;
				
			case 72:
				Client.getInstance().getGame().getMainCharacter().setHp(0);
				Client.getInstance().getGame().getMainCharacter().setDead(true);
				break;
				
			case 73:
				MyLog.log("누가 뒤졌대");
				olist = Client.getInstance().getGame().getCurrentMap().getOtherCharList();
				ic = (OtherCharacterVO)packet.getSubObject();
				for(OtherCharacter otherChar : olist) {
					if(otherChar.getChr_idx()==ic.getChr_idx()) {
						otherChar.setDead(true);
						break;
					}
				}
				break;
				
			case 81:
				MyLog.log("스탯올린상태 왔다");
				cvo = (CharacterVO)packet.getSubObject();
				Client.getInstance().getGame().getMainCharacter().updateStat(cvo);
			}
		}
	}
	
	public boolean isContainCharVO(List<CharacterVO> charVOList, CharacterVO charVO) {
		for(CharacterVO cvo : charVOList) {
			if(cvo.getChr_idx()==charVO.getChr_idx())
				return true;
		}
		return false;
	}
}
