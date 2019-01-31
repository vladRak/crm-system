package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long>{

//    @Query(value = "SELECT firstName FROM Contact c " +
//            "WHERE surname like :surname")
//    List<String> findOneEmbPhone(@Param("surname") String surname);
}
