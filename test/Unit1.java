import java.util.*;
import main.*;

public class Unit1 {
	public static void main(String[] args) {
		Database db = new MongoConnectDatabase();
		UserManager userManager = new UserManager(db);

		//userManager.addUser(new User("test1", "test1", "1234"));
		//userManager.addUser(new User("test2", "test2", "1234"));

		List<User> users = userManager.findUser("test1");
		for(User user : users) {
			System.out.print("USER ID: " + user.getId() + ", ");
			System.out.println("USER NAME: " + user.getName());
			System.out.println("==========================");
		}

		db.close();

		/*
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
		*/
	}
}
