package covoiturage.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class TarifsDao {

	private MongoClient mongoClient;
	private MongoDatabase database;

	public TarifsDao() {
		mongoClient = MongoClientFactory.getClient();
		database = mongoClient.getDatabase("covoiturage");
	}

	public void insert(Tarification tarif) {
		Document doc = new Document();
		doc.append("name", tarif.getName());
		doc.append("amount", tarif.getAmount());
		database.getCollection("tarifs").insertOne(doc);
	}

	public long delete(String name) {
		DeleteResult res = database.getCollection("tarifs").deleteMany(Filters.eq("name", name));
		return res.getDeletedCount();
	}

	public List<Tarification> getTarifs() {
		MongoCollection<Document> tarifs = database.getCollection("tarifs");
		List<Tarification> results = buildTarifications(tarifs);
		return results;
	}

	private List<Tarification> buildTarifications(MongoCollection<Document> tarifs) {
		FindIterable<Document> it = tarifs.find();
		List<Tarification> results = new ArrayList<>();
		for (Document document : it) {
			results.add(new Tarification(document.getString("name"), document.getDouble("amount")));
		}
		return results;
	}

	public void close() {
		mongoClient.close();
	}

	public void drop() {
		database.getCollection("tarifs").drop();
	}
}
