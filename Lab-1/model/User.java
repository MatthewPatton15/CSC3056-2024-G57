package model;

public class User {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private String mobile_number;
	
	public User(String user, String pass, String first, String last, String mobile) {
		this.username = user;
		this.password = pass;
		this.first_name = first;
		this.last_name = last;
		this.mobile_number = mobile;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getFirst_name() {
		return this.first_name;
	}
	
	public void setFirst_name(String first) {
		this.first_name = first;
	}
	
	public String getLast_name() {
		return this.last_name;
	}
	
	public void setLast_name(String last) {
		this.last_name = last;
	}
	
	public String getMobile_number() {
		return this.mobile_number;
	}
	
	public void setMobile_number(String mobile) {
		this.mobile_number = mobile;
	}
	
	@Override
	public String toString() {
		return String.format("%-10s| %-20s| %-10s| %-15s| %-15s",
				this.username,
				this.password,
				this.first_name,
				this.last_name,
				this.mobile_number);
	}
}
