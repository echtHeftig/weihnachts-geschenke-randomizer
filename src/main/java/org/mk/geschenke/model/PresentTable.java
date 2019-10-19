package org.mk.geschenke.model;

import org.mk.geschenke.person.Person;

public class PresentTable {
    private Person schenkender;
    private Person beschenkter;

    public PresentTable() {
    }

    public PresentTable(Person schenkender, Person beschenkter) {
        this.schenkender = schenkender;
        this.beschenkter = beschenkter;
    }

    public Person getSchenkender() {
        return schenkender;
    }

    public void setSchenkender(Person schenkender) {
        this.schenkender = schenkender;
    }

    public Person getBeschenkter() {
        return beschenkter;
    }

    public void setBeschenkter(Person beschenkter) {
        this.beschenkter = beschenkter;
    }
}
