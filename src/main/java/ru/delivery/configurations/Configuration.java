package ru.delivery.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.delivery.converters.Converter;
import ru.delivery.process.ProcessModel;

@org.springframework.context.annotation.Configuration
@ComponentScan
public class Configuration {

    @Bean
    public ProcessModel getProcessModel(){
        return new ProcessModel();
    }

    @Bean
    public Converter getConverter() {
        return new Converter();
    }
}
