package mrhi.adventure.model.vo;


public class AuthenticationVO extends DataVO{
	private String email;
	private String id;
	private Integer authNumber;
	private boolean confirm;
	

	public AuthenticationVO() {}

	public AuthenticationVO(Integer authNumber, boolean confirm) {
		super();
		this.authNumber = authNumber;
		this.confirm = confirm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public Integer getAuthNumber() {
		return authNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAuthNumber(Integer authNumber) {
		this.authNumber = authNumber;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}