package vip.fairy.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import vip.fairy.config.DataServiceConfig;

/**
 * Created by iuliana.cosmina on 10/16/16.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataServiceConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}