package ee.rik.provitoo.controller;

import ee.rik.provitoo.dto.AppParticipantDTO;
import ee.rik.provitoo.service.AppParticipantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/appParticipant")
public class AppParticipantController {

    @Autowired
    private AppParticipantService appParticipantService;

    @PostMapping
    public String save(@Valid @RequestBody AppParticipantDTO vO) {
        return appParticipantService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        appParticipantService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody AppParticipantDTO vO) {
        appParticipantService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AppParticipantDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return appParticipantService.getById(id);
    }

    @GetMapping
    public Page<AppParticipantDTO> query(@Valid AppParticipantDTO vO) {
        return appParticipantService.query(vO);
    }
}
