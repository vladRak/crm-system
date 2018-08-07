package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.Imp.BaseClientImp;
import jcrm.pp.ua.crmsystem.entities.Imp.Company;
import jcrm.pp.ua.crmsystem.entities.Imp.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaseClientRepo extends JpaRepository<BaseClientImp,Long>{

    @Query(value = "SELECT bc FROM BaseClientImp bc " +
            "where clientType like concat('%',:search,'%') ")
    Page<BaseClientImp> search(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT bc FROM BaseClientImp bc " +
            "where clientType like 'contact'")
    Page<Contact> findAllContacts(Pageable pageable);

    @Query(value = "SELECT bc FROM BaseClientImp bc " +
            "where clientType like 'contact' and id = :id")
    Contact findOneContacts(@Param("id") long id);

    @Query(value = "SELECT bc FROM BaseClientImp bc " +
            "where clientType like 'company'")
    Page<Company> findAllCompanies(Pageable pageable);

    @Query(value = "SELECT bc FROM BaseClientImp bc " +
            "where clientType like 'company' and id = :id")
    Company findOneCompany(@Param("id") long id);

//    @Query(value = "select bc from BaseClientImp bc" +
//            "left join Contact2 as contact " +
//            "left join contact.fullName as fullName " +
//            "left join Company as company " +
//            "where lower(contact.fullName.firstname) like concat('%',:search,'%') " +
//            "or lower(contact.fullName.surname)  like concat('%',:search,'%') " +
//            "or lower(company.fullName) like concat('%',:search,'%')")
//    Page<BaseClientImp> searchByName(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT contact FROM Contact contact " +
            "left join contact.fullName as fullName " +
            "where lower(contact.fullName.firstName) like concat('%',:search,'%') " +
            "or lower(contact.fullName.surname)  like concat('%',:search,'%')")
    Page<Contact> searchContactByName(@Param("search") String search, Pageable pageable);

    @Query(value = "select company from Company company " +
            "where lower(company.fullName) like concat('%',:search,'%')")
    Page<Company> searchCompanyByName(@Param("search") String search, Pageable pageable);


}
