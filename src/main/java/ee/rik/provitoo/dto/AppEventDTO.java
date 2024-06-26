package ee.rik.provitoo.dto;

import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppEventDTO {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startAt;

    private String place;

    @Size(min = 0, max = 1000 , message="Ürituse lisainfo: Lubatud maksimaalselt 1000 tähemärki")
    private String description;

    private List<AppParticipantDTO> userList = new ArrayList<>();

    public AppEventDTO(Long id) {
        this.id = id;
    }

    public AppEventDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AppParticipantDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<AppParticipantDTO> userList) {
        this.userList = userList;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }
}
