package com.example.collectionanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClient;

/**
 * Controller to verify MongoDB connectivity.
 * Provides endpoints to check MongoDB connection status and perform basic operations.
 */
@RestController
@RequestMapping("/api/health")
public class MongoHealthCheckController {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test")
    public String test() {
        return "ttt";
    }

    /**
     * Checks MongoDB connection status by performing a ping command.
     * 
     * @return ResponseEntity with connection status
     */
    @GetMapping("/mongodb")
    public ResponseEntity<String> checkMongoDBConnection() {
        try {
            // Test the connection by executing a ping command
            org.bson.Document pingResult = mongoClient.getDatabase("admin").runCommand(org.bson.Document.parse("{ping: 1}"));
            boolean canConnect = pingResult.getDouble("ok") == 1.0;
            
            if (canConnect) {
                return ResponseEntity.ok("MongoDB connection is successful");
            } else {
                return ResponseEntity.status(500).body("MongoDB connection failed: Authentication issue");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("MongoDB connection failed: " + e.getMessage());
        }
    }
}