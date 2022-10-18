package com.emeraldhieu.personmanager.app.person;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class Person {
    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
}
