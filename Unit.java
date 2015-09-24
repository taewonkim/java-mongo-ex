import java.util.*;

class User {
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

class UserManager {
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


class Database {
	public Database() { }

	public void insert(final User newData) {
		// System.out.println("=== INSERT (User) ===");
		// System.out.println("  ID: " + user.getId());
		// System.out.println("NAME: " + user.getName());
	}

	public void update(final User oldData, final User newData) {
		// System.out.println("=== INSERT (User) ===");
		// System.out.println("  ID: " + user.getId());
		// System.out.println("NAME: " + user.getName());
	}

	public void delete(final User oldData) {
		// System.out.println("=== DELETE (User) ===");
		// System.out.println("  ID: " + user.getId());
		// System.out.println("NAME: " + user.getName());
	}

	public List<User> select(final String id) {
		final List<User> users = new LinkedList<User>();
		users.add(new User("1111", "1Gildong", "1111"));
		users.add(new User("2222", "2Gildong", "2222"));
		users.add(new User("3333", "3Gildong", "3333"));
		users.add(new User("4444", "4Gildong", "4444"));
		// System.out.println("=== SELECT (User) ===");
		// System.out.println("  ID: " + id);
		return users;
	}
}

interface Authentication {
	public boolean login(final String id, final String password);
	public void logout();
	public boolean isLogined();
}

class LocalAuthentication implements Authentication {
	private UserManager userManager = null;

	private User loginedUser = null;

	public LocalAuthentication(final UserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public boolean login(final String id, final String password) {
		boolean result = false;
		logout();
		final User user = userManager.findUser(id);
		if(user != null && user.verifyPassword(password)) {
			user.logined();
			loginedUser(user);
			result = true;
		}
		return result;
	}

	private void loginedUser(User user) {
		loginedUser = user;
	}

	@Override
	public void logout() {
		loginedUser = null;
	}

	@Override
	public boolean isLogined() {
		boolean result = false;
		if(loginedUser != null && loginedUser.getLogined())
			result = true;
		return result;
	}
}

public class Unit {
	public static void main(String[] args) {
		Database db = new Database();
		UserManager userManager = new UserManager(db);

		boolean result = false;

		Authentication auth = new LocalAuthentication(userManager);

		result = auth.login("1111", "1111");
		System.out.println("1111 = " + result);
		System.out.println(auth.isLogined());
		auth.logout();

		result = auth.login("1111", "2222");
		System.out.println("1111 = " + result);
		System.out.println(auth.isLogined());
		auth.logout();
	}
}