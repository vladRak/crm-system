package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.entities.impl.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeadService {
    Page<Lead> getAllLeads(Pageable pageable);
    Lead getLeadById(long id);
    void addLead(Lead lead);
    void removeLead(long id);
    void updateLead(Lead lead);
}
