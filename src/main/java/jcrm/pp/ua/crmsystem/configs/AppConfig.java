package jcrm.pp.ua.crmsystem.configs;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import jcrm.pp.ua.crmsystem.customClasses.PageImplBean;
import jcrm.pp.ua.crmsystem.customClasses.registration.InitialDataLoader;
import jcrm.pp.ua.crmsystem.domain.entity.User;
import jcrm.pp.ua.crmsystem.json.serializers.PageSerializer;
import jcrm.pp.ua.crmsystem.listeners.event.RootAwareEventListenerIntegrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
@PropertySources({
        @PropertySource(value = {"classpath:config.properties"}),
//        @PropertySource(value = {"file:${user.home}/crm/conf/config.properties"}, ignoreResourceNotFound = true),
//        @PropertySource(value = {"file:${user.home}/crm/conf/application.properties"}, ignoreResourceNotFound=true)
})
@EnableTransactionManagement
//@EnableJpaRepositories
@EnableWebMvc
@EnableJpaAuditing
public class AppConfig extends WebMvcConfigurerAdapter {
//    @Value("${hibernate.dialect}")
//    private String sqlDialect;
//
//    @Value("${hbm2ddl.auto}")
//    private String hbm2dllAuto;

    @Autowired
    Environment environment;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
            (DataSource dataSource, JpaVendorAdapter jpaVendeorAdapter) {
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.hbm2ddl.auto", environment.getProperty("hbm2ddl.auto"));
        jpaProp.put(
                "hibernate.integrator_provider",
                (IntegratorProvider) () -> Collections.singletonList(
                        RootAwareEventListenerIntegrator.INSTANCE
                )
        );

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendeorAdapter);
        entityManagerFactory.setPackagesToScan("jcrm.pp.ua.crmsystem");
        entityManagerFactory.setJpaProperties(jpaProp);

        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(environment.getProperty("hibernate.dialect"));
        return adapter;
    }

    @Bean
    public Module jacksonHibernate5Module() {
        Hibernate5Module module = new Hibernate5Module();
        module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        return module;
    }

    @Bean
    public Module jacksonPageWithJsonViewModule() {
        SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
        module.addSerializer(PageImplBean.class, new PageSerializer());
        return module;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                ignoreAcceptHeader(false).
                favorParameter(false).
                defaultContentType(MediaType.APPLICATION_JSON_UTF8);
    }

//    @Bean
//    public RepositoryRestConfigurerAdapter globalRepositoryRestConfigurer() {
//        return new RepositoryRestConfigurerAdapter() {
//            @Override
//            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//                super.configureRepositoryRestConfiguration(config);
//                config.getCorsRegistry()
//                        .addMapping("/**")
//                        .allowCredentials(false)
//                        .allowedOrigins("*")
//                        .maxAge(180)
//                        .allowedMethods("PUT", "POST", "GET", "OPTIONS", "DELETE", "PATCH")
//                        .allowedHeaders("*");
//                //.exposedHeaders("Authorization");
//                config.setReturnBodyOnCreate(false);
//                config.setReturnBodyOnUpdate(false);
//            }
//        };
//    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        resolver.setMaxPageSize(100);
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    AuditorAware<User> auditorAware() {
        return new AuditorAwareImpl();
    }

//    @Value("${virustotal.api.key}")
//    private String APIKEY;

//    @Bean
//    VirusResolver virusResolver() {
//        VirusResolver.getInstance().setAPIKEY(APIKEY);
//        return VirusResolver.getInstance();
//    }

    @Bean
    InitialDataLoader initialDataLoader() {
        return new InitialDataLoader();
    }


    @Value("${data.source}")
    private String dataSource;

//    @Bean
//    TxtFileReader fileReader() {
//        //java.nio.file.NoSuchFileException
//
//        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.home") + dataSource))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach(TxtFileReader::addFileToMemory);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        TxtFileReader.addFilesToMemory(Arrays.asList(
////                dataSource + "/type.json"));
//        return TxtFileReader.getInstance();
//    }


    //    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods();
//            }
//        };
//    }

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(false)
//                .allowedOrigins("*")
//                .maxAge(3600)
//                .allowedMethods("PUT", "POST", "GET", "OPTIONS", "DELETE")
//                .allowedHeaders("Access-Control-Allow-Origin")
//               // .exposedHeaders("Authorization", "Content-Type");
//    }


//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    private Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//
//        return connector;
//    }

}
