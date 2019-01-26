package jcrm.pp.ua.crmsystem.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
//@DiscriminatorValue("baseClient")
@Table(name = "base_client")
@EqualsAndHashCode(callSuper = true, exclude = "leads")
@Audited
@Data
public class BaseClient extends BaseBusinessObj {

    private static final long serialVersionUID = 1L;

    private User responsible;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Email> emails = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

    public void setAddresses(List<Address> addresses){
        if(addresses!=null) addAddresses(addresses);
    }

    public void setEmails(List<Email> emails){
        if(emails!=null) addEmails(emails);
    }

    public void setLeads(List<Lead> leads){
        if(leads !=null) addLeads(leads);
    }

    public void setPhones(List<Phone> phones){
        if(phones!=null)   addPhones(phones);
    }

    public void addAddresses(List<Address> addresses){
        for (Address a : addresses) {
            a.setClient(this);
            this.addresses.add(a);
        }
    }

    public void addEmails(List<Email> emails){
        for (Email e : emails) {
            e.setClient(this);
            this.emails.add(e);
        }
    }

    public void addLeads(List<Lead> leads){
        for (Lead l : leads) {
            addLead(l);
        }
    }

    public void addPhones(List<Phone> phones){
        for (Phone p : phones) {
            p.setClient(this);
            this.phones.add(p);
        }
    }

    public void addLead(Lead lead){
        addLead(lead,true);
    }

    public void addLead(Lead lead, boolean set){
        if(lead !=null){
            if (getLeads().contains(lead)){
                getLeads().set(getLeads().indexOf(lead),lead);
            }else {
                getLeads().add(lead);
            }
            if (set){
                lead.setClient(this,false);
            }
        }
    }

    public void removeLead(Lead lead){
        getLeads().remove(lead);
        lead.setClient(null);
    }
}
