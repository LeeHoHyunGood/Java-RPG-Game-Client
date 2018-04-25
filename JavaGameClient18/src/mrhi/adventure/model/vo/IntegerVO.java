package mrhi.adventure.model.vo;

public class IntegerVO extends DataVO {
	
	int value;

	public IntegerVO(int damage) {
		super();
		this.value = damage;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
