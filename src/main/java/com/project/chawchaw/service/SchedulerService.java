package com.project.chawchaw.service;

import com.project.chawchaw.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final ViewRepository viewRepository;

    @Scheduled(cron = "0 0 00 * * *")
    @Transactional
    public void deleteView(){

          viewRepository.truncateTable();
    }

}
