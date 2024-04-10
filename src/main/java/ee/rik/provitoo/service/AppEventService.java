package ee.rik.provitoo.service;

import ee.rik.provitoo.dto.AppCompanyDescriptionDTO;
import ee.rik.provitoo.dto.AppEventDTO;
import ee.rik.provitoo.dto.AppParticipantDTO;
import ee.rik.provitoo.dto.AppPersonDescriptionDTO;
import ee.rik.provitoo.entity.AppEvent;
import ee.rik.provitoo.entity.AppParticipant;
import ee.rik.provitoo.repository.AppEventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AppEventService {

    @Autowired
    private AppEventRepository appEventRepository;

    @Autowired
    private AppParticipantService appParticipantService;

    public Long save(AppEventDTO vO) {
        AppEvent bean = new AppEvent();
        BeanUtils.copyProperties(vO, bean);
        bean = appEventRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        Optional<AppEvent> appEvent = appEventRepository.findById(id);
        if(!appEvent.isPresent())
            return;
        AppEvent event = appEvent.get();
        for( AppParticipant user : event.getUserList()){
            user.setAppEvent(null);
        }
        appEventRepository.delete(event);
    }

    public void update(Long id, AppEventDTO vO) {
        AppEvent bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        appEventRepository.save(bean);
    }

    public AppEventDTO getById(Long id) {
        AppEvent original = requireOne(id);
        return toDTO(original);
    }

    public Page<AppEventDTO> query(AppEventDTO vO) {
        throw new UnsupportedOperationException();
    }

    private AppEventDTO toDTO(AppEvent original) {
        AppEventDTO bean = new AppEventDTO();
        BeanUtils.copyProperties(original, bean);
        for (AppParticipant user : original.getUserList()){
            AppParticipantDTO appParticipantDTO = new AppParticipantDTO();
            appParticipantService.copyProperties(user , appParticipantDTO);
            bean.getUserList().add(appParticipantDTO);
        }
        return bean;
    }

    private AppEvent requireOne(Long id) {
        return appEventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<AppEvent> findByStartAtAfterNow() {
        return appEventRepository.findByStartAtAfter(new Date());
    }

    public List<AppEvent> findByStartAtBeforeNow() {
        return appEventRepository.findByStartAtBefore(new Date());
    }

}
