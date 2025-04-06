package com.example.dbstats.service;

import com.example.common.model.DatabaseStats;
// Removed lombok imports
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
// Removed @Slf4j annotation
public class DatabaseStatsService {

    private static final Logger log = LoggerFactory.getLogger(DatabaseStatsService.class);
    private final MongoTemplate mongoTemplate;

    

    public DatabaseStatsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<String> getAllDatabases() {
        Document result = mongoTemplate.executeCommand(new Document("listDatabases", 1));
        List<Document> databases = result.getList("databases", Document.class);
        
        return databases.stream()
                .map(db -> db.getString("name"))
                .collect(Collectors.toList());
    }

    public Set<String> getCollections(String database) {
        return mongoTemplate.getCollectionNames();
    }
}