package ee.rik.provitoo.dto;

import jakarta.persistence.*;

public class AppCompanyDescriptionDTO {

    private Long id;


    private AppParticipantDTO appParticipant;

    private String name;

    private String code;

    private Integer participantsCount;

    private String companyDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppParticipantDTO getAppParticipant() {
        return appParticipant;
    }

    public void setAppParticipant(AppParticipantDTO appParticipant) {
        this.appParticipant = appParticipant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(Integer participantsCount) {
        this.participantsCount = participantsCount;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
