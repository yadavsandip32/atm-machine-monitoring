package com.bank.atmsystem.service;

import com.bank.atmsystem.model.CameraLog;
import com.bank.atmsystem.repository.CameraLogRepository;
import com.nimbusds.jose.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CameraLogService {
    @Autowired
    private CameraLogRepository cameraLogRepository;

    @Autowired
    private FootageStorageService footageStorageService;

    public List<CameraLog> getCameraLogsByAtmAndTimeRange(String atmId, Date startTime, Date endTime) {
        return cameraLogRepository.findByAtmIdAndTimestampBetween(atmId, startTime, endTime);
    }
    public Resource getFootage(LocalDateTime startTime, LocalDateTime endTime) {
        // Assuming footage retrieval and storage as a file or stream
        return (Resource) footageStorageService.getFootageByTimeRange(startTime, endTime);
    }

}
