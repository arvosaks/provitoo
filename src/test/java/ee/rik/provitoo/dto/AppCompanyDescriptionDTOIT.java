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
import org.thymeleaf.util.StringUtils;

import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 * 
 */
public class AppCompanyDescriptionDTOIT {
    
    public AppCompanyDescriptionDTOIT() {
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
        AppCompanyDescriptionDTO appCompanyDescriptionDTO = new AppCompanyDescriptionDTO();
        appCompanyDescriptionDTO.setCompanyDescription(StringUtils.repeat("*", 5001));
        Set<ConstraintViolation<AppCompanyDescriptionDTO>> result = validator.validate(appCompanyDescriptionDTO);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testMaxRight(){
        AppCompanyDescriptionDTO appCompanyDescriptionDTO = new AppCompanyDescriptionDTO();
        appCompanyDescriptionDTO.setCompanyDescription(StringUtils.repeat("*", 4999));
        Set<ConstraintViolation<AppCompanyDescriptionDTO>> result = validator.validate(appCompanyDescriptionDTO);
        assertTrue(result.isEmpty());
    }
    
}
