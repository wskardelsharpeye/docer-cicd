package com.example.collectionanalysis.controller;

import com.example.collectionanalysis.service.CollectionAnalysisService;
import com.example.common.model.CollectionStats;
import com.example.common.model.ScheduleInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collection")
@Tag(name = "Collection Analysis", description = "APIs for analyzing MongoDB collections")
public class CollectionAnalysisController {

    private final CollectionAnalysisService collectionAnalysisService;
    private static final int DEFAULT_RECENT_LIMIT = 5;
    
    public CollectionAnalysisController(CollectionAnalysisService collectionAnalysisService) {
        this.collectionAnalysisService = collectionAnalysisService;
    }

    @Operation(summary = "Get collection statistics", description = "Retrieves statistical information about a MongoDB collection")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved collection stats",
            content = @Content(schema = @Schema(implementation = CollectionStats.class))),
        @ApiResponse(responseCode = "400", description = "Invalid collection name provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    @GetMapping("/{collectionName}/stats")
    public ResponseEntity<CollectionStats> getCollectionStats(
            @Parameter(description = "Name of the MongoDB collection", required = true)
            @PathVariable String collectionName) {
        try {
            if (collectionName == null || collectionName.trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection name cannot be empty");
            }
            return ResponseEntity.ok(collectionAnalysisService.getCollectionStats(collectionName));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving collection stats");
        }
    }

    @Operation(summary = "Get collection schedule information", description = "Retrieves scheduling information for a MongoDB collection")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved schedule information",
            content = @Content(schema = @Schema(implementation = ScheduleInfo.class))),
        @ApiResponse(responseCode = "400", description = "Invalid collection name provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    @GetMapping("/{collectionName}/schedule")
    public ResponseEntity<ScheduleInfo> getCollectionSchedule(
            @Parameter(description = "Name of the MongoDB collection", required = true)
            @PathVariable String collectionName) {
        try {
            if (collectionName == null || collectionName.trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection name cannot be empty");
            }
            return ResponseEntity.ok(collectionAnalysisService.getCollectionSchedule(collectionName));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving collection schedule");
        }
    }

    @Operation(summary = "Get recent documents", description = "Retrieves the most recent documents from a MongoDB collection")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved recent documents",
            content = @Content(schema = @Schema(implementation = Document.class))),
        @ApiResponse(responseCode = "400", description = "Invalid collection name provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    @GetMapping("/{collectionName}/recent")
    public ResponseEntity<List<Document>> getRecentDocuments(
            @Parameter(description = "Name of the MongoDB collection", required = true)
            @PathVariable String collectionName) {
        try {
            if (collectionName == null || collectionName.trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection name cannot be empty");
            }
            return ResponseEntity.ok(collectionAnalysisService.getRecentDocuments(collectionName, DEFAULT_RECENT_LIMIT));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving recent documents");
        }
    }
}