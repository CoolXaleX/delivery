package ru.sbrf.delivery.configurations;

import org.springframework.context.annotation.Bean;
import ru.sbrf.delivery.cg.RegisterTaskCgService;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RegisterTaskCgService getTask(){
        return new RegisterTaskCgService();
    }
}
