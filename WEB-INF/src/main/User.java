package main;

public class User {
	private String uuid = "";

	private String id = "";

	private String name = "";

	private String password = "";

	private User user_in_user = null;

	// public User(final String uuid, final String id, final String name, final String password) {
	public User(final String uuid, final String id, final String password) {
		this.uuid = uuid;
		this.id = id;
		this.password = password;
	}

	public String getUUID() {
		return this.uuid;
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public User getUserInUser() {
		return this.user_in_user;
	}

	public void setUserInUser(final User user_in_user) {
		this.user_in_user = user_in_user;
	}

	public boolean verifyId(final String id) {
		return verify(this.id, id);
	}

	public boolean verifyPassword(final String password) {
		return verify(this.password, password);
	}

	private boolean verify(final String src, final String tgt) {
		return src.equals(tgt) ? true : false;
	}
}