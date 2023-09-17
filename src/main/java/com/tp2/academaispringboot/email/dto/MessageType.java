package com.tp2.academaispringboot.email.dto;

public enum MessageType {

    CONTACT(1),
    FORGOT_PASSWORD(2);

    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MessageType fromValue(int value) {
        for (MessageType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Valor inválido para MessageType: " + value);
    }

}
