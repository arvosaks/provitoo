package ee.rik.provitoo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="app_payment")
public class AppPayment {

    public enum AppPaymentType {
        BANK, CACHE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private AppPaymentType paymentType;

    @Column(nullable = true)
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
