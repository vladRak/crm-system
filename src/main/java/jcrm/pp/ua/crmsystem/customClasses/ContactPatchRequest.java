package jcrm.pp.ua.crmsystem.customClasses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jcrm.pp.ua.crmsystem.domain.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactPatchRequest {

    private boolean containsAddresses = false;
    private List<Address> addresses = new ArrayList<>();

    private boolean containsCompany = false;
    private Company company;

    private boolean containsEmails = false;
    private List<Email> emails = new ArrayList<>();

    private boolean containsFullname = false;
    private Name fullName;

    private boolean containsLeads = false;
    private List<Lead> leads = new ArrayList<>();

    private boolean containsPhones = false;
    private List<Phone> phones = new ArrayList<>();

    private boolean containsPosition = false;
    private String position;

    private boolean containsTasks = false;
    private List<Task> tasks = new ArrayList<>();

    private Map<String, Object> fields = new HashMap<>();

    private static final String NAME_KEY = "fullname";
    private static final String ADDRESSES_KEY = "addresses";
    private static final String COMPANY_KEY = "company";
    private static final String EMAILS_KEY = "emails";
    private static final String LEADS_KEY = "leads";
    private static final String PHONES_KEY = "phones";
    private static final String POSITION_KEY = "position";
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

    void setCompany(Company company) {
        fields.put(COMPANY_KEY, company);
    }

    boolean containsCompany() {
        return fields.containsKey(COMPANY_KEY);
    }

    Company getCompany() {
        return (Company) fields.get(COMPANY_KEY);
    }

    void setEmails(List<Email> emails) {
        fields.put(EMAILS_KEY, emails);
    }

    boolean containsEmails() {
        return fields.containsKey(EMAILS_KEY);
    }

    List<Email> getEmails() {
        return (List<Email>) fields.get(EMAILS_KEY);
    }

    void setFullname(Name fullname) {
        fields.put(NAME_KEY, fullname);
    }

    boolean containsFullname() {
        return fields.containsKey(NAME_KEY);
    }

    Name getFullname() {
        return (Name) fields.get(NAME_KEY);
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

    void setPosition(String position) {
        fields.put(POSITION_KEY, position);
    }

    boolean containsPosition() {
        return fields.containsKey(POSITION_KEY);
    }

    String getPosition() {
        return (String) fields.get(POSITION_KEY);
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
