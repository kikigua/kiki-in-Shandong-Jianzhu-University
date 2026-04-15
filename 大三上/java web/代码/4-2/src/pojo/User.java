package pojo;

public class User {
	private int userid;
	private String username;
	private String password;
	private String sex;
	private int age;
	private String birthday;
	
	public User(int userid, String username, String password, String sex,
			int age, String birthday) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
	}
	public User(int userid, String username) {
		super();
		this.userid = userid;
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getBirthday() {
		return birthday;
	}
	
}
