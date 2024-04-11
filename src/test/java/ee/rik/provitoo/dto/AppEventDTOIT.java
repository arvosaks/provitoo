package ee.rik.provitoo.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.util.StringUtils;

import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 * 
 */
public class AppEventDTOIT {
    
    public AppEventDTOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMaxExceed(){
        AppEventDTO appEventDTO = new AppEventDTO();
        appEventDTO.setDescription(StringUtils.repeat("*", 1001));
        Set<ConstraintViolation<AppEventDTO>> result = validator.validate(appEventDTO);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testMaxright(){
        AppEventDTO appEventDTO = new AppEventDTO();
        appEventDTO.setDescription(StringUtils.repeat("*", 999));
        Set<ConstraintViolation<AppEventDTO>> result = validator.validate(appEventDTO);
        assertTrue(result.isEmpty());
    }
    
}
