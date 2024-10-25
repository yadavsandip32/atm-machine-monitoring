package com.bank.atmsystem.repository;

import com.bank.atmsystem.model.FailureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface FailureLogRepository extends JpaRepository<FailureLog, Long> {
    List<FailureLog> findByAtmIdAndTimestampBetween(String atmId, Date start, Date end);
}
