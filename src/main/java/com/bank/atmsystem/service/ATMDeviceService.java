package com.bank.atmsystem.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ATMDeviceService {

    // Simulated ATM device status data
    private final Map<String, String> atmDeviceStatus;

    public ATMDeviceService() {
        this.atmDeviceStatus = new HashMap<>();

        // Initialize some sample data for ATM devices
        atmDeviceStatus.put("ATM001", "Online");
        atmDeviceStatus.put("ATM002", "Offline");
        atmDeviceStatus.put("ATM003", "Maintenance");
    }

    /**
     * Get the health status of all ATM devices.
     *
     * @return a map with ATM IDs and their respective statuses
     */
    public Map<String, String> getAllDeviceHealthStatus() {
        return new HashMap<>(atmDeviceStatus); // Return a copy of the current status map
    }

    /**
     * Get the health status of a specific ATM device by its ID.
     *
     * @param atmId the ID of the ATM device
     * @return the status of the specified ATM device or "Unknown" if not found
     */
    public String getDeviceHealthStatus(String atmId) {
        return atmDeviceStatus.getOrDefault(atmId, "Unknown");
    }

    /**
     * Update the health status of a specific ATM device.
     *
     * @param atmId the ID of the ATM device
     * @param status the new status to set for the ATM device
     */
    public void updateDeviceHealthStatus(String atmId, String status) {
        atmDeviceStatus.put(atmId, status);
    }

    public String getHealthStatus(String atmId) {
        Map<String, String> healthStatus = new HashMap<>();

        // Check if the ATM ID exists in our system
        if (!atmDeviceStatus.containsKey(atmId)) {
            healthStatus.put("status", "Unknown");
            healthStatus.put("message", "ATM ID not found.");
            return healthStatus.toString();
        }

        // Example: Simulate a check for ATM health status
        String status = atmDeviceStatus.get(atmId);
        healthStatus.put("status", status);

        // Add more health details based on the status
        if ("Online".equals(status)) {
            healthStatus.put("message", "ATM is functioning properly.");
            healthStatus.put("lastCheck", "2024-10-24T10:30:00"); // Simulated timestamp
        } else if ("Offline".equals(status)) {
            healthStatus.put("message", "ATM is currently offline.");
            healthStatus.put("lastCheck", "2024-10-24T08:15:00"); // Simulated timestamp
        } else if ("Maintenance".equals(status)) {
            healthStatus.put("message", "ATM is under maintenance.");
            healthStatus.put("lastCheck", "2024-10-23T14:45:00"); // Simulated timestamp
        } else {
            healthStatus.put("message", "Status unknown.");
            healthStatus.put("lastCheck", "N/A");
        }

        return healthStatus.toString();

    }
}
