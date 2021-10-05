package top.mothership.cb.msg.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;

public class CbValidateUtil {
    private static final SpringValidatorAdapter validator = new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());

    public static void doSpringValidate(Object data){
        Errors errors = new BeanPropertyBindingResult(data, data.getClass().getName());
        validator.validate(data,errors);
        if (CollectionUtils.isEmpty(errors.getAllErrors())){
            return;
        }
        throw new RuntimeException(errors.getAllErrors().get(0).getDefaultMessage());

    }
}
