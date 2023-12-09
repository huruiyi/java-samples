package com.example;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.example.web.SingerHandler;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@SpringBootApplication
public class SingerApplication {

  final SingerHandler singerHandler;

  public SingerApplication(SingerHandler singerHandler) {
    this.singerHandler = singerHandler;
  }

  @Bean
  public RouterFunction<ServerResponse> routingFunction() {
    return route(GET("/test"), serverRequest -> ok().body(fromObject("works!")))
        .andRoute(GET("/singers"), singerHandler.list)
        .andRoute(GET("/singers/{id}"), singerHandler::show)
        .andRoute(POST("/singers"), singerHandler.save).filter(
            (request, next) -> {
              log.info("Before handler invocation: " + request.path());
              return next.handle(request);
            }
        );
  }

//  @Bean
//  public ServletRegistrationBean servletRegistrationBean() throws Exception {
//    HttpHandler httpHandler = RouterFunctions.toHttpHandler(routingFunction());
//    ServletRegistrationBean registrationBean = new ServletRegistrationBean<>(new ServletHttpHandlerAdapter(httpHandler), "/");
//    registrationBean.setLoadOnStartup(1);
//    registrationBean.setAsyncSupported(true);
//    return registrationBean;
//  }

  public static void main(String... args) throws Exception {
    HashMap<String, Object> hashMap = new HashMap<>() {{
      put("server.port", "8080");
      put("spring.jpa.hibernate.ddl-auto", "create-drop");
    }};
    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SingerApplication.class).properties(hashMap).run(args);
    assert (ctx != null);
    log.info("Application started...");

    System.in.read();
    ctx.close();
  }
}
