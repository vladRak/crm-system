//package jcrm.pp.ua.crmsystem.configs;
//
//import org.omg.CORBA.Environment;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.io.IOException;
//import java.util.Properties;
//
//@Configuration
////@PropertySource("classpath:mail/emailconfig.properties")
//public class MailConfig implements ApplicationContextAware, EnvironmentAware {
//
//    private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";
//
//    private ApplicationContext applicationContext;
//    private Environment environment;
//
//
//
//    @Bean
//    public JavaMailSender mailSender() throws IOException {
//
//        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        // Basic mail sender configuration, based on emailconfig.properties
//        mailSender.setHost(this.environment.getProperty(HOST));
//        mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
//        mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
//        mailSender.setUsername(this.environment.getProperty(USERNAME));
//        mailSender.setPassword(this.environment.getProperty(PASSWORD));
//
//        // JavaMail-specific mail sender configuration, based on javamail.properties
//        final Properties javaMailProperties = new Properties();
//        javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
//        mailSender.setJavaMailProperties(javaMailProperties);
//
//        return mailSender;
//
//    }
//
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//
//    }
//
//    @Override
//    public void setEnvironment(org.springframework.core.env.Environment environment) {
//
//    }
//}
