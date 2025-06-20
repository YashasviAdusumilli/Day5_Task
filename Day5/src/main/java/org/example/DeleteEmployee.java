package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class DeleteEmployee {
    public static void main(String[] args) {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> employees = db.getCollection("employees");

        // Option 1: Delete by email
        String emailToDelete = "john@example.com";
        Document deletedByEmail = employees.findOneAndDelete(eq("email", emailToDelete));

        if (deletedByEmail != null) {
            System.out.println("✅ Employee deleted by email: " + emailToDelete);
        } else {
            System.out.println("❌ No employee found with email: " + emailToDelete);
        }

        // ------------------------------
        // Option 2: Delete by _id
        // ------------------------------
        String idToDelete = "INSERT_OBJECT_ID_HERE";  // Example: "665f84e62f6ab9e2457f7e02"

        // Uncomment below to delete by _id
        /*
        ObjectId objId = new ObjectId(idToDelete);
        Document deletedById = employees.findOneAndDelete(eq("_id", objId));

        if (deletedById != null) {
            System.out.println("✅ Employee deleted by _id: " + idToDelete);
        } else {
            System.out.println("❌ No employee found with _id: " + idToDelete);
        }
        */
    }
}

