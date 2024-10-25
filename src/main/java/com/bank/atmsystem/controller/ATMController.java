// ATMController.java
package com.bank.atmsystem.controller;

import com.bank.atmsystem.model.*;
import com.bank.atmsystem.service.*;
import com.nimbusds.jose.util.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/atm")
public class ATMController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private FailureLogService failureLogService;

    @Autowired
    private CameraLogService cameraLogService;

    @Autowired
    private ATMDeviceService atmDeviceService;

    @Autowired
    private UsageService usageService;

    @Autowired
    private FailureService failureService;

    @GetMapping("/authorize")
    @PreAuthorize("hasAuthority('SCOPE_user')")  // Only accessible to users with 'user' scope
    public String authorizeUser(@AuthenticationPrincipal Jwt jwt) {
        // Returns the authenticated user's details from the JWT token
        return "User authorized with ID: " + jwt.getSubject();
    }

    @GetMapping("/transactions/breakdown")
    public Map<String, Long> getTransactionBreakdown() {
        return transactionService.getTransactionBreakdown();
    }
    @GetMapping("/device/health")
    public String getDeviceHealthStatus() {
        return atmDeviceService.getHealthStatus();
    }

    @GetMapping("/usage/stats")
    public UsageStats getUsageStatistics() {
        return usageService.getUsageStatistics();
    }

    @GetMapping("/failures/{hours}")
    public List<FailureEvent> getFailures(@PathVariable int hours) {
        return failureService.getFailures(hours);
    }
    @GetMapping("/camera/download")
    public ResponseEntity<Resource> downloadCameraFootage(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Resource file = cameraLogService.getFootage(startTime, endTime);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }

    @GetMapping("/customers/last24hours")
    public long getTotalCustomersLast24Hours() {
        // Assuming `transactionService` can fetch unique customer count within a time range.
        return transactionService.countUniqueCustomersLast24Hours();
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(@RequestParam String atmId, @RequestParam Date startTime, @RequestParam Date endTime) {
        return transactionService.getTransactionsByAtmAndTimeRange(atmId, startTime, endTime);
    }

    @GetMapping("/failures")
    public List<FailureLog> getFailures(@RequestParam String atmId, @RequestParam Date startTime, @RequestParam Date endTime) {
        return failureLogService.getFailuresByAtmAndTimeRange(atmId, startTime, endTime);
    }

    @GetMapping("/camera-logs")
    public List<CameraLog> getCameraLogs(@RequestParam String atmId, @RequestParam Date startTime, @RequestParam Date endTime) {
        return cameraLogService.getCameraLogsByAtmAndTimeRange(atmId, startTime, endTime);
    }
}
