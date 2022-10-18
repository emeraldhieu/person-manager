package com.emeraldhieu.personmanager.app.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonScheduler {

    private final PersonRepository personRepository;

    @Scheduled(initialDelay = 0, timeUnit = TimeUnit.SECONDS, fixedDelay = 5)
    public void log() {
        int numberOfPersons = personRepository.getPersons().entrySet().size();
        log.info("number of persons: " + numberOfPersons);
    }
}
