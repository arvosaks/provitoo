package ee.rik.provitoo.dto;

import ee.rik.provitoo.entity.AppPayment;

public class AppPaymentDTO {

    private Long id;

    private AppPayment.AppPaymentType paymentType;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
