package ee.rik.provitoo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="app_participant")
public class AppParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppPayment appPayment;

    @OneToOne
    private AppCompanyDescription appCompanyDescription;

    @OneToOne
    private AppPersonDescription appPersonDescription;

    private boolean isCompany;

    @ManyToOne
    @JoinColumn(name = "app_event_id")
    private AppEvent appEvent;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public AppEvent getAppEvent() {
        return appEvent;
    }

    public void setAppEvent(AppEvent appEvent) {
        this.appEvent = appEvent;
    }

    public AppCompanyDescription getAppCompanyDescription() {
        return appCompanyDescription;
    }

    public void setAppCompanyDescription(AppCompanyDescription appCompanyDescription) {
        this.appCompanyDescription = appCompanyDescription;
    }

    public AppPersonDescription getAppPersonDescription() {
        return appPersonDescription;
    }

    public void setAppPersonDescription(AppPersonDescription appPersonDescription) {
        this.appPersonDescription = appPersonDescription;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public AppPayment getAppPayment() {
        return appPayment;
    }

    public void setAppPayment(AppPayment appPayment) {
        this.appPayment = appPayment;
    }
}
