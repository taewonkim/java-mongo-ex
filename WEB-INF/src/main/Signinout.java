package main;

public interface Signinout {
	public boolean signin(final String id, final String password);
	public void signout();
	public boolean signed();
}