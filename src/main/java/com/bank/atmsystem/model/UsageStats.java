package com.bank.atmsystem.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UsageStats {

    private int totalCustomers;
    private Map<String, Integer> transactionBreakdown;
    private LocalDateTime lastResetTime;

    public UsageStats() {
        this.totalCustomers = 0;
        this.transactionBreakdown = new HashMap<>();
        this.transactionBreakdown.put("Deposit", 0);
        this.transactionBreakdown.put("Cash Withdrawal", 0);
        this.transactionBreakdown.put("Balance Information", 0);
        this.lastResetTime = LocalDateTime.now();
    }

    /**
     * Records a transaction by type and increments customer count if new customer.
     *
     * @param transactionType the type of transaction (e.g., Deposit, Cash Withdrawal)
     */
    public void recordTransaction(String transactionType) {
        this.transactionBreakdown.put(transactionType, this.transactionBreakdown.getOrDefault(transactionType, 0) + 1);
    }

    /**
     * Increment the customer count
     */
    public void incrementCustomerCount() {
        this.totalCustomers++;
    }

    /**
     * Resets the usage stats (e.g., for a new day).
     */
    public void resetUsageStats() {
        this.totalCustomers = 0;
        this.transactionBreakdown.put("Deposit", 0);
        this.transactionBreakdown.put("Cash Withdrawal", 0);
        this.transactionBreakdown.put("Balance Information", 0);
        this.lastResetTime = LocalDateTime.now();
    }

    /**
     * Returns the total number of customers in the last 24 hours.
     *
     * @return the total number of customers
     */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Returns the breakdown of transactions by type.
     *
     * @return a map with transaction types and their counts
     */
    public Map<String, Integer> getTransactionBreakdown() {
        return new HashMap<>(transactionBreakdown); // Return a copy to prevent modification
    }

    /**
     * Returns the time of the last reset (i.e., when stats were last cleared).
     *
     * @return the last reset time
     */
    public LocalDateTime getLastResetTime() {
        return lastResetTime;
    }
}
