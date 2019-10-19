package org.mk.geschenke.model;

public class SchenkenderBeschenkenderException extends Throwable {
    private String newMessage;

    public SchenkenderBeschenkenderException(String s) {
        this.newMessage = s;
    }

    public String getNewMessage() {
        return newMessage;
    }
}
