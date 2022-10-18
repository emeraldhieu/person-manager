package com.emeraldhieu.personmanager.app.person;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonException extends RuntimeException {

    public PersonException(String message) {
        super(message);
    }

    public PersonException(String message, Exception exception) {
        super(message, exception);
    }
}
