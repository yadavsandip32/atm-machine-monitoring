package com.bank.atmsystem.service;

import com.bank.atmsystem.model.FailureEvent;
import com.bank.atmsystem.repository.FailureEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FailureService {

    @Autowired
    private FailureEventRepository failureEventRepository;

    public List<FailureEvent> getFailures(int hours) {
//        return failureEventRepository.findAllByOccurredRecently();
        LocalDateTime recentTime = LocalDateTime.now().minusHours(hours);
        return failureEventRepository.findAllByOccurredRecently(recentTime);

    }
}
