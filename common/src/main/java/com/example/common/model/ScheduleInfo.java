package com.example.common.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedule_info")
public class ScheduleInfo {
    @Id
    @Column(name = "collection_name")
    private String collectionName;
    
    @Column(name = "cron_expression")
    private String cronExpression;
    
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "last_execution_time")
    private String lastExecutionTime;
    
    @Column(name = "next_execution_time")
    private String nextExecutionTime;
    
    // Default constructor
    public ScheduleInfo() {
    }
    
    // All-args constructor
    public ScheduleInfo(String collectionName, String cronExpression, boolean enabled,
                       String lastExecutionTime, String nextExecutionTime) {
        this.collectionName = collectionName;
        this.cronExpression = cronExpression;
        this.enabled = enabled;
        this.lastExecutionTime = lastExecutionTime;
        this.nextExecutionTime = nextExecutionTime;
    }
    
    // Getters
    public String getCollectionName() {
        return collectionName;
    }
    
    public String getCronExpression() {
        return cronExpression;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public String getLastExecutionTime() {
        return lastExecutionTime;
    }
    
    public String getNextExecutionTime() {
        return nextExecutionTime;
    }
    
    // Setters
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
    
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public void setLastExecutionTime(String lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }
    
    public void setNextExecutionTime(String nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }
    
    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        ScheduleInfo that = (ScheduleInfo) o;
        
        if (enabled != that.enabled) return false;
        if (collectionName != null ? !collectionName.equals(that.collectionName) : that.collectionName != null) return false;
        if (cronExpression != null ? !cronExpression.equals(that.cronExpression) : that.cronExpression != null) return false;
        if (lastExecutionTime != null ? !lastExecutionTime.equals(that.lastExecutionTime) : that.lastExecutionTime != null) return false;
        return nextExecutionTime != null ? nextExecutionTime.equals(that.nextExecutionTime) : that.nextExecutionTime == null;
    }
    
    @Override
    public int hashCode() {
        int result = collectionName != null ? collectionName.hashCode() : 0;
        result = 31 * result + (cronExpression != null ? cronExpression.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (lastExecutionTime != null ? lastExecutionTime.hashCode() : 0);
        result = 31 * result + (nextExecutionTime != null ? nextExecutionTime.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "ScheduleInfo{" +
                "collectionName='" + collectionName + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", enabled=" + enabled +
                ", lastExecutionTime='" + lastExecutionTime + '\'' +
                ", nextExecutionTime='" + nextExecutionTime + '\'' +
                '}';
    }
}