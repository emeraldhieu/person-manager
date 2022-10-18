package com.emeraldhieu.personmanager.app.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPersonService implements PersonService {

    private final PersonValidator personValidator;
    private final PersonRepository personRepository;

    @Override
    public List<PersonDTO> getPersons() {
        return personRepository.getPersons().values().stream()
            .map(person -> PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .build())
            .collect(Collectors.toList());
    }

    public void validatePhoneNumber(PersonDTO personDTO) {
        String phoneNumber = personDTO.getPhoneNumber();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> personValidator.validatePhoneNumberDigits(phoneNumber));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> personValidator.validatePhoneNumberLength(phoneNumber));

        String email = personDTO.getEmail();
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> personValidator.validateEmail(email));

        String combinedResponse = Stream.of(future1, future2, future3)
            .map(CompletableFuture::join)
            .collect(Collectors.joining(" "));
        log.info("combinedResponse: " + combinedResponse);

        // TODO Check HTTP status first.
        // ...

        if (combinedResponse.contains("invalid")) {
            throw new PersonException("Invalid phone number");
        }
    }

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        validatePhoneNumber(personDTO);

        String id = UUID.randomUUID().toString();
        Person personToPersist = Person.builder()
            .id(id)
            .name(personDTO.getName())
            .email(personDTO.getEmail())
            .phoneNumber(personDTO.getPhoneNumber())
            .build();
        personRepository.getPersons().put(id, personToPersist);

        return mapPersonToPersonDTO(personToPersist);
    }

    @Override
    public void deletePerson(String id) {
        personRepository.getPersons().remove(id);
    }

    @Override
    public PersonDTO getPerson(String id) {
        Person retrievedPerson = personRepository.getPersons().get(id);
        return mapPersonToPersonDTO(retrievedPerson);
    }

    private static PersonDTO mapPersonToPersonDTO(Person retrievedPerson) {
        return PersonDTO.builder()
            .id(retrievedPerson.getId())
            .name(retrievedPerson.getName())
            .email(retrievedPerson.getEmail())
            .phoneNumber(retrievedPerson.getPhoneNumber())
            .build();
    }
}
