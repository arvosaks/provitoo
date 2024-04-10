package ee.rik.provitoo.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonalCodeValidator implements ConstraintValidator<PersonalCodeValidation, String> {

    @Override
    public void initialize(PersonalCodeValidation personalCodeValidation) {
    }

    private static final String PERSONAL_CODE_REGEX =
            "^[1-6]\\d{2}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|1\\d|2[0-9])))\\d{4}$";

    @Override
    public boolean isValid(String personalCode, ConstraintValidatorContext context) {
        return personalCode == null || personalCode.isEmpty() || (personalCode.matches(PERSONAL_CODE_REGEX) && isControlNumberValid(personalCode));
    }

    static boolean isControlNumberValid(String personalCode) {
        return Character.getNumericValue(personalCode.charAt(personalCode.length() - 1))
                == calculateControlNumber(personalCode);
    }

    private static int calculateControlNumber(String personalCode) {
        String[] numberArray = personalCode.substring(0, 10).split("");
        List<Integer> numberList = Arrays.stream(numberArray)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        int sum = 0;
        int[] multipliers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        for (int i = 0; i < numberList.size(); i++) {
            sum += numberList.get(i) * multipliers[i];
        }
        int parsedControlNumber = sum % 11;
        if (parsedControlNumber == 10) {
            sum = 0;
            multipliers = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            for (int i = 0; i < numberList.size(); i++) {
                sum += numberList.get(i) * multipliers[i];
            }
            parsedControlNumber = sum % 11;
            if (parsedControlNumber == 10) {
                return 0;
            }
        }
        return parsedControlNumber;
    }
}
