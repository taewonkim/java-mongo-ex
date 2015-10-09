package main;

import java.util.*;
import org.bson.*;
import com.mongodb.*;
import com.mongodb.client.*;

public class MongoConnectDatabase implements Database {
	private MongoClient mongoClient;
	private MongoDatabase db;
	private List<User> users = new LinkedList<User>();

	public MongoConnectDatabase() {
		 this.mongoClient = new MongoClient("192.168.8.81");
		 this.db = mongoClient.getDatabase("test");
	}

	@Override
	public void insert(final User newData) {
		db.getCollection("Authentication")
			.insertOne(
        		new Document("users",
                	new Document()
                        .append("ID", "test")
                        .append("PW", "1234")));
	}

	@Override
	public void update(final User oldData, final User newData) {
	}

	@Override
	public void delete(final User oldData) {
	}

	@Override
	public List<User> select(final String id) {
		users.add(new User("1111", "1Gildong", "1111"));
		users.add(new User("2222", "2Gildong", "2222"));
		users.add(new User("3333", "3Gildong", "3333"));
		users.add(new User("4444", "4Gildong", "4444"));
		return users;
	}
}