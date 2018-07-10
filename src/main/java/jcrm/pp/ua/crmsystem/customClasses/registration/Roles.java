package jcrm.pp.ua.crmsystem.customClasses.registration;

import java.util.Arrays;
import java.util.Collection;

public enum Roles {

    ROLE_SYSTEM {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.CREATE_ACCOUNT_PRIVILEGE
            );
        }
    },
    ROLE_GUEST {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.READ_PRIVILEGE
            );
        }
    },
    ROLE_USER {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.WRITE_PRIVILEGE,
                    Privileges.DELETE_PRIVILEGE
            );
        }
    },
    ROLE_MANAGER {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.CREATE_GUEST_PRIVILEGE,
                    Privileges.CREATE_USER_PRIVILEGE,
                    Privileges.DELETE_GUEST_PRIVILEGE,
                    Privileges.DELETE_USER_PRIVILEGE,
                    Privileges.BACK_UP_PRIVILEGE
            );
        }
    },
    ROLE_AUDITOR {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.PHYSICAL_BACK_UP_PRIVILEGE
            );
        }
    },
    ROLE_ADMINISTRATOR {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.CREATE_MANAGER_PRIVILEGE,
                    Privileges.CREATE_AUDITOR_PRIVILEGE,
                    Privileges.DELETE_MANAGER_PRIVILEGE,
                    Privileges.DELETE_AUDITOR_PRIVILEGE
            );
        }
    },
    ROLE_SYS_ADMINISTRATOR {
        public Collection<Privileges> privileges() {
            return Arrays.asList(
                    Privileges.DELETE_ACCOUNT_PRIVILEGE,
                    Privileges.BLOCK_ACCOUNT_PRIVILEGE,
                    Privileges.BACK_UP_ACCOUNT_PRIVILEGE
            );
        }
    };

    public abstract Collection<Privileges> privileges();
}
