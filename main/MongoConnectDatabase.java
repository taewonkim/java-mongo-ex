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
	public void insert(final User newData) {
		BasicDBObject doc = new BasicDBObject("name", "users")
						.append("U_ID", newData.getId())
                        .append("U_PW", newData.getPassword())
		                .append("U_NAME", newData.getName());
		dbCollection.insert(doc);
	}

	@Override
	public void update(final String filterId, final User newData) {
		// User.Name = get("Name")
		//if(newData.get("id").equals(uId)) {
			// processing...
		//}
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
				String uId = dbObject.get("U_ID").toString();
				String uName = dbObject.get("U_PW").toString();
				String uPw = dbObject.get("U_NAME").toString();
				users.add(new User(uId, uName, uPw));
			}
		} finally {
			cursor.close();
		}
		return users;
	}
}