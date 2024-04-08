package ee.rik.provitoo.repository;

import ee.rik.provitoo.entity.AppEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface AppEventRepository extends JpaRepository<AppEvent, Long>, JpaSpecificationExecutor<AppEvent> {

    List<AppEvent> findByStartAtAfter(Date date);
    List<AppEvent> findByStartAtBefore(Date date);
}
