package vip.fairy;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

//http://mongodb.github.io/mongo-java-driver/

public class Mongos {

  private static final MongoClient mongoClient;

  static {
    mongoClient = MongoClients.create("mongodb://localhost:27017");
  }

  public static void main(String[] args) {
    MongoInsert();
  }

  static void MongoQuery() {
    try {
      MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
      // mongoDatabase.createCollection("test");
      MongoCollection<Document> collection = mongoDatabase.getCollection("fbilion");
      FindIterable<Document> findIterable = collection.find();
      for (Document document : findIterable) {
        System.out.println(document);
      }
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  static void MongoInsert() {
    try {
      MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
      MongoCollection<Document> collection = mongoDatabase.getCollection("fbilion");
      Document document = new Document();
      document.append("name", "xiaoxuexue");
      document.append("age", 100001);
      List<Document> documents = new ArrayList<>();
      documents.add(document);
      collection.insertMany(documents);
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  static void MongoUpdate() {
    try {
      MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
      MongoCollection<Document> collection = mongoDatabase.getCollection("fbilion");

      BasicDBObject queryObect = new BasicDBObject("_id", new ObjectId("5a7aaff2ad0aab2b1016659e"));
      collection.updateMany(Filters.eq(queryObect), new Document("$set", new Document("name", "xiaomengx")));

      collection.updateMany(Filters.eq("_id", new ObjectId("5a7aaff2ad0aab2b1016659e")),
          new Document("$set", new Document("name", "xiaomengx")));
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

  }

  static void MongoDelete() {
    try {
      MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
      MongoCollection<Document> collection = mongoDatabase.getCollection("fbilion");

      // collection.deleteMany(Filters.eq("_id", new
      // ObjectId("5a7aafe2ad0aab086ceee708")));

      // collection.deleteMany(Filters.gt("age", 90000));

      DeleteResult deleteOne = collection.deleteOne(Filters.gt("age", 89980));
      System.out.println(collection.countDocuments() + "  " + deleteOne.getDeletedCount());
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }

  }

  public static void FindByObjectDemo() {
    try {
      // **********************Method1**********************
      DBObject filter1 = new BasicDBObject();
      filter1.put("_id", new ObjectId("5a79a2814ac5429c64d12e91"));
      // **********************Method2**********************
      Document filter2 = new Document();
      filter2.append("_id", new ObjectId("5a79a2814ac5429c64d12e91"));
      // **********************Method3**********************
      Bson filter3 = Filters.and(Filters.eq("_id", new ObjectId("5a79a2814ac5429c64d12e91")));

      MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
      MongoCollection<Document> collection = mongoDatabase.getCollection("fbilion");
      List<Document> results = new ArrayList<Document>();
      FindIterable<Document> iterables = collection.find((Bson) filter3);
      MongoCursor<Document> cursor = iterables.iterator();

      while (cursor.hasNext()) {
        results.add(cursor.next());
      }

      for (Document doc : results) {
        System.out.println("鏂规硶1锛�" + doc.toJson());
      }
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

}
