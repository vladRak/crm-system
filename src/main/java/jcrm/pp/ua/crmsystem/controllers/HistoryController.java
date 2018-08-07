package jcrm.pp.ua.crmsystem.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.OptimisticLockException;

//@PropertySource(value = {"file:${user.home}/crm/conf/config-external.properties"}, ignoreResourceNotFound = true)

//@PropertySources({
//        @PropertySource("classpath:abc.properties"),
//        @PropertySource(value="file:abc.properties", ignoreResourceNotFound=true)
//})

@RestController
@RequestMapping(
        value = "history",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE})
public class HistoryController {

    Logger logger = LoggerFactory.getLogger(HistoryController.class.getName());

    @Autowired
    Environment environment;


    @GetMapping(value = "/check")
    void findContactByID(){
        System.out.println("enviroment =" + environment.getProperty("data.name"));
        logger.info("OptimisticLockException("+ environment.getProperty("data.name")+")");
        logger.debug("OptimisticLockException("+ environment.getProperty("data.name") +" - debug)");
        throw new OptimisticLockException();
    }
}
