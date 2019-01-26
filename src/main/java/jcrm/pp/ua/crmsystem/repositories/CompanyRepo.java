package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
}
