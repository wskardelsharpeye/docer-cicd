package com.example.common.repository;

import com.example.common.model.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ScheduleInfo entity.
 * Provides CRUD operations and custom query methods for ScheduleInfo.
 */
@Repository
public interface ScheduleInfoRepository extends JpaRepository<ScheduleInfo, String> {
    // The primary key type is String because collectionName is the @Id field in ScheduleInfo
    
    // Custom query methods can be added here as needed
    // For example:
    // List<ScheduleInfo> findByEnabled(boolean enabled);
    // ScheduleInfo findByCronExpression(String cronExpression);
}