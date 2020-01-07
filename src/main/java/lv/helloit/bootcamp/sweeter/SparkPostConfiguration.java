package lv.helloit.bootcamp.sweeter;

import com.sparkpost.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.validation.Valid;

@Configuration
public class SparkPostConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SparkPostConfiguration.class);
String test;
    @Value(value = "${lv.helloit.bootcamp.sparkpost.api.key}")
    private String api_key;

    @Bean
    @Primary
    public Client sparkPostClient() {
        LOGGER.info("Spark post client bean created!");
        return new Client(api_key);
    }

    @Bean
    public Client vipSparkPostClient() {
        LOGGER.info("Spark post VIP USER client bean created!");
        String API_KEY = "339165f89dac945c6ad84fe63d2e8e89d505f7f8";
        return new Client(API_KEY);
    }
}
