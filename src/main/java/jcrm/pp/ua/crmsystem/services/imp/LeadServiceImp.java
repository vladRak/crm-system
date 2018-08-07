package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.entities.Imp.Lead;
import jcrm.pp.ua.crmsystem.repositories.LeadRepo;
import jcrm.pp.ua.crmsystem.services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LeadServiceImp implements LeadService{

    @Autowired
    LeadRepo leadRepo;

    @Override
    public Page<Lead> getAllLeads(Pageable pageable) {
        Page<Lead> page = leadRepo.findAll(pageable);
        return page;
    }

    @Override
    public Lead getLeadById(long id) {
        return leadRepo.findOne(id);
    }

    @Override
    public void addLead(Lead lead) {
        leadRepo.save(lead);
    }

    @Override
    public void removeLead(long id) {
        leadRepo.delete(id);
    }

    @Override
    public void updateLead(Lead lead) {
        leadRepo.save(lead);
    }
}
