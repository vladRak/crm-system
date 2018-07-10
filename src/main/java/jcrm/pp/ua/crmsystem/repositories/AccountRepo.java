package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.Imp.AccountImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<AccountImp,Long> {
}
