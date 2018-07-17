package jcrm.pp.ua.crmsystem.entities.Imp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
//@DiscriminatorValue("company")
@Data
@Audited
@EqualsAndHashCode(callSuper = true, exclude = "contacts")
public class Company extends BaseClientImp implements Serializable{

    private static final long serialVersionUID = 1L;

    private String fullName;

    private String branch;

    private String webSite;

    @OneToMany(cascade = {CascadeType.ALL},
            //targetEntity = Contact.class,
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<Contact> contacts = new ArrayList<Contact>();

    public Company() {
    }

    public void addContacts(List<Contact> contacts){
        for (Contact c : contacts) {
            addContact(c);
        }
    }

    public void addContact(Contact contact){
        addContact(contact,true);
    }

    void addContact(Contact contact, boolean set){
        if(contact !=null){
            if (getContacts().contains(contact)){
                getContacts().set(getContacts().indexOf(contact),contact);
            }else {
                getContacts().add(contact);
            }
            if (set){
                contact.setCompany(this,false);
            }
        }
    }

    public void removeContact(Contact contact){
        getContacts().remove(contact);
        contact.setCompany(null);
    }
}
