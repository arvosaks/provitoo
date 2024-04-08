package ee.rik.provitoo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="app_person_description")
public class AppPersonDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppParticipant appParticipant;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String personalCode;

    @Column(length = 1500)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppParticipant getAppParticipant() {
        return appParticipant;
    }

    public void setAppParticipant(AppParticipant appParticipant) {
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
