package mrhi.adventure.model.game;

import mrhi.adventure.model.vo.ChatVO;

public class Chat {
	private String text;
	private String name;
	private int chr_idx;
	private long time;
	
	public Chat(ChatVO chatVO) {
		this.text = chatVO.getText();
		this.chr_idx = chatVO.getChr_idx();
		this.name = chatVO.getName();
		this.time = System.currentTimeMillis();
	}
	
	public Chat(String text, String name, int chr_idx) {
		super();
		this.text = text;
		this.name = name;
		this.chr_idx = chr_idx;
		this.time = System.currentTimeMillis();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getChr_idx() {
		return chr_idx;
	}
	public void setChr_idx(int chr_idx) {
		this.chr_idx = chr_idx;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
