package com.bank.atmsystem.repository;

import com.bank.atmsystem.model.CameraLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface CameraLogRepository extends JpaRepository<CameraLog, Long> {
    List<CameraLog> findByAtmIdAndTimestampBetween(String atmId, Date start, Date end);
}
