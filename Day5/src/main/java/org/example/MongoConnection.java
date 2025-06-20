package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    public static MongoDatabase connect() {
        String uri = "mongodb://localhost:27017"; // Default MongoDB URI
        MongoClient client = MongoClients.create(uri);
        return client.getDatabase("employeeDB"); // You can name your DB anything
    }
}
