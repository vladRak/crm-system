package jcrm.pp.ua.crmsystem.entities;

import java.util.UUID;

public class IdGenerator {

    public static String createId() {
        return UUID
                .randomUUID().toString();

                //.getMostSignificantBits() & Long.MAX_VALUE;
    }
}
