package main;

public class User {
	private String id = "";

	private String name = "";

	private String password = "";

	private boolean logined = false;

	public User(final String id, final String name, final String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public boolean getLogined() {
		return this.logined;
	}

	public boolean verifyPassword(final String password) {
		return verify(this.password, password);
	}

	private boolean verify(final String src, final String tgt) {
		return (src.compareTo(tgt) == 0) ? true : false;
	}

	public void logined() {
		if(this.logined == false) {
			this.logined = true;
		}
	}

	public void unLogined() {
		if(this.logined == true) {
			this.logined = false;
		}
	}
}