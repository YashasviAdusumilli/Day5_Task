package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class PaginateEmployees {
    public static void main(String[] args) {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> employees = db.getCollection("employees");

        int pageSize = 5;     // Number of employees per page
        int pageNumber = 1;   // Page number to fetch (1-based)

        // Sort by name (ascending) or joining date (descending) — choose one
        Document sortBy = new Document("name", 1); // 1 = ASC, -1 = DESC

        // Pagination logic
        int skip = (pageNumber - 1) * pageSize;

        // Fetch paginated and sorted results
        FindIterable<Document> results = employees.find()
                .sort(sortBy)
                .skip(skip)
                .limit(pageSize);

        // Print results
        System.out.println("📑 Page " + pageNumber + " (sorted by name):");
        for (Document doc : results) {
            System.out.println(doc.toJson());
        }
    }
}
