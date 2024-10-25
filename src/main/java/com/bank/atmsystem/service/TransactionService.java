package com.bank.atmsystem.service;

import com.bank.atmsystem.model.Transaction;
import com.bank.atmsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public long countUniqueCustomersLast24Hours() {
        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        return transactionRepository.countUniqueCustomersSince(last24Hours);
    }

    public List<Transaction> getTransactionsByAtmAndTimeRange(String atmId, Date startTime, Date endTime) {
        return transactionRepository.findByAtmIdAndTimestampBetween(atmId, startTime, endTime);
    }

    public Map<String, Long> getTransactionBreakdown() {
        Map<String, Long> breakdown = new HashMap<>();
        breakdown.put("Deposit", transactionRepository.countByType("Deposit"));
        breakdown.put("Cash Withdrawal", transactionRepository.countByType("Cash Withdrawal"));
        breakdown.put("Balance Information", transactionRepository.countByType("Balance Information"));
        return breakdown;
    }
}
