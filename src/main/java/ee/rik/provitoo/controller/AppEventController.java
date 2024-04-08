package ee.rik.provitoo.controller;

import ee.rik.provitoo.dto.AppCompanyDescriptionDTO;
import ee.rik.provitoo.dto.AppEventDTO;
import ee.rik.provitoo.dto.AppParticipantDTO;
import ee.rik.provitoo.dto.AppPersonDescriptionDTO;
import ee.rik.provitoo.entity.AppParticipant;
import ee.rik.provitoo.service.AppEventService;
import ee.rik.provitoo.service.AppParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AppEventController {

    @Autowired
    private AppEventService appEventService;

    @Autowired
    private AppParticipantService appParticipantService;

    private static final String REQUEST_MAPPING_VALUE = "requestMappingValue";


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(REQUEST_MAPPING_VALUE, "/");
        model.addAttribute("tulevased", appEventService.findByStartAtAfterNow());
        model.addAttribute("toimunud",  appEventService.findByStartAtBeforeNow());
        return "home.html";
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.GET)
    public String eventGet(Model model) {
        model.addAttribute(REQUEST_MAPPING_VALUE, "/addevent");
        model.addAttribute("addevent",  new AppEventDTO());
        return "addevent.html";
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String eventPost(@ModelAttribute AppEventDTO addevent, Model model) {
        model.addAttribute(REQUEST_MAPPING_VALUE, "/addevent");
        appEventService.save(addevent);
        return index(model);
    }

    @RequestMapping(value = "/participantst/{eventid}", method = RequestMethod.GET)
    public String participantsGet(Model model , @PathVariable Long eventid) {
        model.addAttribute("addevent", appEventService.getById(eventid));
        AppParticipantDTO participant = new AppParticipantDTO();
        AppCompanyDescriptionDTO comp = new AppCompanyDescriptionDTO();
        participant.setAppCompanyDescription(comp);
        AppPersonDescriptionDTO pers = new AppPersonDescriptionDTO();
        participant.setAppPersonDescription(pers);
        model.addAttribute("participant", participant);
        return "addparticipant.html";
    }

    @RequestMapping(value = "/participant", method = RequestMethod.GET)
    public String participantGet(Model model) {
        return "home.html";
    }

    @RequestMapping(value = "/participant", method = RequestMethod.POST)
    public String participantPost(Model model) {
        return "home.html";
    }
}
