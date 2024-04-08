package ee.rik.provitoo.dto;

import jakarta.persistence.*;


public class AppParticipantDTO {

    private Long id;

    private AppPaymentDTO appPayment;


    private AppCompanyDescriptionDTO appCompanyDescription;

    private AppPersonDescriptionDTO appPersonDescription;

    private boolean isCompany;

    private AppEventDTO appEvent;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public AppEventDTO getAppEvent() {
        return appEvent;
    }

    public void setAppEvent(AppEventDTO appEvent) {
        this.appEvent = appEvent;
    }

    public AppCompanyDescriptionDTO getAppCompanyDescription() {
        return appCompanyDescription;
    }

    public void setAppCompanyDescription(AppCompanyDescriptionDTO appCompanyDescription) {
        this.appCompanyDescription = appCompanyDescription;
    }

    public AppPersonDescriptionDTO getAppPersonDescription() {
        return appPersonDescription;
    }

    public void setAppPersonDescription(AppPersonDescriptionDTO appPersonDescription) {
        this.appPersonDescription = appPersonDescription;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public AppPaymentDTO getAppPayment() {
        return appPayment;
    }

    public void setAppPayment(AppPaymentDTO appPayment) {
        this.appPayment = appPayment;
    }
}
