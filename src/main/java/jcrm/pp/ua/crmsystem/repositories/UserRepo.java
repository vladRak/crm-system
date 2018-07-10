package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.Imp.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);

//    @Modifying
//    @Query(
//            //value = "INSERT INTO user (id, username, enabled) VALUES (1 ,'system', TRUE);",
//            value = "INSERT INTO base_business_obj (id, b_o_type) VALUES (1 , 'user');",
//            nativeQuery = true)
//    void addSystem();
}
