package jcrm.pp.ua.crmsystem.domain;

import java.util.UUID;

public enum GeneratedIdType {
    LONG(Long.class.getSimpleName()),
    STRING(String.class.getSimpleName()),
    UUID(UUID.class.getSimpleName());

    // declaring private variable for getting values
    private String type;

    // getter method
    public String getType() {
        return this.type;
    }

    // enum constructor - cannot be public or protected
    GeneratedIdType(String type) {
        this.type = type;
    }
}
