package arc.validator.spring;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;
import java.nio.charset.StandardCharsets;

/**
 * Created by Arc on 20/10/2016.
 */
@Configuration
@ComponentScan("arc.validator")
@EnableAspectJAutoProxy
public class AppConfig {
//    @Bean
//    public BeanValidationPostProcessor beanValidationPostProcessor(){
//        BeanValidationPostProcessor beanValidationPostProcessor = new BeanValidationPostProcessor();
//        return beanValidationPostProcessor;
//    }
    @Bean
    @Autowired
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator){
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator);
        return methodValidationPostProcessor;
    }

    @Bean
    @Autowired
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        return localValidatorFactoryBean;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames(
                "classpath:i18n/validation"
        );
        return messageSource;
    }
}
