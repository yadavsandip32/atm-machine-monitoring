package com.bank.atmsystem.model;

import java.time.LocalDateTime;

public class FailureEvent {
    private String id; // Unique identifier for the failure event
    private String atmId; // The ID of the ATM where the failure occurred
    private String transactionType; // Type of transaction attempted when the failure occurred
    private String errorMessage; // Description of the failure
    private LocalDateTime timestamp; // When the failure occurred

    public FailureEvent(String id, String atmId, String transactionType, String errorMessage, LocalDateTime timestamp) {
        this.id = id;
        this.atmId = atmId;
        this.transactionType = transactionType;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
