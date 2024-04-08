package ee.rik.provitoo.service;

import ee.rik.provitoo.dto.AppEventDTO;
import ee.rik.provitoo.entity.AppEvent;
import ee.rik.provitoo.repository.AppEventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppEventService {

    @Autowired
    private AppEventRepository appEventRepository;

    public Long save(AppEventDTO vO) {
        AppEvent bean = new AppEvent();
        BeanUtils.copyProperties(vO, bean);
        bean = appEventRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        appEventRepository.deleteById(id);
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