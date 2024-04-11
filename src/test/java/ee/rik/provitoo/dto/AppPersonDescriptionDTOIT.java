package ee.rik.provitoo.dto;

import ee.rik.provitoo.utils.PersonalCodeValidation;
import ee.rik.provitoo.utils.PersonalCodeValidator;
import jakarta.validation.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.thymeleaf.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

/**
 *
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AppPersonDescriptionDTOIT {
    
    public AppPersonDescriptionDTOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    private Validator validator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private PersonalCodeValidator personalCodeValidator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        doCallRealMethod().when(personalCodeValidator).initialize(any());
        when(personalCodeValidator.isValid(any(), any())).thenCallRealMethod();
        ValidatorTestClass validatorTestClass = new ValidatorTestClass();
        personalCodeValidator.initialize(validatorTestClass);
    }


    @After
    public void tearDown() {
    }

    @Test
    public void testMaxExceed(){
        AppPersonDescriptionDTO appPersonDescriptionDTO = new AppPersonDescriptionDTO();
        appPersonDescriptionDTO.setDescription(StringUtils.repeat("*", 1501));
        Set<ConstraintViolation<AppPersonDescriptionDTO>> result = validator.validate(appPersonDescriptionDTO);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testMaxRight(){
        AppPersonDescriptionDTO appPersonDescriptionDTO = new AppPersonDescriptionDTO();
        appPersonDescriptionDTO.setDescription(StringUtils.repeat("*", 1499));
        Set<ConstraintViolation<AppPersonDescriptionDTO>> result = validator.validate(appPersonDescriptionDTO);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUserCodeWrong(){
        AppPersonDescriptionDTO appPersonDescriptionDTO = new AppPersonDescriptionDTO();
        appPersonDescriptionDTO.setPersonalCode(StringUtils.repeat("*", 14));
        boolean result = personalCodeValidator.isValid(appPersonDescriptionDTO.getPersonalCode() , constraintValidatorContext);
        assertFalse(result);
    }

    @Test
    public void testUserCodeRight(){
        AppPersonDescriptionDTO appPersonDescriptionDTO = new AppPersonDescriptionDTO();
        appPersonDescriptionDTO.setPersonalCode("48808115234");
        boolean result = personalCodeValidator.isValid(appPersonDescriptionDTO.getPersonalCode() , constraintValidatorContext);
        assertTrue(result);
    }
    private class ValidatorTestClass implements PersonalCodeValidation {

        @Override
        public String message() {
            return null;
        }

        @Override
        public Class<?>[] groups() {
            return new Class[0];
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[0];
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }
    }
}
