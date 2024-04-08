package ee.rik.provitoo.dto;

import jakarta.persistence.*;

public class AppPersonDescriptionDTO {


    private Long id;

    private AppParticipantDTO appParticipant;

    private String firstName;

    private String lastName;

    private String personalCode;

    private String description;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
