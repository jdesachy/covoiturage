package covoiturage.db;

import java.util.Calendar;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import covoiturage.TarifName;
import covoiturage.calendrier.Jour;

public class DaysDao {

	private MongoClient mongoClient;
	private MongoDatabase database;

	public DaysDao() {
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase("covoiturage");
	}

	public void insert(Jour jour) {
		Document doc = new Document().append("name", buildId(jour.getDay())).append("matin", jour.getAller().name())
				.append("soir", jour.getRetour().name());
		database.getCollection("days").insertOne(doc);
	}

	public Jour find(Calendar cal) {
		MongoCollection<Document> daysCollection = database.getCollection("days");
		String id = buildId(cal);
		FindIterable<Document> doc = daysCollection.find(com.mongodb.client.model.Filters.eq("name", id));

		Document first = doc.first();
		Jour day = initDay(cal, first);
		if (first != null) {
			day.setAller(TarifName.valueOf(first.getString("matin")));
			day.setRetour(TarifName.valueOf(first.getString("soir")));
		} else {
			day.setAller(TarifName.AUCUN);
			day.setRetour(TarifName.AUCUN);
		}
		return day;
	}

	public long update(Jour jour) {
		MongoCollection<Document> daysCollection = database.getCollection("days");
		String id = buildId(jour.getDay());
		Document document = new Document("matin", jour.getAller().name());
		UpdateResult res = daysCollection.updateOne(com.mongodb.client.model.Filters.eq("name", id),
				new Document("$set", document));

		long modifiedCount = res.getModifiedCount();
		if (modifiedCount == 0) {
			insert(jour);
		}
		return modifiedCount;
	}

	private Jour initDay(Calendar cal, Document first) {
		Jour day = new Jour();
		day.setDay(cal);
		day.setAller(TarifName.AUCUN);
		day.setRetour(TarifName.AUCUN);
		return day;
	}

	private String buildId(Calendar day) {
		StringBuilder id = new StringBuilder();
		id.append(day.get(Calendar.DAY_OF_MONTH));
		id.append("_");
		id.append(day.get(Calendar.MONTH));
		id.append("_");
		id.append(day.get(Calendar.YEAR));
		return id.toString();
	}

	public void close() {
		mongoClient.close();
	}
}
