package org.mk.geschenke.domain;

public class PresentTable {
    private Person schenkender;
    private Person beschenkter;

    public PresentTable(Person schenkender, Person beschenkter) {
        this.schenkender = schenkender;
        this.beschenkter = beschenkter;
    }

    public Person getSchenkender() {
        return schenkender;
    }

    public Person getBeschenkter() {
        return beschenkter;
    }

}
