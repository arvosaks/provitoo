package ee.rik.provitoo.service;

import ee.rik.provitoo.dto.AppCompanyDescriptionDTO;
import ee.rik.provitoo.dto.AppParticipantDTO;
import ee.rik.provitoo.dto.AppPersonDescriptionDTO;
import ee.rik.provitoo.entity.AppCompanyDescription;
import ee.rik.provitoo.entity.AppParticipant;
import ee.rik.provitoo.entity.AppPayment;
import ee.rik.provitoo.entity.AppPersonDescription;
import ee.rik.provitoo.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AppParticipantService {

    @Autowired
    private AppParticipantRepository appParticipantRepository;

    @Autowired
    private AppEventRepository appEventRepository;

    @Autowired
    private AppPaymentRepository аppPaymentRepository;

    @Autowired
    private AppCompanyDescriptionRepository appCompanyDescriptionRepository;

    @Autowired
    private  AppPersonDescriptionRepository appPersonDescriptionRepository;

    public List<AppPayment> getAppPayment(){
        return аppPaymentRepository.findAll();
    }

    public Long save(AppParticipantDTO vO) {
        AppParticipant bean = vO.getId()!=null ? appParticipantRepository.findById(vO.getId()).get() : new AppParticipant();
        copyProperties(vO, bean);
        bean = appParticipantRepository.save(bean);
        return bean.getId();
    }

    private void copyProperties(AppParticipantDTO vO, AppParticipant bean) {
        bean.setAppEvent(appEventRepository.findById(vO.getAppEvent().getId()).get());
        bean.setId(vO.getId());
        AppCompanyDescription appCompanyDescription = appCompanyDescriptionRepository
                .findById(vO.getAppCompanyDescription().getId() == null ? -1L : vO.getAppCompanyDescription().getId())
                .orElse(new AppCompanyDescription());
        AppPersonDescription appPersonDescription = appPersonDescriptionRepository
                .findById(vO.getAppPersonDescription().getId() == null ? -1L : vO.getAppPersonDescription().getId())
                .orElse(new AppPersonDescription());
        if(vO.getCompany()){
            appCompanyDescription.setCode(vO.getAppCompanyDescription().getCode());
            appCompanyDescription.setName(vO.getAppCompanyDescription().getName());
            appCompanyDescription.setParticipantsCount(vO.getAppCompanyDescription().getParticipantsCount());
            bean.setAppPersonDescription(null);
            appPersonDescriptionRepository.delete(appPersonDescription);
            bean.setAppPayment(createPayment(vO.getAppPaymentCompany()));
            bean.setAppCompanyDescription(appCompanyDescription);
            bean.setCompany(true);
        }else{
            appPersonDescription.setFirstName(vO.getAppPersonDescription().getFirstName());
            appPersonDescription.setLastName(vO.getAppPersonDescription().getLastName());
            appPersonDescription.setPersonalCode(vO.getAppPersonDescription().getPersonalCode());
            appPersonDescription.setDescription(vO.getAppPersonDescription().getDescription());
            bean.setAppCompanyDescription(null);
            appCompanyDescriptionRepository.delete(appCompanyDescription);
            bean.setAppPayment(createPayment(vO.getAppPaymentPerson()));
            bean.setAppPersonDescription(appPersonDescription);
            bean.setCompany(false);
        }
    }

    public void copyProperties(AppParticipant original, AppParticipantDTO bean) {
        bean.setId(original.getId());
        bean.setCompany(original.getCompany());
        bean.setAppPaymentCompany(original.getAppPayment().getPaymentType());
        bean.setAppPaymentPerson(original.getAppPayment().getPaymentType());
        if(original.getAppCompanyDescription()!=null){
            AppCompanyDescriptionDTO appCompanyDescriptionDTO = new AppCompanyDescriptionDTO();
            BeanUtils.copyProperties(original.getAppCompanyDescription() , appCompanyDescriptionDTO);
            bean.setAppCompanyDescription(appCompanyDescriptionDTO);
        }
        if(original.getAppPersonDescription()!=null){
            AppPersonDescriptionDTO appPersonDescriptionDTO = new AppPersonDescriptionDTO();
            BeanUtils.copyProperties(original.getAppPersonDescription() , appPersonDescriptionDTO);
            bean.setAppPersonDescription(appPersonDescriptionDTO);
        }
    }

    public void delete(Long id) {
        Optional<AppParticipant> appParticipant = appParticipantRepository.findById(id);
        if(!appParticipant.isPresent())
            return;
        AppParticipant participant = appParticipant.get();
        if(participant.getAppPersonDescription() != null){
            participant.setAppCompanyDescription(null);
            appPersonDescriptionRepository.delete(participant.getAppPersonDescription());
        }
        if(participant.getAppCompanyDescription() != null){
            participant.setAppCompanyDescription(null);
            appCompanyDescriptionRepository.delete(participant.getAppCompanyDescription() );
        }
        appParticipantRepository.delete(participant);
    }

    public void update(Long id, AppParticipantDTO vO) {
        AppParticipant bean = requireOne(id);
        copyProperties(vO, bean);
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
        copyProperties(original, bean);
        return bean;
    }



    private AppParticipant requireOne(Long id) {
        return appParticipantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<AppParticipant> getByEventId(Long eventid) {
        return appParticipantRepository.findByAppEventId(eventid);
    }
    private AppPayment createPayment(AppPayment.AppPaymentType appPaymentCompany){
        AppPayment payment = new AppPayment();
        payment.setPaymentType(appPaymentCompany);
        return аppPaymentRepository.findOne(Example.of(payment)).get();
    }
}
