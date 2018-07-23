package pl.sda.javaldz6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "pl.sda.javaldz6")
public class RestWebConfig implements WebMvcConfigurer {

        @Bean
    public RestTemplate restTemplate() {
            return new RestTemplate();
        }

}
