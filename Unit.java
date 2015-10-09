import main.*;

public class Unit {
	public static void main(String[] args) {
		Database db = new MongoConnectDatabase();
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
