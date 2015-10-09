package main;

import java.util.*;

public class InMemoryDatabase implements Database {
	final List<User> users = new LinkedList<User>();

	public InMemoryDatabase() { }

	public void insert(final User newData) {
		this.users.add(newData);
	}

	public void update(final User oldData, final User newData) {
		for(int i = 0; i < users.size(); i++) {
			if(this.users.get(i) == oldData) {
				// 찾은 유저 리스트에 할당...
			}
		}
	}

	public void delete(final User oldData) {
		this.users.remove(oldData);
	}

	public List<User> select(final String id) {
		users.add(new User("1111", "1Gildong", "1111"));
		users.add(new User("2222", "2Gildong", "2222"));
		users.add(new User("3333", "3Gildong", "3333"));
		users.add(new User("4444", "4Gildong", "4444"));
		// System.out.println("=== SELECT (User) ===");
		// System.out.println("  ID: " + id);
		return users;
	}
}