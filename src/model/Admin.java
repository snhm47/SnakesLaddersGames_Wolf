package model;

public class Admin {
	
	private String userName;
	private String pass;
	
	public Admin(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Admin [userName=" + userName + ", pass=" + pass + "]";
	}
	
}
