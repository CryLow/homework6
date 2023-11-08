package org.example;

import org.example.config.AnnotationApplicationConfig;
import org.example.controller.ControllerInterface;
import org.example.controller.impl.ControllerInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class.getPackage().getName());
        ControllerInterface controllerInterface = context.getBean(ControllerInterfaceImpl.class);
        controllerInterface.start();

    }
}
