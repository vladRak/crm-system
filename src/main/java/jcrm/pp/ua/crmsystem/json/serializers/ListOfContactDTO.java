package jcrm.pp.ua.crmsystem.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jcrm.pp.ua.crmsystem.dto.ContactDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfContactDTO extends StdSerializer<List<ContactDTO>> {


    public ListOfContactDTO() {
        this(null);
    }

    public ListOfContactDTO(Class<List<ContactDTO>> t) {
        super(t);
    }

    @Override
    public void serializeWithType(List<ContactDTO> value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForObject(value,gen);
        serialize(value, gen, serializers); // call your customized serialize method
        typeSer.writeTypeSuffixForObject(value, gen);
    }

    @Override
    public void serialize(List<ContactDTO> contactDTOList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

//        jsonGenerator.writeStartArray();
//        for (ContactDTO contactDTO : contactDTOList) {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", contactDTO.getId());
//
//            jsonGenerator.writeStringField("fullname", String.valueOf(contactDTO.getFullName()));
//
//            jsonGenerator.writeArrayFieldStart("phones");
//            List<PhoneDTO> phones = new ArrayList<>(contactDTO.getPhones());
//            for (PhoneDTO p : phones) {
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeNumberField("id", p.getId());
//                jsonGenerator.writeStringField("phone", p.getPhone());
//                jsonGenerator.writeEndObject();
//            }
//            jsonGenerator.writeEndArray();
//
//            jsonGenerator.writeArrayFieldStart("emails");
//            List<EmailDTO> emails = new ArrayList<>(contactDTO.getEmails());
//            for (EmailDTO e : emails) {
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeNumberField("id", e.getId());
//                jsonGenerator.writeStringField("email", e.getEmail());
//                jsonGenerator.writeEndObject();
//            }
//            jsonGenerator.writeEndArray();
//
//            jsonGenerator.writeArrayFieldStart("addresses");
//            List<AddressDTO> address = new ArrayList<>(contactDTO.getAddresses());
//            for (AddressDTO a : address) {
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeNumberField("id", a.getId());
//                jsonGenerator.writeStringField("city", a.getCity());
//                jsonGenerator.writeStringField("address", a.getAddress());
//                jsonGenerator.writeEndObject();
//            }
//            jsonGenerator.writeEndArray();
//
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();

        List<ContactDTO> contacts = new ArrayList<>();
        for (ContactDTO c : contactDTOList) {
            c.setCompany(null);
            contacts.add(c);


        }
        jsonGenerator.writeObject(contacts);
    }
}
