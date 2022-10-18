package com.emeraldhieu.personmanager.app.person;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getPersons();

    PersonDTO createPerson(PersonDTO personDTO);

    void deletePerson(String id);

    PersonDTO getPerson(String id);
}
