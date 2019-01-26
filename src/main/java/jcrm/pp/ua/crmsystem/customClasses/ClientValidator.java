//package jcrm.pp.ua.crmsystem.customClasses;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.github.fge.jsonschema.core.exceptions.ProcessingException;
//import com.github.fge.jsonschema.core.report.ProcessingReport;
//import com.github.fge.jsonschema.main.JsonSchema;
//import com.github.fge.jsonschema.main.JsonSchemaFactory;
//import jcrm.pp.ua.crmsystem.dto.CompanyDTO;
//import jcrm.pp.ua.crmsystem.dto.ContactDTO;
//import jcrm.pp.ua.crmsystem.entities.Company;
//import jcrm.pp.ua.crmsystem.entities.Contact;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.util.LinkedList;
//import java.util.List;
//
//public class ClientValidator {
//
//    private static final ClientValidator clientValidator = new ClientValidator();
//
//    private final ObjectMapper objectMapper;
//    private final ModelMapper modelMapper;
//
//    public ClientValidator(){
//        objectMapper = new ObjectMapper();
//        modelMapper = new ModelMapper();
//    }
//
//    public static ClientValidator getInstance() {
//        return clientValidator;
//    }
//
//    public void validate(String string) throws ProcessingException, IOException {
//        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//
//        File fType = new File(System.getProperty("user.home") + "/schemas/type.json");
//
//        URI uriType = fType.toURI();
//
//        JsonSchema jSchType = factory.getJsonSchema(uriType.toString());
//
//        ProcessingReport report;
//
//        report = jSchType.validate(objectMapper.readTree(string));
//
//        ObjectNode on = objectMapper.readValue(string,ObjectNode.class);
//        if(on.has("type")){
//            JsonNode type = on.get("type");
//            if (type.toString().equals("\"contact\"")){
//                File fCon = new File(System.getProperty("user.home") + "/schemas/contact.json");
//                URI uriCon = fCon.toURI();
//                JsonSchema jSchCon = factory.getJsonSchema(uriCon.toString());
//                report = jSchCon.validate(objectMapper.readTree(string));
//                if (jSchCon.validInstance(objectMapper.readTree(string))){
//                    ContactDTO contactDTO = objectMapper.readValue(string,ContactDTO.class);
//                    Contact contact = modelMapper.map(contactDTO,Contact.class);
//                    clientService.addContact(contact);
//                }
//                return report.toString();
//
//            }else if (type.toString().equals("\"company\"")){
//                File fComp = new File(System.getProperty("user.home") + "/schemas/company.json");
//                URI uriComp = fComp.toURI();
//                JsonSchema jSchComp = factory.getJsonSchema(uriComp.toString());
//                report = jSchComp.validate(objectMapper.readTree(string));
//                if (jSchComp.validInstance(objectMapper.readTree(string))){
//                    CompanyDTO companyDTO = objectMapper.readValue(string,CompanyDTO.class);
//                    Company company = modelMapper.map(companyDTO,Company.class);
//                    clientService.addCompany(company);
//                }
//                return report.toString();
//            }else return report.toString();
//        }
//        return report.toString();
//    }
//
//
//
//}
