package main;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class SigninoutImpl implements Signinout {
	private UserManager userManager = null;

	private HttpSession session = null;

	public SigninoutImpl(final UserManager userManager, final HttpSession session) {
		this.userManager = userManager;
		this.session = session;
	}

	@Override
	public boolean signin(final String id, final String password) {
		boolean result = false;
		if(signed()) {
			result = true;
			return result;
		}
		final List<User> users = userManager.findUser(id);
		if(users == null || users.size() == 0) {
			result = false;
		} else {
			for(User user : users) {
				if(user.verifyId(id) && user.verifyPassword(password)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public void signout() {
		synchronized(session) {
			if( session.getAttribute("user_id") != null ) {
				session.invalidate();
			}
		}
	}

	@Override
	public boolean signed() {
		boolean result = false;
		if( session.getAttribute("user_id") != null ) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
}