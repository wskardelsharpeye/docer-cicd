package com.example.collectionanalysis.service;

import com.example.common.model.CollectionStats;
import com.example.common.model.ScheduleInfo;
// Removed lombok imports
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
// Removed @RequiredArgsConstructor and @Slf4j annotations
public class CollectionAnalysisService {

    private static final Logger log = LoggerFactory.getLogger(CollectionAnalysisService.class);
    
    private final MongoTemplate mongoTemplate;
    
    // Mock schedule data - in a real application, this would come from a database
    private final Map<String, ScheduleInfo> scheduleInfoMap = new ConcurrentHashMap<>();
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Added explicit constructor
    public CollectionAnalysisService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public CollectionStats getCollectionStats(String collectionName) {
        Document statsCommand = new Document("collStats", collectionName);
        Document stats;
        
        try {
            stats = mongoTemplate.executeCommand(statsCommand);
        } catch (Exception e) {
            log.error("Error getting stats for collection: {}", collectionName, e);
            return new CollectionStats(collectionName, 0, 0, "0", "0", false);
        }
        
        CollectionStats collectionStats = new CollectionStats();
        collectionStats.setCollectionName(collectionName);
        collectionStats.setDocumentCount(stats.getInteger("count", 0));
        collectionStats.setSizeInBytes(stats.getInteger("size", 0));
        collectionStats.setAvgObjectSize(stats.get("avgObjSize", "0").toString());
        collectionStats.setStorageSize(stats.get("storageSize", "0").toString());
        collectionStats.setCapped(stats.getBoolean("capped", false));
        
        return collectionStats;
    }

    public ScheduleInfo getCollectionSchedule(String collectionName) {
        // In a real application, this would fetch from a database
        // For demo purposes, we'll create mock data if it doesn't exist
        if (!scheduleInfoMap.containsKey(collectionName)) {
            LocalDateTime now = LocalDateTime.now();
            ScheduleInfo scheduleInfo = new ScheduleInfo(
                collectionName,
                "0 0/30 * * * ?", // Every 30 minutes
                true,
                now.minusHours(1).format(DATE_FORMATTER),
                now.plusHours(1).format(DATE_FORMATTER)
            );
            scheduleInfoMap.put(collectionName, scheduleInfo);
        }
        
        return scheduleInfoMap.get(collectionName);
    }

    public List<Document> getRecentDocuments(String collectionName, int limit) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            return new ArrayList<>();
        }
        
        Query query = new Query();
        query.limit(limit);
        query.with(Sort.by(Sort.Direction.DESC, "_id")); // Assuming _id has a timestamp component
        
        return mongoTemplate.find(query, Document.class, collectionName);
    }
}