package mrhi.adventure.model.packet;

import java.io.Serializable;

import mrhi.adventure.model.vo.DataVO;


public class MyPacket implements Serializable{

	protected int type;
	protected DataVO subObject;
	
	public MyPacket(int type, DataVO subObject) {
		super();
		this.type = type;
		this.subObject = subObject;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public DataVO getSubObject() {
		return subObject;
	}
	public void setSubObject(DataVO subObject) {
		this.subObject = subObject;
	}
}



