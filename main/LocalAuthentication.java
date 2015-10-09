package main;

public class LocalAuthentication implements Authentication {
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