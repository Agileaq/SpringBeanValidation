package arc.validator.test;

import arc.validator.bean.User;
import arc.validator.service.UserService;
import arc.validator.spring.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Arc on 20/10/2016.
 */
@Test
@ContextConfiguration(classes = AppConfig.class, loader=AnnotationConfigContextLoader.class)
public class ValidatorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @Test
    public void testInsert(){
        LocaleContextHolder.setLocale(new Locale("zh", "CN"));
        try {
            userService.insert(null, "aaaa", "11111111");
        } catch(ConstraintViolationException constraintViolationException) {
            constraintViolationException.printStackTrace();
        }
        System.out.println("testInsert done");
    }

    @Test
    public void testCustomize(){
        try {
            userService.insert("test11name","aaa@aaaa.com","13266542325");
        } catch(ConstraintViolationException constraintViolationException) {
            constraintViolationException.printStackTrace();
        }
        System.out.println("testInsert done");
    }

    @Test
    public void testGet(){
        LocaleContextHolder.setLocale(new Locale("zh", "CN"));
        try {
            User user = userService.getUser();
        } catch(ConstraintViolationException constraintViolationException) {
            ConstraintViolation constraintViolation = constraintViolationException.getConstraintViolations().iterator().next();
            String msgTemplate = constraintViolation.getMessageTemplate();
            String msg = constraintViolation.getMessage();
            constraintViolationException.printStackTrace();
        }
        System.out.println("testGet done");
    }

    @Test
    public void testChangeLocale(){
        LocaleContextHolder.setLocale(new Locale("zh", "CN"));
        ConstraintViolationException constraintViolationException1 = null;
        ConstraintViolationException constraintViolationException2 = null;
        try {
            List<User> users = userService.getUserByName("test11name");
        } catch(ConstraintViolationException constraintViolationException) {
            constraintViolationException1 = constraintViolationException;
        }

        LocaleContextHolder.setLocale(new Locale("en", "US"));
        try {
            List<User> users = userService.getUserByName("test11name");
        } catch(ConstraintViolationException constraintViolationException) {
            constraintViolationException2 = constraintViolationException;
        }
        System.out.println("testChangeLocale done" + constraintViolationException1 + constraintViolationException2);
    }

    @Test
    public void testMessageSource(){
        String msg = messageSource.getMessage("validate.user.firstName",null, Locale.getDefault());
        System.out.println("testMessageSource done ---> " + msg);

        String msg1 = messageSource.getMessage("validate.user.firstName",null, null);
        System.out.println("testMessageSource done ---> " + msg1);

        String msg2 = messageSource.getMessage("validate.user.firstName",null, new Locale("zh", "CN"));
        System.out.println("testMessageSource done ---> " + msg2);
    }

}
