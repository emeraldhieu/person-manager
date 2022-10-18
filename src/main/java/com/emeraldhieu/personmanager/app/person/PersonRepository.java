package com.emeraldhieu.personmanager.app.person;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
@RequiredArgsConstructor
public class PersonRepository implements InitializingBean {

    private final Map<String, Person> personsByPersonId = new TreeMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        personsByPersonId.put("42", Person.builder()
            .id("42")
            .name("John Doe")
            .email("johndoe@gmail.com")
            .phoneNumber("+84909327140")
            .build());
        personsByPersonId.put("666", Person.builder()
            .id("666")
            .name("Jane Roe")
            .email("janeroe@gmail.com")
            .phoneNumber("+90909327199")
            .build());
    }

    public Map<String, Person> getPersons() {
        return personsByPersonId;
    }
}
