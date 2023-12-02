package vip.fairy;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import vip.fairy.config.AppConfig;

public class WebApplicationInitializerSample implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {

        //org.apache.logging.slf4j.SLF4JServiceProvider
        //org.slf4j.reload4j.Reload4jServiceProvider

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setInitParameter("enableLoggingRequestDetails", "true");
        registration.setInitParameter("dispatchTraceReuest", "true");

        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }

}
