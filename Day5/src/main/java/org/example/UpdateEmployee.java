package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.combine;

import java.util.Arrays;

public class UpdateEmployee {
    public static void main(String[] args) {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> employees = db.getCollection("employees");

        // Find by email
        String email = "john@example.com";

        // Update only department and skills
        Document updateFields = new Document();
        updateFields.append("department", "Finance");
        updateFields.append("skills", Arrays.asList("Java", "Excel", "Accounting"));

        // Combine the updates using $set
        Document updateOperation = new Document("$set", updateFields);

        // Perform the update
        Document result = employees.findOneAndUpdate(eq("email", email), updateOperation);

        if (result != null) {
            System.out.println("✅ Employee updated successfully.");
        } else {
            System.out.println("❌ Employee not found with email: " + email);
        }
    }
}

