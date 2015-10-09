package main;

public interface Authentication {
	public boolean login(final String id, final String password);
	public void logout();
	public boolean isLogined();
}