# Person Manager

This is a pair programming test that uses Spring Cloud Open Feign to call an external [validator](https://github.com/emeraldhieu/validator). 

I failed this test because I didn't know that Spring AOP wouldn't work if `@Retryable` method and calling method were in the same class.

The problem is explained at [Understanding AOP Proxies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-understanding-aop-proxies).

The solution is simply extracting the AOP-annotated methods into a separate class.

```java
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
}
```