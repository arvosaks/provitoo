package ee.rik.provitoo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="app_company_description")
public class AppCompanyDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AppParticipant appParticipant;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private Integer participantsCount;

    @Column(length = 5000)
    private String companyDescription;

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
