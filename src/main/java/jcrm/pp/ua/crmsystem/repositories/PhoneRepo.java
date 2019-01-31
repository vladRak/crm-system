package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.domain.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface PhoneRepo extends JpaRepository<Phone,Long> {
}
