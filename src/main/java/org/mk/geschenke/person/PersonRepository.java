package org.mk.geschenke.person;

import org.mk.geschenke.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    void deleteByName(String personName);
}
