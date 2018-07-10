package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.Imp.BaseBusinessObjImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseBusinessObjRepo extends JpaRepository<BaseBusinessObjImp,Long> {
}
