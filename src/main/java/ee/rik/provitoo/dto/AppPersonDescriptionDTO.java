package ee.rik.provitoo.dto;

import ee.rik.provitoo.utils.PersonalCodeValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

public class AppPersonDescriptionDTO {

    private Long id;

    private AppParticipantDTO appParticipant;

    private String firstName;

    private String lastName;

    @PersonalCodeValidation()
    private String personalCode;

    @Size(min = 0, max = 1500 , message="Eraisik lisainfo: Lubatud maksimaalselt 1500 tähemärki")
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
