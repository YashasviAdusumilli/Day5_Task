package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class AddEmployee {
    public static void main(String[] args) throws Exception {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> employees = db.getCollection("employees");

        String email = "Tarun@example.com";

        // ✅ Check if email already exists
        if (employees.find(eq("email", email)).first() != null) {
            System.out.println("❌ Email already exists!");
            return;
        }

        // 🎯 Parse joining date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date joiningDate = sdf.parse("2023-03-25");

        // ✅ Create employee document
        Document emp = new Document("name", "Tarun B")
                .append("email", email)
                .append("department", "Backend")
                .append("skills", Arrays.asList("MongoDb", "Development"))
                .append("joiningDate", joiningDate);

        employees.insertOne(emp);
        System.out.println("✅ Employee added!");
    }
}

