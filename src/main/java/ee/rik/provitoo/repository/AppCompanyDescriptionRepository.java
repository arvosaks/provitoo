package ee.rik.provitoo.repository;

import ee.rik.provitoo.entity.AppCompanyDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppCompanyDescriptionRepository extends JpaRepository<AppCompanyDescription, Long>, JpaSpecificationExecutor<AppCompanyDescription> {

}