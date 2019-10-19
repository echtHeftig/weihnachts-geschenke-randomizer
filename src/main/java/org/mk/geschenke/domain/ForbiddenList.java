package org.mk.geschenke.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ForbiddenList {

    @Id
    @GeneratedValue
    private Long id;
    private String firstPersonName;
    private String secondPersonName;

    public ForbiddenList() {}

    public ForbiddenList(String firstPersonName, String secondPersonName) {
        this.firstPersonName = firstPersonName;
        this.secondPersonName = secondPersonName;
    }

    public String getFirstPersonName() {
        return firstPersonName;
    }
    public String getSecondPersonName() {
        return secondPersonName;
    }

}

