package jcrm.pp.ua.crmsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import jcrm.pp.ua.crmsystem.dto.UserDTO;
import jcrm.pp.ua.crmsystem.entities.User;
import jcrm.pp.ua.crmsystem.repositories.ContactRepo;
import jcrm.pp.ua.crmsystem.services.AccountService;
import jcrm.pp.ua.crmsystem.services.ClientService;
import jcrm.pp.ua.crmsystem.services.UserService;
import jcrm.pp.ua.crmsystem.services.imp.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(
        value = "/account",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE})
public class AccountController {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    //@ResponseBody
    String createAccount(
            @RequestBody String string,
            @RequestParam(value="demo", required = false, defaultValue = "false") boolean demo) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        ProcessingReport report;

        File fUser = new File(System.getProperty("user.home") + "/schemas/user.json");
        URI uriUser = fUser.toURI();
        JsonSchema jSchUser = factory.getJsonSchema(uriUser.toString());
        report = jSchUser.validate(objectMapper.readTree(string));
                if  (jSchUser.validInstance(objectMapper.readTree(string))){
                    UserDTO userDTO = objectMapper.readValue(string,UserDTO.class);
                    User user = modelMapper.map(userDTO,User.class);
                    accountService.createAccount(user, demo);
                }
                else return report.toString();
        return report.toString();
    }

    @Autowired
    Environment environment;

    @GetMapping(value = "/check")
    @ResponseStatus(HttpStatus.OK)
        //@ResponseBody
    void checkAccount(){
        System.out.println("Account = " + environment.getProperty("data.name"));
        System.out.println("Account = " + environment.getProperty("data.source"));
        System.out.println("spring.jpa.show-sql = " + environment.getProperty("spring.jpa.show-sql"));
        System.out.println("spring.main.banner-mode = " + environment.getProperty("spring.main.banner-mode"));
        System.out.println("spring.main.banner-mode = " + environment.toString());
    }


    @Autowired
    UserService userService;

    @Autowired
    ContactRepo contactRepo;

    @GetMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
        //@ResponseBody
    void checkDelete(){
//        userService.deleteUser(32L);
        contactRepo.delete(30L);
    }

//    @PostMapping(value = "/import")
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody String importClients(
//            @RequestParam("file") MultipartFile file){
//        Log4J2PropertiesConf log4J2PropertiesConf=new Log4J2PropertiesConf();
//        log4J2PropertiesConf.performSomeTask();
//        String name = "test11";
//        if (!file.isEmpty()) {
//            try {
////                File convFile = new File( file.getOriginalFilename());
////                file.transferTo(convFile);
//
//                System.out.println(file.getContentType());
//
//                byte[] bytes = file.getBytes();
//
////                ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
////                baos.write(bytes, 0, bytes.length);
////
////                OutputStream outputStream = new FileOutputStream("thefilename");
////                    baos.writeTo(outputStream);
//
////                File convFile = new File(file.getOriginalFilename());
////                convFile.createNewFile();
////                FileOutputStream fos = new FileOutputStream(convFile);
////                fos.write(file.getBytes());
////                fos.close();
//
////                if (accountService.importClients(convFile)) {
////                    convFile.delete();
////                    return "You successfully uploaded " + name + " into " + name + "-uploaded !";
////                }
////                else return "Not uploaded!!!!!";
//
//
//                if (accountService.importClients(bytes)) {
//                    return "You successfully uploaded " + name + " into " + name + "-uploaded !";
//                }
//                else return "Not uploaded!!!!!";
//
//
////                byte[] bytes = file.getBytes();
////                BufferedOutputStream stream =
////                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
////                stream.write(bytes);
////                stream.close();
//
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
//    }
}
