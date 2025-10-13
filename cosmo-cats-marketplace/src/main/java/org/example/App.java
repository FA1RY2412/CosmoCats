package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication(scanBasePackages = "org.example")
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  // друк усіх замаплених ендпоінтів при старті
  @Bean
  CommandLineRunner printRoutes(RequestMappingHandlerMapping mapping) {
    return args -> mapping.getHandlerMethods().forEach((info, method) ->
        System.out.println(">> " + info));
  }
}
