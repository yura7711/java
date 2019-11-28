package com.geekbrains.training.lesson11;

import com.geekbrains.training.lesson11.entities.Task;
import com.geekbrains.training.lesson11.services.TaskService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8189);

        ProtectionDomain domain = MainApp.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app");
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}
