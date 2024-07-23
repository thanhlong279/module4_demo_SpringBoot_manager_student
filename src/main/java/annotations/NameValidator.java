package annotations;

import com.example.demo_spring_boot.repositories.IStudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameValidator implements ConstraintValidator<NameValid, String> {

    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

        return false;
    }
}
