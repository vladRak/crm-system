//package jcrm.pp.ua.crmsystem.controllers;
//
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.github.fge.jsonschema.core.exceptions.ProcessingException;
//import com.github.fge.jsonschema.core.report.ProcessingReport;
//import com.github.fge.jsonschema.main.JsonSchema;
//import com.github.fge.jsonschema.main.JsonSchemaFactory;
//import jcrm.pp.ua.crmsystem.customClasses.CompanyPatchRequest;
//import jcrm.pp.ua.crmsystem.customClasses.ContactPatchRequest;
//import jcrm.pp.ua.crmsystem.customClasses.EmailService;
//import jcrm.pp.ua.crmsystem.customClasses.PageImplBean;
//import jcrm.pp.ua.crmsystem.dto.*;
//import jcrm.pp.ua.crmsystem.entities.*;
//import jcrm.pp.ua.crmsystem.json.serializers.Views;
//import jcrm.pp.ua.crmsystem.services.ContactService;
//import org.modelmapper.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.mail.MessagingException;
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.util.Arrays;
//
//@RestController
//@RequestMapping(
//        value = "list",
//        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE},
//        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE})
//public class ContactController {
//    private ObjectMapper objectMapper = new ObjectMapper();
//    private final ModelMapper modelMapper = new ModelMapper();
//
//    @Autowired
//    ContactService contactService;
//
//
//    @Autowired
//    EmailService emailService;
//
//    //@JsonView(Views.OneLineName.class)
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody Page<ClientDTOInt> findAllClients(@RequestParam(value="search", required = false) String search,
//                                                    @PageableDefault(size = 20) Pageable pageable) throws MessagingException {
//        Page<Client> clientsPage;
//
//        if (search != null) {
//            clientsPage = contactService.searchClients(search,pageable);
//        }else clientsPage = contactService.findPaginated(pageable);
//
//        Page<ClientDTOInt> resultPage = clientsPage.map(new Converter<Client, ClientDTOInt>() {
//
//            @Override
//            public ClientDTOInt convert(Client client) {
//                    modelMapper.typeMap(Client.class, ClientDTOInt.class).setProvider(new Provider<ClientDTOInt>() {
//                        public ClientDTOInt get(ProvisionRequest<ClientDTOInt> request) {
//                            ClientDTO clientDTO = modelMapper.map(request.getSource(),ClientDTO.class);
//                            if (clientDTO.getContact()!=null){
//                                ContactDTO contactDTO = clientDTO.getContact();
//                                return contactDTO;
//                            }else {
//                                CompanyDTO companyDTO = clientDTO.getCompany();
//                                return companyDTO;
//                            }
//                        }
//                    });
//                return modelMapper.map(client, ClientDTOInt.class);
//            }
//        });
//
//       // emailService.send("vladrakitianskiy@gmail.com","Test Email","Test sending email.");
//
//        Contact2 contact2 = new Contact2();
//        Address address = new Address();
//        address.setCity("qeqqwewe");
//        address.setAddress("rwerwerewr 5");
//        contact2.setAddresses(Arrays.asList(address));
//        address.setBaseClient(contact2);
//
//        Company company2 = new Company();
//        company2.setFullName("Ololo");
//
//        contact2.setCompany2(company2);
//
//        Name name = new Name();
//        name.setFirstname("sdssd");
//        name.setSurname("dfjdfdjf");
//
//        name.setContact2(contact2);
//        contact2.setFullName(name);
//
//        Client2 client21 = new Client2();
//        client21.setBaseClient(company2);
//
//        Client2 client2 = new Client2();
//        client2.setBaseClient(contact2);
//
//        try {
//            contactService.addContact2(client2);
//            contactService.addContact2(client21);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return new PageImplBean(resultPage);
//    }
//
//    @GetMapping(value = "/contacts")
//    @ResponseBody Page<ContactDTO> findAllContacts(@RequestParam(value="search", required = false) String search,
//                                                   @PageableDefault(size = 20) Pageable pageable) throws IOException {
//        Page<Contact> contactPage;
//
//        if (search != null) {
//            contactPage = contactService.searchContacts(search,pageable);
//        }else contactPage = contactService.getAllContacts(pageable);
//
//        Page<ContactDTO> resultPage = contactPage.map(new Converter<Contact, ContactDTO>() {
//            @Override
//            public ContactDTO convert(Contact contact) {
//                return modelMapper.map(contact,ContactDTO.class);
//            }
//        });
//        return resultPage;
//    }
//
//    @GetMapping(value = "/contacts/{id}")
//    @ResponseBody ObjectNode findContactByID(@PathVariable long id){
//        Contact contact = contactService.getContactById(id);
//        ContactDTO contactDTO = modelMapper.map(contact,ContactDTO.class);
//        SerializationConfig conf = objectMapper.getSerializationConfig().withView(Views.Name.class);
//        ObjectNode objectNode = objectMapper.setConfig(conf).valueToTree(contactDTO);
//
//        return objectNode;
//    }
//
//    @GetMapping(value = "/companies")
//    @ResponseBody Page<CompanyDTO> findAllCompanies(@RequestParam(value="search", required = false) String search,
//                                                    @PageableDefault(size = 20) Pageable pageable){
//        Page<Company> companiesPage;
//
//        if (search != null) {
//            companiesPage = contactService.searchCompanies(search,pageable);
//        }else companiesPage = contactService.getAllCompanies(pageable);
//
//        Page<CompanyDTO> resultPage = companiesPage.map(new Converter<Company, CompanyDTO>() {
//            @Override
//            public CompanyDTO convert(Company company) {
//                return modelMapper.map(company,CompanyDTO.class);
//            }
//        });
//        return resultPage;
//    }
//
//    @GetMapping(value = "/companies/{id}")
//    @ResponseBody CompanyDTO findCompanyByID(@PathVariable long id){
//        Company company = contactService.getCompanyById(id);
//        CompanyDTO companyDTO = modelMapper.map(company,CompanyDTO.class);
//        return companyDTO;
//    }
//
//    @PostMapping(value = "/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody String addContact(@RequestBody String string) throws IOException, ProcessingException, InterruptedException {
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
//                    contactService.addContact(contact);
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
//                    contactService.addCompany(company);
//                }
//                return report.toString();
//            }else return report.toString();
//        }
//        return report.toString();
//    }
//
//    @DeleteMapping(value = "/companies/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    void removeCompany(@PathVariable long id){
//        contactService.removeCompanyById(id);
//    }
//
//    @DeleteMapping(value = "/contacts/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    void removeClient(@PathVariable long id){
//        contactService.removeContactById(id);
//    }
//
//    @PutMapping(value = "/contacts/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody String updateClient(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {
//
//        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//        File file = new File(System.getProperty("user.home") + "/schemas/contact.json");
//        URI uri = file.toURI();
//
//        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());
//
//        ProcessingReport report;
//
//        report = jsonSchema.validate(objectMapper.readTree(string));
//
//        if (jsonSchema.validInstance(objectMapper.readTree(string))){
//            ContactDTO contactDTO = objectMapper.readValue(string,ContactDTO.class);
//            Contact contact = modelMapper.map(contactDTO,Contact.class);
//            contactService.updateContact(contact, id);
//        }
//        System.out.println(report.toString());
//        return report.toString();
//    }
//
//    @PutMapping(value = "/companies/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody String updateCompany(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {
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
//            contactService.updateCompany(company, id);
//        }
//        System.out.println(report.toString());
//        return report.toString();
//    }
//
//    @PatchMapping(value = "/contacts/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody String patchClient(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {
//
//        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
//        File file = new File(System.getProperty("user.home") + "/schemas/contact.json");
//        URI uri = file.toURI();
//
//        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());
//
//        ProcessingReport report;
//
//        report = jsonSchema.validate(objectMapper.readTree(string));
//
//        if (jsonSchema.validInstance(objectMapper.readTree(string))){
//            ContactPatchRequest patchRequest = objectMapper.readValue(string,ContactPatchRequest.class);
//            contactService.patchContact(patchRequest,id);
//        }
//        System.out.println(report.toString());
//        return report.toString();
//    }
//
//    @PatchMapping(value = "/companies/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody String patchCompany(@PathVariable Long id, @RequestBody String string) throws ProcessingException, IOException {
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
//            CompanyPatchRequest patchRequest = objectMapper.readValue(string,CompanyPatchRequest.class);
//            contactService.patchCompany(patchRequest, id);
//        }
//        System.out.println(report.toString());
//        return report.toString();
//    }
//
//
//
//
//    //    @RequestMapping(
////            value = "/companies/add",
////            method = RequestMethod.POST
////    )
////    @ResponseBody
////    @ResponseStatus(HttpStatus.CREATED)
////    String addCompany(@RequestBody String string) throws ProcessingException, IOException, InterruptedException {
////
////        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
////        File file = new File(System.getProperty("user.home") + "/schemas/company.json");
////        URI uri = file.toURI();
////
////        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());
////
////        ProcessingReport report;
////
////        report = jsonSchema.validate(objectMapper.readTree(string));
////
////        if (jsonSchema.validInstance(objectMapper.readTree(string))){
////            CompanyDTO companyDTO = objectMapper.readValue(string,CompanyDTO.class);
////            Company company = modelMapper.map(companyDTO,Company.class);
////            contactService.addCompany(company);
////        }
////        System.out.println(report.toString());
////        return report.toString();
////    }
//
//
//
//
//
////    @GetMapping(value = "/search")
////    @ResponseStatus(HttpStatus.OK)
////    @ResponseBody Page<ClientDTOInt> search(@RequestParam(value="search") String search, @PageableDefault(size = 20) Pageable pageable) {
////
////        Page<Client> contactPage = contactService.searchClients(search,pageable);
////        Page<ClientDTOInt> resultPage = contactPage.map(new Converter<Client, ClientDTOInt>() {
////
////            @Override
////            public ClientDTOInt convert(Client client) {
////                modelMapper.typeMap(Client.class, ClientDTOInt.class).setProvider(new Provider<ClientDTOInt>() {
////                    public ClientDTOInt get(ProvisionRequest<ClientDTOInt> request) {
////                        ClientDTO clientDTO = modelMapper.map(request.getSource(),ClientDTO.class);
////                        if (clientDTO.getContact()!=null){
////                            ContactDTO contactDTO = clientDTO.getContact();
////                            return contactDTO;
////                        }else {
////                            CompanyDTO companyDTO = clientDTO.getCompany();
////                            return companyDTO;
////                        }
////                    }
////                });
////                return modelMapper.map(client, ClientDTOInt.class);
////            }
////        });
////        return new PageImplBean(resultPage);
////    }
//
//
//
//}
