package ee.rik.provitoo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="app_participant")
public class AppParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppPayment appPayment;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Company_Description_id" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppCompanyDescription appCompanyDescription;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Person_Description_id" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppPersonDescription appPersonDescription;

    private boolean company;

    @ManyToOne(cascade=CascadeType.DETACH)
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

    public boolean getCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public AppPayment getAppPayment() {
        return appPayment;
    }

    public void setAppPayment(AppPayment appPayment) {
        this.appPayment = appPayment;
    }
}
