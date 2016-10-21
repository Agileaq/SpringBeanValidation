package arc.validator.customize;

import arc.validator.bean.User;
import arc.validator.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Created by Arc on 21/10/2016.
 */
public class NameDuplicatedValidator implements ConstraintValidator<NameDuplicated, CharSequence> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void initialize(NameDuplicated constraintAnnotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        List<User> users = userMapper.getUserByName(String.valueOf(value));
        if(CollectionUtils.isEmpty(users)) {
            return true;
        } else {
            return false;
        }
    }
}
