package mrhi.adventure.model.vo;

public class ChatVO extends DataVO{
	private int chr_idx;
	private String name;
	private String text;

	public ChatVO() {
		super();
	}
	
	public ChatVO(int chr_idx, String name, String text) {
		super();
		this.chr_idx = chr_idx;
		this.name = name;
		this.text = text;
	}

	public ChatVO(String name, String text) {
		super();
		this.name = name;
		this.text = text;
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
	
}
