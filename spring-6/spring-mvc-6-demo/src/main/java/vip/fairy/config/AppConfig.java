package vip.fairy.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vip.fairy.model.Person;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"vip.fairy"})
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //Log4jConfigListener
        //Log4jServletContextListener

        //APPLICATION_JSON_UTF8
        //<value>application/json;charset=UTF-8</value>
        //<value>text/plain;charset=UTF-8</value>
        //<value>text/html;charset=UTF-8</value>
        //<value>text/json;charset=UTF-8</value>
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/app/hello");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Charset.forName("UTF-8")
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
    }

    @Bean
    public Person person() {
        return new Person(1L, "张三", 18, "北京");
    }

//    @Bean
//    public CharacterEncodingFilter characterEncodingFilter() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        String encoding = "UTF-8";
//        characterEncodingFilter.setEncoding(encoding);
//        characterEncodingFilter.setForceEncoding(true);
//        characterEncodingFilter.setForceRequestEncoding(true);
//        characterEncodingFilter.setForceResponseEncoding(true);
//        return characterEncodingFilter;
//    }

//    @Bean
//    StringHttpMessageConverter stringHttpMessageConverter() {
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
//        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
//        stringHttpMessageConverter.setSupportedMediaTypes(List.of(APPLICATION_JSON_UTF8));
//        return stringHttpMessageConverter;
//    }

}
