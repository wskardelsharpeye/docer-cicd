package com.example.common.repository;

import com.example.common.model.DatabaseStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for DatabaseStats entity.
 * Provides CRUD operations and custom query methods for DatabaseStats.
 */
@Repository
public interface DatabaseStatsRepository extends JpaRepository<DatabaseStats, String> {
    // The primary key type is String because databaseName is the @Id field in DatabaseStats
    
    // Custom query methods can be added here as needed
    // For example:
    // List<DatabaseStats> findByDocumentCountGreaterThan(int count);
}