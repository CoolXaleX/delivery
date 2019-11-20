package ru.delivery.configurations;

import org.springframework.context.annotation.Bean;
import ru.delivery.process.ProcessModel;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ProcessModel getProcessModel(){
        return new ProcessModel();
    }
}
