//package jcrm.pp.ua.crmsystem.json.serializers;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
//import jcrm.pp.ua.crmsystem.dto.CompanyDTO;
//import jcrm.pp.ua.crmsystem.entities.impl.Company;
//
//import java.io.IOException;
//
//public class CompanySerializer extends JsonSerializer<CompanyDTO>{
//
//    @Override
//    public void serializeWithType(CompanyDTO value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
//        //super.serializeWithType(value, gen, serializers, typeSer);
//
//        //typeSer.writeTypePrefixForObject(value, gen);
//        typeSer.writeTypePrefixForObject(value,gen);
//        serialize(value, gen, serializers); // call your customized serialize method
//        typeSer.writeTypeSuffixForObject(value, gen);
//
//
//    }
//
//    @Override
//    public void serialize(CompanyDTO company, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//
//        jsonGenerator.writeNumberField("id", company.getId());
//        jsonGenerator.writeStringField("fullname", company.getFullname());
//    }
//}
