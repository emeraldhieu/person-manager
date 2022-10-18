package com.emeraldhieu.personmanager.app.person;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The second way of using Open Feign without Spring.
 */
@FeignClient(value = "post", url = "http://localhost:8080"
)
public interface ValidatorClient {

    @GetMapping("/api2/v2/validate")
    String validatePhoneNumberDigits(@RequestParam String phone);

    @GetMapping("/api2/v1/validate")
    String validatePhoneNumberLength(@RequestParam String phone);

    @GetMapping("/api1/v1/validate")
    String validateEmail(@RequestParam String email);
}
