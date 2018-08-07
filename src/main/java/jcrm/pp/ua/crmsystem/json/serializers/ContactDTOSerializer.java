package jcrm.pp.ua.crmsystem.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import jcrm.pp.ua.crmsystem.dto.AddressDTO;
import jcrm.pp.ua.crmsystem.dto.ContactDTO;
import jcrm.pp.ua.crmsystem.dto.EmailDTO;
import jcrm.pp.ua.crmsystem.dto.PhoneDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactDTOSerializer extends JsonSerializer<ContactDTO> {

    @Override
    public void serializeWithType(ContactDTO value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        //super.serializeWithType(value, gen, serializers, typeSer);

        //typeSer.writeTypePrefixForObject(value, gen);
        typeSer.writeTypePrefixForObject(value,gen);
        serialize(value, gen, serializers); // call your customized serialize method
        typeSer.writeTypeSuffixForObject(value, gen);
    }

    @Override
    public void serialize(ContactDTO contactDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

            jsonGenerator.writeNumberField("id", contactDTO.getId());

            jsonGenerator.writeStringField("fullname", String.valueOf(contactDTO.getFullName()));

            jsonGenerator.writeArrayFieldStart("phones");
            List<PhoneDTO> phones = new ArrayList<>(contactDTO.getPhones());
            for (PhoneDTO p : phones) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", p.getId());
                jsonGenerator.writeStringField("phone", p.getPhone());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeArrayFieldStart("emails");
            List<EmailDTO> emails = new ArrayList<>(contactDTO.getEmails());
            for (EmailDTO e : emails) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", e.getId());
                jsonGenerator.writeStringField("email", e.getEmail());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeArrayFieldStart("addresses");
            List<AddressDTO> address = new ArrayList<>(contactDTO.getAddresses());
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
