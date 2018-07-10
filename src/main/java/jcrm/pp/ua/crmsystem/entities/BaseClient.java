package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.entities.Imp.Address;
import jcrm.pp.ua.crmsystem.entities.Imp.Email;
import jcrm.pp.ua.crmsystem.entities.Imp.Lead;
import jcrm.pp.ua.crmsystem.entities.Imp.Phone;

import java.util.ArrayList;
import java.util.List;

public interface BaseClient extends BaseBusinessObj {
    List<Phone> phones = new ArrayList<>();
    List<Email> emails = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();
    List<Lead> leads = new ArrayList<>();
    void setAddresses(List<Address> addresses);
    void setEmails(List<Email> emails);
    void setLeads(List<Lead> leads);
    void setPhones(List<Phone> phones);
    void addAddresses(List<Address> addresses);
    void addEmails(List<Email> emails);
    void addLeads(List<Lead> leads);
    void addPhones(List<Phone> phones);
    void addLead(Lead lead);
    void removeLead(Lead lead);
}
