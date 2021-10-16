package hnt.com.repository;

import hnt.com.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long> {

    List<Log> findAll();

    @Transactional(rollbackFor = Exception.class)
    default void clear(EntityManager entityManager) {
        // 1. Clean table
        this.deleteAll();

        // 2. reset mysql sequence
        String query = "ALTER TABLE log AUTO_INCREMENT = 1;";

        javax.persistence.Query sqlQuery = entityManager.createNativeQuery(query);
        sqlQuery.executeUpdate();
    }
}
