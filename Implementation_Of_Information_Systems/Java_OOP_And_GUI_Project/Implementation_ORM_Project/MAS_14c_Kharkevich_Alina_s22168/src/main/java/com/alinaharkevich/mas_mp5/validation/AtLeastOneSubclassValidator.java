package com.alinaharkevich.mas_mp5.validation;
import com.alinaharkevich.mas_mp5.model.Assistant;
import com.alinaharkevich.mas_mp5.model.Doctor;
import com.alinaharkevich.mas_mp5.model.Employee;
import com.alinaharkevich.mas_mp5.model.Intern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneSubclassValidator implements ConstraintValidator<AtLeastOneSubclass, Employee> {
    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        if (employee == null) {
            return false;
        }

        boolean isDoctor = employee instanceof Doctor;
        boolean isAssistant = employee instanceof Assistant;
        boolean isIntern = employee instanceof Intern;

        // Check all possible combinations
        return (isDoctor && isAssistant && isIntern) ||
                (isDoctor && isAssistant) ||
                (isDoctor && isIntern) ||
                (isAssistant && isIntern) ||
                (isDoctor || isAssistant || isIntern);
    }
}