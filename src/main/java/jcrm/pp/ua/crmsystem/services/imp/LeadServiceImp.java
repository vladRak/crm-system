package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.entities.Imp.Lead;
import jcrm.pp.ua.crmsystem.repositories.LeadRepo;
import jcrm.pp.ua.crmsystem.services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImp implements LeadService{

    @Autowired
    LeadRepo leadRepo;

    @Override
    public Page<Lead> getAllLeads(Pageable pageable) {
        List<Lead> list = (List<Lead>) leadRepo.findAll();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        Page<Lead> pages = new PageImpl(list.subList(start, end), pageable, list.size());

        return pages;
    }

    @Override
    public Lead getLeadById(long id) {
        Lead lead = leadRepo.findOne(id);
        return lead;
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
