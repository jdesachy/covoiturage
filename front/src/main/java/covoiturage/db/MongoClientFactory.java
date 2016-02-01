package covoiturage.db;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import covoiturage.Configuration;

public class MongoClientFactory {

	public static MongoClient getClient() {
		Configuration configuration = new Configuration();
		ServerAddress addr = new ServerAddress(configuration.getString("database.host"),
				configuration.getInt("database.port"));
		String user = configuration.getString("database.user");
		MongoClient mongoClient;
		if (isValued(user)) {
			char[] password = configuration.getString("database.password").toCharArray();
			MongoCredential credential = MongoCredential.createCredential(user, "covoiturage", password);
			mongoClient = new MongoClient(addr, Arrays.asList(credential));
		} else {
			mongoClient = new MongoClient(addr);
		}
		return mongoClient;
	}

	private static boolean isValued(String user) {
		return user != null && !"".equals(user.trim());
	}
}
