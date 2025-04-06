package com.example.dbstats.controller;

import com.example.common.model.DatabaseStats;
import com.example.dbstats.service.DatabaseStatsService;
// Removed lombok import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/stats")
public class DatabaseStatsController {

    private final DatabaseStatsService databaseStatsService;

    

    public DatabaseStatsController(DatabaseStatsService databaseStatsService) {
        this.databaseStatsService = databaseStatsService;
    }

    @GetMapping("/databases")
    public ResponseEntity<List<String>> getAllDatabases() {
        return ResponseEntity.ok(databaseStatsService.getAllDatabases());
    }

    @GetMapping("/collections")
    public ResponseEntity<Set<String>> getCollections(@RequestParam(required = false) String database) {
        return ResponseEntity.ok(databaseStatsService.getCollections(database));
    }
}