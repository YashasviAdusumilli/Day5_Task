package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;

public class SearchEmployees {
    public static void main(String[] args) throws Exception {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> employees = db.getCollection("employees");

        // ----------------------------------------
        // 1. 🔍 Search by Name (contains "john")
        // ----------------------------------------
        System.out.println("\n🔤 Search: Name contains 'john'");
        Pattern namePattern = Pattern.compile("john", Pattern.CASE_INSENSITIVE);
        printResults(employees.find(regex("name", namePattern)));

        // ----------------------------------------
        // 2. 🏢 Search by Department = "HR"
        // ----------------------------------------
        System.out.println("\n🏢 Search: Department is 'HR'");
        printResults(employees.find(eq("department", "HR")));

        // ----------------------------------------
        // 3. 🛠️ Search by Skill = "Java"
        // ----------------------------------------
        System.out.println("\n🛠️ Search: Has skill 'Java'");
        printResults(employees.find(elemMatch("skills", eq("Java"))));

        // ----------------------------------------
        // 4. 📅 Search by Joining Date Range (2023)
        // ----------------------------------------
        System.out.println("\n📅 Search: Joined in 2023");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2023-01-01");
        Date end = sdf.parse("2023-12-31");

        printResults(employees.find(and(gte("joiningDate", start), lte("joiningDate", end))));
    }

    // Helper method to print results
    private static void printResults(FindIterable<Document> docs) {
        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }
    }
}
