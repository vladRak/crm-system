//package jcrm.pp.ua.crmsystem.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
//import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
//import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//@Configuration
//public class RepositoryConfig extends RepositoryRestMvcConfiguration {
//
//    @Override
//    @Bean
//    public HateoasPageableHandlerMethodArgumentResolver pageableResolver() {
//        HateoasPageableHandlerMethodArgumentResolver resolver = super.pageableResolver();
//        resolver.setOneIndexedParameters(true);
//        resolver.setMaxPageSize(100);
//        return resolver;
//    }
//}
//
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowCredentials(false)
////                .allowedOrigins("*")
////                .maxAge(3600)
////                .allowedMethods("PUT", "POST", "GET", "OPTIONS", "DELETE")
////                .allowedHeaders("Access-Control-Allow-Origin");
////                //.exposedHeaders("Authorization", "Content-Type");
////    }
//}
