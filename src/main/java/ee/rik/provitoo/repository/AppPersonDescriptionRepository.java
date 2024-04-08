package ee.rik.provitoo.repository;

import ee.rik.provitoo.entity.AppPersonDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppPersonDescriptionRepository extends JpaRepository<AppPersonDescription, Long>, JpaSpecificationExecutor<AppPersonDescription> {

}