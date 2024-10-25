package com.bank.atmsystem.service;

import com.bank.atmsystem.model.FailureLog;
import com.bank.atmsystem.repository.FailureLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FailureLogService {
    @Autowired
    private FailureLogRepository failureLogRepository;

    public List<FailureLog> getFailuresByAtmAndTimeRange(String atmId, Date startTime, Date endTime) {
        return failureLogRepository.findByAtmIdAndTimestampBetween(atmId, startTime, endTime);
    }
}
