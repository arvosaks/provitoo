package ee.rik.provitoo.dto;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AppEventDTO {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private Date startAt;

    private String place;

    private String description;

    private List<AppParticipantDTO> userList;

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
