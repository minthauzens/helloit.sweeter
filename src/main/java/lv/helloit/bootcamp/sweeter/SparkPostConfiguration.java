package lv.helloit.bootcamp.sweeter;

import com.sparkpost.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.validation.Valid;

//@Configuration
public class SparkPostConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SparkPostConfiguration.class);
    @Value(value = "${lv.helloit.bootcamp.sparkpost.api.key}")
    private String api_key;

    @Bean
    @Primary
    public Client sparkPostClient() {
        LOGGER.info("Spark post client bean created!");
        return new Client(api_key);
    }
}
