package com.example.common.repository;

import com.example.common.model.CollectionStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for CollectionStats entity.
 * Provides CRUD operations and custom query methods for CollectionStats.
 */
@Repository
public interface CollectionStatsRepository extends JpaRepository<CollectionStats, String> {
    // The primary key type is String because collectionName is the @Id field in CollectionStats
    
    // Custom query methods can be added here as needed
    // For example:
    // CollectionStats findByCollectionNameAndCapped(String collectionName, boolean capped);
}