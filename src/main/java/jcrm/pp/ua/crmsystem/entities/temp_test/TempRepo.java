package jcrm.pp.ua.crmsystem.entities.temp_test;

import jcrm.pp.ua.crmsystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface TempRepo extends JpaRepository<Temp,Long> {
}