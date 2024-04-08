package ee.rik.provitoo.service;

import ee.rik.provitoo.dto.AppParticipantDTO;
import ee.rik.provitoo.entity.AppParticipant;
import ee.rik.provitoo.repository.AppParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppParticipantService {

    @Autowired
    private AppParticipantRepository appParticipantRepository;

    public Long save(AppParticipantDTO vO) {
        AppParticipant bean = new AppParticipant();
        BeanUtils.copyProperties(vO, bean);
        bean = appParticipantRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        appParticipantRepository.deleteById(id);
    }

    public void update(Long id, AppParticipantDTO vO) {
        AppParticipant bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        appParticipantRepository.save(bean);
    }

    public AppParticipantDTO getById(Long id) {
        AppParticipant original = requireOne(id);
        return toDTO(original);
    }

    public Page<AppParticipantDTO> query(AppParticipantDTO vO) {
        throw new UnsupportedOperationException();
    }

    private AppParticipantDTO toDTO(AppParticipant original) {
        AppParticipantDTO bean = new AppParticipantDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private AppParticipant requireOne(Long id) {
        return appParticipantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<AppParticipant> getByEventId(Long eventid) {
        return appParticipantRepository.findByAppEventId(eventid);
    }

}
