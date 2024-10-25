package com.bank.atmsystem.repository;

import com.bank.atmsystem.model.FailureEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FailureEventRepository extends JpaRepository<FailureEvent, String> {
    // Find all failure events for a specific ATM
    List<FailureEvent> findByAtmId(String atmId);

    // Find failure events within a specific time range
    List<FailureEvent> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT fe FROM FailureEvent fe WHERE fe.timestamp >= :recentTime")
    List<FailureEvent> findAllByOccurredRecently(LocalDateTime recentTime);

}
