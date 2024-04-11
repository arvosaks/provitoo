package ee.rik.provitoo.controller;

import ee.rik.provitoo.dto.AppCompanyDescriptionDTO;
import ee.rik.provitoo.dto.AppEventDTO;
import ee.rik.provitoo.dto.AppParticipantDTO;
import ee.rik.provitoo.dto.AppPersonDescriptionDTO;
import ee.rik.provitoo.entity.AppPayment;
import ee.rik.provitoo.service.AppEventService;
import ee.rik.provitoo.service.AppParticipantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = "/participantst/{eventid}/{participantId}", method = RequestMethod.GET)
    public String participantsUpdate(Model model  , @ModelAttribute("participant") AppParticipantDTO participant , @PathVariable Long eventid , @PathVariable Long participantId , BindingResult bindingResult ) {
        participant = appParticipantService.getById(participantId);
        return participantsGet( model , participant, bindingResult , eventid );
    }

    @RequestMapping(value = "/participantst/{eventid}", method = RequestMethod.GET)
    public String participantsGet(Model model , @ModelAttribute("participant") AppParticipantDTO participant , BindingResult bindingResult , @PathVariable Long eventid ) {
        AppEventDTO addevent = appEventService.getById(eventid);
        model.addAttribute("addevent", addevent);
        List<AppPayment> payments = appParticipantService.getAppPayment();
        model.addAttribute("payments", payments);
        model.addAttribute("participant", participant);
        return "addparticipant.html";
    }

    @RequestMapping(value = "/participantst/{eventid}", method = RequestMethod.POST)
    public String participantsPost( Model model , @Valid @ModelAttribute("participant") AppParticipantDTO participant, BindingResult bindingResult , @PathVariable Long eventid ) {
        if (!bindingResult.hasErrors()) {
            participant.setAppEvent(new AppEventDTO(eventid));
            appParticipantService.save(participant);
            fillEmpty( participant);
        }
        return participantsGet( model , participant , bindingResult,   eventid );
    }

    @RequestMapping(value = "/participantst/{eventid}/{participantId}/delete", method = RequestMethod.GET)
    public String participantDelete(Model model, @ModelAttribute("participant") AppParticipantDTO participant, @PathVariable Long eventid , @PathVariable Long participantId , BindingResult bindingResult ) {
        appParticipantService.delete(participantId);
        return participantsGet( model , new AppParticipantDTO() , bindingResult,   eventid );
    }

    @RequestMapping(value = "/event/delete/{eventid}", method = RequestMethod.GET)
    public String participantPost(Model model, @PathVariable Long eventid) {
        appEventService.delete(eventid);
        return "home.html";
    }

    private void fillEmpty(AppParticipantDTO participant){
        List<AppPayment> payments = appParticipantService.getAppPayment();
        participant.setId(null);
        participant.setCompany(true);
        participant.setAppPaymentCompany(payments.getFirst().getPaymentType());
        participant.setAppPaymentPerson(payments.getLast().getPaymentType());
        AppCompanyDescriptionDTO comp = new AppCompanyDescriptionDTO();
        participant.setAppCompanyDescription(comp);
        AppPersonDescriptionDTO pers = new AppPersonDescriptionDTO();
        participant.setAppPersonDescription(pers);
    }
}
