package ru.delivery.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.delivery.converters.Converter;
import ru.delivery.process.EcmProcess;
import ru.delivery.process.OadProcess;
import ru.delivery.process.ProcessModel;
import ru.delivery.process.TfsProcess;

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

    @Bean("EcmProcess")
    public EcmProcess getEcmProcess() {
        return new EcmProcess();
    }

    @Bean("TfsProcess")
    public TfsProcess getTfsProcess() {
        return new TfsProcess();
    }

    @Bean("OadProcess")
    public OadProcess getOadProcess() {
        return new OadProcess();
    }
}
