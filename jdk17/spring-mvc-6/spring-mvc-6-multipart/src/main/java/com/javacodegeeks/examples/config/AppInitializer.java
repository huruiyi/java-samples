package com.javacodegeeks.examples.config;

import java.io.File;


import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private int maxUploadSizeInMb = 1024 * 1024 * 10; // 10 MB

    private File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    private MultipartConfigElement getMultipartConfigElement() {
        String absolutePath = uploadDirectory.getAbsolutePath();
        return new MultipartConfigElement(absolutePath, maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

}
