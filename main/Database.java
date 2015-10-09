package main;

import java.util.*;

public interface Database {
	public void insert(final User newData);
	public void update(final User oldData, final User newData);
	public void delete(final User oldData);
	public List<User> select(final String id);
}