package jcrm.pp.ua.crmsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import jcrm.pp.ua.crmsystem.dto.LeadDTO;
import jcrm.pp.ua.crmsystem.entities.Imp.Lead;
import jcrm.pp.ua.crmsystem.services.LeadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(
        value = "leads",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE})
public class LeadController {

    ObjectMapper objectMapper = new ObjectMapper();
    final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    LeadService leadService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Page<LeadDTO> findAllLeads(@PageableDefault(size = 20) Pageable pageable) throws IOException {
        Page<Lead> contactPage = leadService.getAllLeads(pageable);
        Page<LeadDTO> resultPage = contactPage.map(new Converter<Lead, LeadDTO>() {
            @Override
            public LeadDTO convert(Lead lead) {
                return modelMapper.map(lead,LeadDTO.class);
            }
        });
        return resultPage;
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody LeadDTO findLeadByID(@PathVariable long id){
        Lead lead = leadService.getLeadById(id);
        LeadDTO leadDTO = modelMapper.map(lead,LeadDTO.class);
        return leadDTO;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    void removeLead(@PathVariable long id){
        leadService.removeLead(id);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String updateLead(@RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/lead.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            LeadDTO leadDTO = objectMapper.readValue(string,LeadDTO.class);
            Lead lead = modelMapper.map(leadDTO,Lead.class);
            leadService.updateLead(lead);
        }
        System.out.println(report.toString());
        return report.toString();
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody String addLead(@RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/lead.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            LeadDTO leadDTO = objectMapper.readValue(string,LeadDTO.class);
            Lead lead = modelMapper.map(leadDTO,Lead.class);
            leadService.addLead(lead);
        }
        System.out.println(report.toString());
        return report.toString();
    }

}
