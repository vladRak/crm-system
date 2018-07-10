package jcrm.pp.ua.crmsystem.customClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jcrm.pp.ua.crmsystem.entities.Imp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyPatchRequest {

    private boolean containsAddresses = false;
    private List<Address> addresses = new ArrayList<>();

    private boolean containsContacts = false;
    private List<Contact> contacts;

    private boolean containsEmails = false;
    private List<Email> emails = new ArrayList<>();

    private boolean containsFullname = false;
    private String fullName;

    private boolean containsLeads = false;
    private List<Lead> leads = new ArrayList<>();

    private boolean containsPhones = false;
    private List<Phone> phones = new ArrayList<>();

    private boolean containsTasks = false;
    private List<Task> tasks = new ArrayList<>();

    private Map<String, Object> fields = new HashMap<>();

    private static final String NAME_KEY = "fullname";
    private static final String ADDRESSES_KEY = "addresses";
    private static final String CONTACTS_KEY = "contacts";
    private static final String EMAILS_KEY = "emails";
    private static final String LEADS_KEY = "leads";
    private static final String PHONES_KEY = "phones";
    private static final String TASKS_KEY = "tasks";

    public Map<String,Object> getFields(){
        return this.fields;
    }

    void setAddresses(List<Address> addresses) {
        fields.put(ADDRESSES_KEY, addresses);
    }

    boolean containsAddresses() {
        return fields.containsKey(ADDRESSES_KEY);
    }

    List<Address> getAddresses() {
        return (List<Address>) fields.get(ADDRESSES_KEY);
    }

    void setContacts(List<Contact> contacts) {
        fields.put(CONTACTS_KEY, contacts);
    }

    boolean containsContacts() {
        return fields.containsKey(CONTACTS_KEY);
    }

    List<Contact> getContacts() {return (List<Contact>) fields.get(CONTACTS_KEY);}

    void setEmails(List<Email> emails) {
        fields.put(EMAILS_KEY, emails);
    }

    boolean containsEmails() {
        return fields.containsKey(EMAILS_KEY);
    }

    List<Email> getEmails() {
        return (List<Email>) fields.get(EMAILS_KEY);
    }

    void setFullname(String fullname) {
        fields.put(NAME_KEY, fullname);
    }

    boolean containsFullname() {
        return fields.containsKey(NAME_KEY);
    }

    String getFullname() {
        return (String) fields.get(NAME_KEY);
    }

    void setLeads(List<Lead> leads) {
        fields.put(LEADS_KEY, leads);
    }

    boolean containsLeads() {
        return fields.containsKey(LEADS_KEY);
    }

    List<Lead> getLeads() {
        return (List<Lead>) fields.get(LEADS_KEY);
    }

    void setPhones(List<Phone> phones) {
        fields.put(PHONES_KEY, phones);
    }

    boolean containsPhones() {
        return fields.containsKey(PHONES_KEY);
    }

    List<Phone> getPhones() {
        return (List<Phone>) fields.get(PHONES_KEY);
    }

    void setTasks(List<Task> tasks) {
        fields.put(TASKS_KEY, tasks);
    }

    boolean containsTasks() {
        return fields.containsKey(TASKS_KEY);
    }

    List<Task> getTasks() {
        return (List<Task>) fields.get(TASKS_KEY);
    }
}
