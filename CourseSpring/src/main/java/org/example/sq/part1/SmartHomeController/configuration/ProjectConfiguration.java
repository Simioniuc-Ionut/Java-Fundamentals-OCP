package org.example.sq.part1.SmartHomeController.configuration;

import org.example.sq.part1.SmartHomeController.aop.audit.LoggingAspect;
import org.example.sq.part1.SmartHomeController.repository.lightRepository.LightInMemoryRepository;
import org.example.sq.part1.SmartHomeController.repository.lightRepository.LightRepository;
import org.example.sq.part1.SmartHomeController.services.lightning.LightControlServiceImpl;
import org.springframework.aop.aspectj.annotation.PrototypeAspectInstanceFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@ComponentScan(basePackages = "org.example.sq.part1.SmartHomeController")
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "org.example.sq.part1.SmartHomeController")
public class ProjectConfiguration {

    @Bean
    @Lazy
    public LightRepository lightRepository(){
        return new LightInMemoryRepository();
    }

    @Bean
    public LoggingAspect logging(){
        return new LoggingAspect();
    }

    @Bean
    @Scope("prototype")
    public LightControlServiceImpl lightControlServiceImpl(){
        return new LightControlServiceImpl(lightRepository());
    }


}
