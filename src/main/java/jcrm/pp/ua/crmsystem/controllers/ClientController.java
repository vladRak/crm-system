package jcrm.pp.ua.crmsystem.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import jcrm.pp.ua.crmsystem.customClasses.CompanyPatchRequest;
import jcrm.pp.ua.crmsystem.customClasses.ContactPatchRequest;
import jcrm.pp.ua.crmsystem.customClasses.EmailService;
import jcrm.pp.ua.crmsystem.customClasses.PageImplBean;
import jcrm.pp.ua.crmsystem.dto.BaseClientDTO;
import jcrm.pp.ua.crmsystem.dto.CompanyDTO;
import jcrm.pp.ua.crmsystem.dto.ContactDTO;
import jcrm.pp.ua.crmsystem.entities.Imp.BaseClientImp;
import jcrm.pp.ua.crmsystem.entities.Imp.Company;
import jcrm.pp.ua.crmsystem.entities.Imp.Contact;
import jcrm.pp.ua.crmsystem.json.serializers.Views;
import jcrm.pp.ua.crmsystem.services.ClientService;
import jcrm.pp.ua.crmsystem.services.HistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(
        value = "list",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE})
public class ClientController {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    ClientService clientService;

    @Autowired
    HistoryService historyService;


    @Autowired
    EmailService emailService;

    //@JsonView(Views.OneLineName.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Page<BaseClientDTO> findAllClients(@RequestParam(value="search", required = false) String search,
                                      @PageableDefault(size = 20) Pageable pageable) throws MessagingException, InterruptedException {

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Page<BaseClientImp> clientsPage;

        if (search != null) {
            clientsPage = clientService.searchClients(search,pageable);
        }else clientsPage = clientService.getAllClients(pageable);

        Page<BaseClientDTO> resultPage = clientsPage.map(new Converter<BaseClientImp, BaseClientDTO>() {
            @Override
            public BaseClientDTO convert(BaseClientImp client) {
                if (client instanceof Contact){
                    ContactDTO contactDTO = modelMapper.map(client,ContactDTO.class);
                    return contactDTO;
                }else {
                    CompanyDTO companyDTO = modelMapper.map(client,CompanyDTO.class);
                    return companyDTO;
                }
            }
        });

        // emailService.send("vladrakitianskiy@gmail.com","Test Email","Test sending email.");

        //clientService.findAuditByRevision(1L,1);

        List list = historyService.findContactRevisions(1L);

        return new PageImplBean(resultPage);
    }

    @GetMapping(value = "/contacts")
    @ResponseBody Page<ContactDTO> findAllContacts(@RequestParam(value="search", required = false) String search,
                                                   @PageableDefault(size = 20) Pageable pageable) throws IOException {
        Page<Contact> contactPage;

        if (search != null) {
            contactPage = clientService.searchContacts(search,pageable);
        }else contactPage = clientService.getAllContacts(pageable);

        Page<ContactDTO> resultPage = contactPage.map(new Converter<Contact, ContactDTO>() {
            @Override
            public ContactDTO convert(Contact contact) {
                return modelMapper.map(contact,ContactDTO.class);
            }
        });
        return resultPage;
    }

    @GetMapping(value = "/contacts/{id}")
    @ResponseBody
    ObjectNode findContactByID(@PathVariable long id){
        Contact contact = clientService.getContactById(id);
        ContactDTO contactDTO = modelMapper.map(contact,ContactDTO.class);
        SerializationConfig conf = objectMapper.getSerializationConfig().withView(Views.Public.class);
        ObjectNode objectNode = objectMapper.setConfig(conf).valueToTree(contactDTO);

        return objectNode;
    }

    @GetMapping(value = "/companies")
    @ResponseBody Page<CompanyDTO> findAllCompanies(@RequestParam(value="search", required = false) String search,
                                                    @PageableDefault(size = 20) Pageable pageable){
        Page<Company> companiesPage;

        if (search != null) {
            companiesPage = clientService.searchCompanies(search,pageable);
        }else companiesPage = clientService.getAllCompanies(pageable);

        Page<CompanyDTO> resultPage = companiesPage.map(new Converter<Company, CompanyDTO>() {
            @Override
            public CompanyDTO convert(Company company) {
                return modelMapper.map(company,CompanyDTO.class);
            }
        });
        return resultPage;
    }

    @GetMapping(value = "/companies/{id}")
    @ResponseBody CompanyDTO findCompanyByID(@PathVariable long id){
        Company company = clientService.getCompanyById(id);
        CompanyDTO companyDTO = modelMapper.map(company,CompanyDTO.class);
        return companyDTO;
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody String addContact(@RequestBody String string) throws IOException, ProcessingException, InterruptedException {
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

        File fType = new File(System.getProperty("user.home") + "/schemas/type.json");

        URI uriType = fType.toURI();

        JsonSchema jSchType = factory.getJsonSchema(uriType.toString());

        ProcessingReport report;

        report = jSchType.validate(objectMapper.readTree(string));

        ObjectNode on = objectMapper.readValue(string,ObjectNode.class);
        if(on.has("type")){
            JsonNode type = on.get("type");
            if (type.toString().equals("\"contact\"")){
                File fCon = new File(System.getProperty("user.home") + "/schemas/contact.json");
                URI uriCon = fCon.toURI();
                JsonSchema jSchCon = factory.getJsonSchema(uriCon.toString());
                report = jSchCon.validate(objectMapper.readTree(string));
                if (jSchCon.validInstance(objectMapper.readTree(string))){
                    ContactDTO contactDTO = objectMapper.readValue(string,ContactDTO.class);
                    Contact contact = modelMapper.map(contactDTO,Contact.class);
                    clientService.addContact(contact);
                }
                return report.toString();

            }else if (type.toString().equals("\"company\"")){
                File fComp = new File(System.getProperty("user.home") + "/schemas/company.json");
                URI uriComp = fComp.toURI();
                JsonSchema jSchComp = factory.getJsonSchema(uriComp.toString());
                report = jSchComp.validate(objectMapper.readTree(string));
                if (jSchComp.validInstance(objectMapper.readTree(string))){
                    CompanyDTO companyDTO = objectMapper.readValue(string,CompanyDTO.class);
                    Company company = modelMapper.map(companyDTO,Company.class);
                    clientService.addCompany(company);
                }
                return report.toString();
            }else return report.toString();
        }
        return report.toString();
    }

    @DeleteMapping(value = "/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    void removeCompany(@PathVariable long id){
        clientService.removeCompanyById(id);
    }

    @DeleteMapping(value = "/contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    void removeClient(@PathVariable long id){
        clientService.removeContactById(id);
    }

    @PutMapping(value = "/contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String updateClient(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/contact.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            ContactDTO contactDTO = objectMapper.readValue(string,ContactDTO.class);
            Contact contact = modelMapper.map(contactDTO,Contact.class);
            clientService.updateContact(contact, id);
        }
        System.out.println(report.toString());
        return report.toString();
    }

    @PutMapping(value = "/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String updateCompany(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/company.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            CompanyDTO companyDTO = objectMapper.readValue(string,CompanyDTO.class);
            Company company = modelMapper.map(companyDTO,Company.class);
            clientService.updateCompany(company, id);
        }
        System.out.println(report.toString());
        return report.toString();
    }

    @PatchMapping(value = "/contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String patchClient(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/contact.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            ContactPatchRequest patchRequest = objectMapper.readValue(string,ContactPatchRequest.class);
            clientService.patchContact(patchRequest,id);
        }
        System.out.println(report.toString());
        return report.toString();
    }

    @PatchMapping(value = "/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String patchCompany(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/company.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            CompanyPatchRequest patchRequest = objectMapper.readValue(string,CompanyPatchRequest.class);
            clientService.patchCompany(patchRequest, id);
        }
        System.out.println(report.toString());
        return report.toString();
    }




    //    @RequestMapping(
//            value = "/companies/add",
//            method = RequestMethod.POST
//    )
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    String addCompany(@RequestBody String string) throws ProcessingException, IOException, InterruptedException {
//
//        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//        File file = new File(System.getProperty("user.home") + "/schemas/company.json");
//        URI uri = file.toURI();
//
//        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());
//
//        ProcessingReport report;
//
//        report = jsonSchema.validate(objectMapper.readTree(string));
//
//        if (jsonSchema.validInstance(objectMapper.readTree(string))){
//            CompanyDTO companyDTO = objectMapper.readValue(string,CompanyDTO.class);
//            Company company = modelMapper.map(companyDTO,Company.class);
//            contactService.addCompany(company);
//        }
//        System.out.println(report.toString());
//        return report.toString();
//    }





//    @GetMapping(value = "/search")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody Page<ClientDTOInt> search(@RequestParam(value="search") String search, @PageableDefault(size = 20) Pageable pageable) {
//
//        Page<Client> contactPage = contactService.searchClients(search,pageable);
//        Page<ClientDTOInt> resultPage = contactPage.map(new Converter<Client, ClientDTOInt>() {
//
//            @Override
//            public ClientDTOInt convert(Client client) {
//                modelMapper.typeMap(Client.class, ClientDTOInt.class).setProvider(new Provider<ClientDTOInt>() {
//                    public ClientDTOInt get(ProvisionRequest<ClientDTOInt> request) {
//                        ClientDTO clientDTO = modelMapper.map(request.getSource(),ClientDTO.class);
//                        if (clientDTO.getContact()!=null){
//                            ContactDTO contactDTO = clientDTO.getContact();
//                            return contactDTO;
//                        }else {
//                            CompanyDTO companyDTO = clientDTO.getCompany();
//                            return companyDTO;
//                        }
//                    }
//                });
//                return modelMapper.map(client, ClientDTOInt.class);
//            }
//        });
//        return new PageImplBean(resultPage);
//    }



}

