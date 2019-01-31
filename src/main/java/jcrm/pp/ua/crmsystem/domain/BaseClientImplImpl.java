package jcrm.pp.ua.crmsystem.domain;

import jcrm.pp.ua.crmsystem.domain.entity.*;
import jcrm.pp.ua.crmsystem.domain.interfaces.BaseClient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "base_client")
@EqualsAndHashCode(callSuper = true, exclude = "leads")
//@Audited
//@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseClientImplImpl extends BaseTaskTargetImpl implements BaseClient {

    private static final long serialVersionUID = 1L;

    private User responsible;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Phone> phones;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Email> emails;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client",
            orphanRemoval = true)
    private List<Lead> leads;

//    public BaseClientImplImpl(
//            Long id, Long versionNum,
//            boolean deleted, boolean physicalRemoval,
//            Account account, List<Task> tasks,
//            User responsible, List<Phone> phones,
//            List<Email> emails, List<Address> addresses,
//            List<Lead> leads) {
//        super(id, versionNum, deleted, physicalRemoval, account, tasks);
//        this.responsible = responsible;
//        this.phones = phones;
//        this.emails = emails;
//        this.addresses = addresses;
//        this.leads = leads;
//    }










    public void setAddresses(List<Address> addresses) {
        if (addresses != null) addAddresses(addresses);
    }

    public void setEmails(List<Email> emails) {
        if (emails != null) addEmails(emails);
    }

    public void setLeads(List<Lead> leads) {
        if (leads != null) addLeads(leads);
    }

    public void setPhones(List<Phone> phones) {
        if (phones != null) addPhones(phones);
    }

    public void addAddresses(List<Address> addresses) {
        for (Address a : addresses) {
            a.setClient(this);
            this.addresses.add(a);
        }
    }

    public void addEmails(List<Email> emails) {
        for (Email e : emails) {
            e.setClient(this);
            this.emails.add(e);
        }
    }

    public void addLeads(List<Lead> leads) {
        for (Lead l : leads) {
            addLead(l);
        }
    }

    public void addPhones(List<Phone> phones) {
        for (Phone p : phones) {
            p.setClient(this);
            this.phones.add(p);
        }
    }

    public void addLead(Lead lead) {
        addLead(lead, true);
    }

    public void addLead(Lead lead, boolean set) {
        if (lead != null) {
            if (getLeads().contains(lead)) {
                getLeads().set(getLeads().indexOf(lead), lead);
            } else {
                getLeads().add(lead);
            }
            if (set) {
                lead.setClient(this, false);
            }
        }
    }

    public void removeLead(Lead lead) {
        getLeads().remove(lead);
        lead.setClient(null);
    }
}
