package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.domain.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PrivilegeRepo extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);
}
