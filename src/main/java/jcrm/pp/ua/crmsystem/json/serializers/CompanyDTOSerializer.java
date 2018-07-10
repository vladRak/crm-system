package jcrm.pp.ua.crmsystem.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import jcrm.pp.ua.crmsystem.dto.AddressDTO;
import jcrm.pp.ua.crmsystem.dto.CompanyDTO;
import jcrm.pp.ua.crmsystem.dto.EmailDTO;
import jcrm.pp.ua.crmsystem.dto.PhoneDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDTOSerializer extends JsonSerializer<CompanyDTO> {

    @Override
    public void serializeWithType(CompanyDTO value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        //super.serializeWithType(value, gen, serializers, typeSer);

        //typeSer.writeTypePrefixForObject(value, gen);
        typeSer.writeTypePrefixForObject(value,gen);
        serialize(value, gen, serializers); // call your customized serialize method
        typeSer.writeTypeSuffixForObject(value, gen);


    }

    @Override
    public void serialize(CompanyDTO companyDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeNumberField("id", companyDTO.getId());

        jsonGenerator.writeStringField("fullname",companyDTO.getFullname());

        jsonGenerator.writeArrayFieldStart("phones");
        List<PhoneDTO> phones = new ArrayList<>(companyDTO.getPhones());
        for (PhoneDTO p : phones) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", p.getId());
            jsonGenerator.writeStringField("phone", p.getPhone());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("emails");
        List<EmailDTO> emails = new ArrayList<>(companyDTO.getEmails());
        for (EmailDTO e : emails) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", e.getId());
            jsonGenerator.writeStringField("email", e.getEmail());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("addresses");
        List<AddressDTO> address = new ArrayList<>(companyDTO.getAddresses());
        for (AddressDTO a : address) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", a.getId());
            jsonGenerator.writeStringField("city", a.getCity());
            jsonGenerator.writeStringField("address", a.getAddress());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

    }




}
