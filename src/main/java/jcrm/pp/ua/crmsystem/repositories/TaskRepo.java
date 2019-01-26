package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface TaskRepo extends JpaRepository<Task,Long> {
}
