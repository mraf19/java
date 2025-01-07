package enigma.livecodesmartclass.service.Impl;

import enigma.livecodesmartclass.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    private final Validator validator;
    @Override
    public void validate(Object object){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        if(!constraintViolations.isEmpty()){
            throw new ValidationException(constraintViolations.iterator().next().getMessage(), null);
        }
    }

}
