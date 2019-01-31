package jcrm.pp.ua.crmsystem.domain.temp_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface TempRepo extends JpaRepository<Temp,Long> {
}