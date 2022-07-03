package chatapp;

//import java.sql.Date;

public class User {
	
	private String userName;
	private String password;
	private String sdt;
	private String email;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password, String sdt, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.sdt = sdt;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
