package main;

import java.util.*;
import main.*;

public interface Database {
	public void connect();
	public boolean connected();
	public void release();
	public void insert(final User newData);
	public void update(final User newData);
	public void delete(final User oldData);
	public List<User> select(final String id);
	public String password(final String password);
}