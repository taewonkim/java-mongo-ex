package main;

import java.util.*;
import org.bson.*;
import com.mongodb.*;
import com.mongodb.client.*;

public class MongoConnectDatabase implements Database {
	private MongoClient mongoClient;
	//private MongoDatabase db;
	private DB db;
	private DBCollection dbCollection;

	public MongoConnectDatabase() {
		 this.mongoClient = new MongoClient("192.168.1.108");
		 //this.db = this.mongoClient.getDatabase("test");
		 this.db = this.mongoClient.getDB("test");
		 this.dbCollection = this.db.getCollection("Authentication");
	}

	@Override
	public void connect() {
	}

	@Override
	public void release() {
	}

	@Override
	public boolean connected() {
		return false;
	}

	@Override
	public String password(final String password) {
		return password;
	}

	@Override
	public void insert(final User newData) {
		BasicDBObject doc = new BasicDBObject("name", "users")
						.append("user_uuid", newData.getUUID())
						.append("user_id", newData.getId())
                        .append("user_password", newData.getPassword())
		                .append("user_name", newData.getName());
		dbCollection.insert(doc);
	}

	@Override
	public void update(final User newData) {
		
	}

	@Override
	public void delete(final User oldData) {
	}

	@Override
	public List<User> select(final String id) {
		List<User> users = new LinkedList<User>();
		//BasicDBObject filter = new BasicDBObject("U_ID", id);
		BasicDBObject filter = new BasicDBObject();
		DBCursor cursor = dbCollection.find(filter);
		try {
			while(cursor.hasNext()) {
				DBObject dbObject = cursor.next();
				String user_uuid = dbObject.get("uuid").toString();
				String user_id = dbObject.get("user_id").toString();
				String user_password = dbObject.get("user_password").toString();
				String user_name = dbObject.get("user_name").toString();
				User user = new User(user_uuid, user_id, user_password);
				user.setName(user_name);
				users.add(user);
			}
		} finally {
			cursor.close();
		}
		return users;
	}
}