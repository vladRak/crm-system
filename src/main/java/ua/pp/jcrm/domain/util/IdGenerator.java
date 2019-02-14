package ua.pp.jcrm.domain.util;

import java.util.UUID;

public class IdGenerator {

    public static UUID createId() {
        return UUID
                .randomUUID();
                //.getMostSignificantBits() & Long.MAX_VALUE;
    }
}
