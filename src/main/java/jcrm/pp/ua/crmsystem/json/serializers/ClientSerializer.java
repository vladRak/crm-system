//package jcrm.pp.ua.crmsystem.json.serializers;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import jcrm.pp.ua.crmsystem.entities.Contact;
//import jcrm.pp.ua.crmsystem.entities.Email;
//import jcrm.pp.ua.crmsystem.entities.Phone;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClientSerializer extends JsonSerializer<Contact>{
//
//
//
//
//    @Override
//    public void serialize(Contact contact, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//
////            "content": [
////            {
////                "id": 2,
////                    "fullname": "John Doe",
////                    "phoneDTOS": "some string",
////                    "email": "some string",
////                    "position": "some string",
////                    "city": "some string",
////                    "address": "some string",
////                    "taskDTOS":[],
////                "leadDTOS":[]
//
//
//        jsonGenerator.writeStartObject();
//
//        jsonGenerator.writeStringField("type","contactDTO");
//
//        jsonGenerator.writeNumberField("id", contact.getId());
//
//        jsonGenerator.writeFieldName("fullname");
//        jsonGenerator.writeStartObject();
//
////        jsonGenerator.writeStringField("firstname", contactDTO.getName());
////        jsonGenerator.writeStringField("surname", contactDTO.getSurname());
//
//        jsonGenerator.writeEndObject();
//
//        jsonGenerator.writeArrayFieldStart("phoneDTOS");
//        List<Phone> phones = new ArrayList<>(contact.getPhones());
//        for (Phone p : phones) {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", p.getId());
//            jsonGenerator.writeStringField("phone", p.getPhone());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();
//
//        jsonGenerator.writeArrayFieldStart("emailDTOS");
//        List<Email> emails = new ArrayList<>(contact.getEmails());
//        for (Email e : emails) {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeNumberField("id", e.getId());
//            jsonGenerator.writeStringField("email", e.getEmail());
//            jsonGenerator.writeEndObject();
//        }
//        jsonGenerator.writeEndArray();
//
//        jsonGenerator.writeFieldName("position");
//        jsonGenerator.writeString(contact.getPosition());
//        jsonGenerator.writeEndObject();
//
//
//    }
//}
