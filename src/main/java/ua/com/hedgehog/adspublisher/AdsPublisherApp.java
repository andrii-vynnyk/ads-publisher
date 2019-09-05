package ua.com.hedgehog.adspublisher;

import com.google.common.collect.Sets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import ua.com.hedgehog.adspublisher.rest.AdRequestConverter;

@SpringBootApplication
public class AdsPublisherApp {

    public static void main(String[] args) {
        SpringApplication.run(AdsPublisherApp.class, args);
    }

    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(Sets.newHashSet(new AdRequestConverter()));
        bean.afterPropertiesSet();
        return bean.getObject();
    }
}
