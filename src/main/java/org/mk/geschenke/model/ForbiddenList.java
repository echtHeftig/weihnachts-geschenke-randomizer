package org.mk.geschenke.model;

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

    public ForbiddenList() {
    }

    public ForbiddenList(String firstPersonName, String secondPersonName) {
        this.firstPersonName = firstPersonName;
        this.secondPersonName = secondPersonName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstPersonName() {
        return firstPersonName;
    }

    public void setFirstPersonName(String firstPersonName) {
        this.firstPersonName = firstPersonName;
    }

    public String getSecondPersonName() {
        return secondPersonName;
    }

    public void setSecondPersonName(String secondPersonName) {
        this.secondPersonName = secondPersonName;
    }
}

