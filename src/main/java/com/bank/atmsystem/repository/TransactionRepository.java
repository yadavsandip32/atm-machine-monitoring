package com.bank.atmsystem.repository;

import com.bank.atmsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAtmIdAndTimestampBetween(String atmId, Date start, Date end);

    @Query("SELECT COUNT(DISTINCT t.customerId) FROM Transaction t WHERE t.timestamp >= :since")
    long countUniqueCustomersSince(LocalDateTime since);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.type = :transactionType")
    long countByType(String transactionType);

}
