package main;

import java.util.*;

public class UserManager {
	private Database db;
	
	public UserManager(final Database db) {
		this.db = db;
	}

	public void addUser(final User user) {
		if(user != null)
			db.insert(user);
	}

	public void delUser(final User user) {
		if(user != null)
			db.delete(user);
	}

	public User findUser(final String id) {
		final List<User> users = db.select(id);
		for(User user : users) {
			if(user.getId().compareTo(id) == 0) {
				return user;
			}
		}
		return null;
	}
}