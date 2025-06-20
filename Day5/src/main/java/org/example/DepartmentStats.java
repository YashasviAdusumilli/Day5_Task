package org.example;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class DepartmentStats {
    public static void main(String[] args) {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> employees = db.getCollection("employees");

        // 🎯 MongoDB aggregation pipeline
        AggregateIterable<Document> result = employees.aggregate(Arrays.asList(
                new Document("$group", new Document("_id", "$department")
                        .append("count", new Document("$sum", 1))),
                new Document("$sort", new Document("count", -1)) // Optional: Sort by count DESC
        ));

        System.out.println("📊 Employees per Department:");
        for (Document doc : result) {
            System.out.println("Department: " + doc.getString("_id") + " | Count: " + doc.getInteger("count"));
        }
    }
}
