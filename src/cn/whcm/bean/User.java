package cn.whcm.bean;
	

/*
 * 		�����û�ʵ��
 * 		id  user  passwrod 
 * 
 */
public class User {
	private Integer id;
	private String user;
	private String password;
	public User() {
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public User(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", password=" + password + "]";
	}
	
	
}	
