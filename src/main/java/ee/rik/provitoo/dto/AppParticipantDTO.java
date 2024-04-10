package ee.rik.provitoo.dto;

import ee.rik.provitoo.entity.AppPayment.AppPaymentType;
import jakarta.validation.Valid;


public class AppParticipantDTO {

    private Long id;

    private AppPaymentType appPaymentCompany;

    private AppPaymentType appPaymentPerson;


    @Valid
    private AppCompanyDescriptionDTO appCompanyDescription;

    @Valid
    private AppPersonDescriptionDTO appPersonDescription;

    private boolean company;

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

    public boolean getCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public AppPaymentType getAppPaymentPerson() {
        return appPaymentPerson;
    }

    public void setAppPaymentPerson(AppPaymentType appPaymentPerson) {
        this.appPaymentPerson = appPaymentPerson;
    }

    public AppPaymentType getAppPaymentCompany() {
        return appPaymentCompany;
    }

    public void setAppPaymentCompany(AppPaymentType appPaymentCompany) {
        this.appPaymentCompany = appPaymentCompany;
    }
}
