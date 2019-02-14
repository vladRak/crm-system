package ua.pp.jcrm.domain.entity.impl;

import ua.pp.jcrm.domain.entity.BaseClientImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.*;

@Entity
@Table(name = "company")
@DiscriminatorValue("company")
@Data
//@Audited
@EqualsAndHashCode(callSuper = true, exclude = "contactPersons")
public class Company extends BaseClientImpl {

    private static final long serialVersionUID = 1L;

    private String fullName;

    private String branch;

    private String webSite;

    @OneToMany(cascade = {CascadeType.ALL},
            //targetEntity = Contact.class,
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<ContactPerson> contactPersons = new ArrayList<>();

    public Company() {
    }

    public void addContacts(List<ContactPerson> contactPeople) {
        for (ContactPerson c : contactPeople) {
            addContact(c);
        }
    }

    public void addContact(ContactPerson contactPerson) {
        addContact(contactPerson, true);
    }

    void addContact(ContactPerson contactPerson, boolean set) {
        if (contactPerson != null) {
            if (getContactPersons().contains(contactPerson)) {
                getContactPersons().set(getContactPersons().indexOf(contactPerson), contactPerson);
            } else {
                getContactPersons().add(contactPerson);
            }
            if (set) {
                contactPerson.setCompany(this, false);
            }
        }
    }

    public void removeContact(ContactPerson contactPerson) {
        if (nonNull(contactPerson)) {
            getContactPersons().remove(contactPerson);
            contactPerson.setCompany(null);
        }
    }
}
