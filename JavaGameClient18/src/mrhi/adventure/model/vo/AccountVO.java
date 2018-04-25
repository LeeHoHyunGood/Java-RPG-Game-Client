package mrhi.adventure.model.vo;

public class AccountVO extends DataVO{
	private int acc_idx;
	private String acc_id;
	private String acc_pw;
	private String acc_name;
	private String acc_email;
	private String acc_hp;
	
	public AccountVO(int acc_idx, String acc_id, String acc_pw, String acc_name, String acc_email, String acc_hp) {
		super();
		this.acc_idx = acc_idx;
		this.acc_id = acc_id;
		this.acc_pw = acc_pw;
		this.acc_name = acc_name;
		this.acc_email = acc_email;
		this.acc_hp = acc_hp;
	}
	
	public AccountVO() {
		super();
	}

	public int getAcc_idx() {
		return acc_idx;
	}

	public void setAcc_idx(int acc_idx) {
		this.acc_idx = acc_idx;
	}

	public String getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}

	public String getAcc_pw() {
		return acc_pw;
	}

	public void setAcc_pw(String acc_pw) {
		this.acc_pw = acc_pw;
	}

	public String getAcc_name() {
		return acc_name;
	}

	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}

	public String getAcc_email() {
		return acc_email;
	}

	public void setAcc_email(String acc_email) {
		this.acc_email = acc_email;
	}

	public String getAcc_hp() {
		return acc_hp;
	}

	public void setAcc_hp(String acc_hp) {
		this.acc_hp = acc_hp;
	}

	@Override
	public String toString() {
		return "AccountVO [acc_idx=" + acc_idx + ", acc_id=" + acc_id + ", acc_pw=" + acc_pw + ", acc_name=" + acc_name
				+ ", acc_email=" + acc_email + ", acc_hp=" + acc_hp + "]";
	}
	
}
