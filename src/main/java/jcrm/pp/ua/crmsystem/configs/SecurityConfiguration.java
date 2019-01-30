package jcrm.pp.ua.crmsystem.configs;

import jcrm.pp.ua.crmsystem.customClasses.CorsFilter;
import jcrm.pp.ua.crmsystem.customClasses.CustomBasicAuthenticationEntryPoint;
import jcrm.pp.ua.crmsystem.customClasses.registration.MyUserDetailsService;
import jcrm.pp.ua.crmsystem.entities.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

//import jcrm.pp.ua.crmsystem.customClasses.DetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static String REALM="MY_TEST_REALM";
    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Autowired
    MyUserDetailsService detailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("173247").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("system").password("173247").roles("SYSTEM");
        auth.userDetailsService(detailsService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                //.anonymous().disable()
                .authorizeRequests()
                    .antMatchers("/account/create").permitAll()
                //.antMatchers("/list").hasAuthority("ROLE_SYS_ADMINISTRATOR")
                    .anyRequest().authenticated()
                .and()
                    .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and()
                    .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}


//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("173247").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .addFilterBefore(corsFilter(), SessionManagementFilter.class) //adds your custom CorsFilter
//                //.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
//                .formLogin()
//                .successHandler(ajaxSuccessHandler)
//                .failureHandler(ajaxFailureHandler)
//                .loginProcessingUrl("/authentication")
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .csrf().disable()
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/authentication").permitAll()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/user/*").access("hasRole('ROLE_USER')");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .antMatchers("/user/**").hasRole("ADMIN")
//
//                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
//    }
//



