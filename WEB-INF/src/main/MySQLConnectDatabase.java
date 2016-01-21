package main;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class MySQLConnectDatabase implements Database {
	private Connection connectObj = null;
	private Statement statement = null;
	private String dbConnectUrl = "";
	private String dbUsername = "";
	private String dbPassword = "";

	public MySQLConnectDatabase(final String dbConnectUrl, final String dbUsername, final String dbPassword) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.dbConnectUrl = dbConnectUrl;
			this.dbUsername = dbUsername;
			this.dbPassword = dbPassword;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connect() {
		try {
			createConnect();
			createStatement();
		} catch(Exception e) {
			this.connectObj = null;
			this.statement = null;
		}
	}

	private void createConnect() throws SQLException {
		this.connectObj = DriverManager.getConnection(this.dbConnectUrl, this.dbUsername, this.dbPassword);
	}

	private void createStatement() throws SQLException {
		this.statement = this.connectObj.createStatement();
	}

	@Override
	public void release() {
		try { 
			if(this.statement != null)
				this.statement.close();
		} catch(SQLException e) { }
		try {
			if(this.connectObj!=null)
				this.connectObj.close();
		} catch(SQLException e) { }
	}

	@Override
	public void insert(final User newData) {
		if(!connected())
			return;
		String sql = "";
		try {
			sql = "INSERT INTO `user` (user_uuid, user_id, user_password, user_name, user_in_user, user_created, user_deleted) VALUES (UUID(), '"+newData.getId()+"', '"+newData.getName()+"', password('"+newData.getPassword()+"'), NULL, NOW(), NULL)";
			statement.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(final User newData) {
		if(!connected())
			return;
		this.delete(newData);
		this.insert(newData);
	}

	@Override
	public void delete(final User oldData) {
		if(!connected())
			return;
		String sql = "";
		try {
			sql = "UPDATE `user` SET user_deleted=NOW() WHERE user_uuid='"+oldData.getUUID()+"'";
			statement.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> select(final String id) {
		if(!connected())
			return new LinkedList<User>();
		List<User> users = new LinkedList<User>();
		String sql = "";
		try {
			sql = "SELECT * FROM `user` WHERE user_id='"+id+"' AND user_deleted IS NULL";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String user_uuid  = rs.getString("user_uuid");
				String user_id = rs.getString("user_id");
				String user_password = rs.getString("user_password");
				String user_name = rs.getString("user_name");
				User user = new User(user_uuid, user_id, user_password);
				user.setName(user_name);
				users.add(user);
			}
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public String password(final String password) {
		if(!connected())
			return new String("");
		String result = "";
		String sql = "";
		try {
			sql = "SELECT PASSWORD('"+password+"') AS PASSWORD";
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			result = rs.getString("PASSWORD");
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean connected() {
		return this.connectObj != null ? true : false;
	}
}