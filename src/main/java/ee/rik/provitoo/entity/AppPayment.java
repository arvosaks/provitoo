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
    @Column(name = "payment_type")
    private AppPaymentType paymentType;

    @Column(nullable = true)
    private String name;

    @OneToMany(mappedBy = "appPayment", fetch = FetchType.LAZY)
    private List<AppParticipant> appParticipant;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppPaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(AppPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<AppParticipant> getAppParticipant() {
        return appParticipant;
    }

    public void setAppParticipant(List<AppParticipant> appParticipant) {
        this.appParticipant = appParticipant;
    }

}
