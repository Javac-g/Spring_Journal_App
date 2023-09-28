package com.denisov.journal.Configuration;

import com.denisov.journal.Controllers.Controller;
import com.denisov.journal.Services.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "com.denisov.journal.Controllers")
public class SpringConfigurationFactory {

    @Bean(name = "Controller")
   
    public Controller controllerBean (){
        Controller controller = new Controller();
        controller.setService(serviceBean());
        return controller;
    }

    @Bean(name = "Service")
    public Service serviceBean(){
        return new Service();
    }
}
