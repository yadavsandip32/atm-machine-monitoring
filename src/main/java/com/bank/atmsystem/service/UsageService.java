package com.bank.atmsystem.service;

import com.bank.atmsystem.model.UsageStats;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsageService {

    private final UsageStats usageStats;

    public UsageService() {
        this.usageStats = new UsageStats();
    }

    /**
     * Increments the count of customers in the last 24 hours.
     */
    public void incrementCustomerCount() {
        usageStats.incrementCustomerCount();
    }

    /**
     * Records a transaction of a specific type (e.g., Deposit, Cash Withdrawal).
     *
     * @param transactionType The type of transaction being recorded.
     */
    public void recordTransaction(String transactionType) {
        usageStats.recordTransaction(transactionType);
    }

    /**
     * Gets the total number of unique customers who used the ATM in the last 24 hours.
     *
     * @return Total customers in the last 24 hours.
     */
    public int getTotalCustomersInLast24Hours() {
        return usageStats.getTotalCustomers();
    }

    /**
     * Gets a breakdown of transactions by type for the last 24 hours.
     *
     * @return A map with the transaction type as the key and the count as the value.
     */
    public Map<String, Integer> getTransactionBreakdown() {
        return usageStats.getTransactionBreakdown();
    }

    /**
     * Resets the usage statistics (e.g., resets the count for a new day).
     */
    public void resetUsageStats() {
        usageStats.resetUsageStats();
    }

    public UsageStats getUsageStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalCustomers", usageStats.getTotalCustomers());
        statistics.put("transactionBreakdown", usageStats.getTransactionBreakdown());
        return (UsageStats) statistics;
    }
}
