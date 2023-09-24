package com.denisov.journal.Configuration;

import com.denisov.journal.Controllers.Controller;
import com.denisov.journal.Services.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SpringConfigurationFactory {

    @Bean(name = "Controller")
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
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
