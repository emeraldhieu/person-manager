package com.emeraldhieu.personmanager.app.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonValidator {

    private final ValidatorClient validatorClient;

    @Retryable(value = RuntimeException.class, backoff = @Backoff(5000))
    public String validatePhoneNumberDigits(String phoneNumber) {
        log.info("Calling #validatePhoneNumberDigits...");
        String response = validatorClient.validatePhoneNumberDigits(phoneNumber);
        log.info("#validatePhoneNumberDigits; " + response);
        return response;
    }

    @Retryable(value = RuntimeException.class, backoff = @Backoff(5000))
    public String validatePhoneNumberLength(String phoneNumber) {
        log.info("Calling #validatePhoneNumberLength...");
        String response = validatorClient.validatePhoneNumberLength(phoneNumber);
        log.info("#validatePhoneNumberLength; " + response);
        return response;
    }

    @Retryable(value = RuntimeException.class, backoff = @Backoff(5000))
    public String validateEmail(String email) {
        log.info("Calling #validateEmail...");
        String response = validatorClient.validateEmail(email);
        log.info("#validateEmail; " + response);
        return response;
    }
}
