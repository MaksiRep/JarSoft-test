package ru.maksirep.jarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksirep.jarsoft.model.Logs;

import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Integer> {

    List<Logs> findAllByUserAgentAndIpAddress (String userAgent, String ip);

}
