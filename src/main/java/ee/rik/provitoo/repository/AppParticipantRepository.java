package ee.rik.provitoo.repository;

import ee.rik.provitoo.entity.AppParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AppParticipantRepository extends JpaRepository<AppParticipant, Long>, JpaSpecificationExecutor<AppParticipant> {

    List<AppParticipant> findByAppEventId(Long eventid);
}
