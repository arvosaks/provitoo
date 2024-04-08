package ee.rik.provitoo.repository;

import ee.rik.provitoo.entity.AppPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppPaymentRepository extends JpaRepository<AppPayment, Long>, JpaSpecificationExecutor<AppPayment> {

}