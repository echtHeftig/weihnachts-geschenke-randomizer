package org.mk.geschenke.presenttable;

public class SchenkenderBeschenkenderException extends Throwable {
    private String newMessage;

    public SchenkenderBeschenkenderException(String s) {
        this.newMessage = s;
    }

    public String getNewMessage() {
        return newMessage;
    }
}
