package jcrm.pp.ua.crmsystem.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jcrm.pp.ua.crmsystem.domain.entity.Phone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneSerializer extends StdSerializer<List<Phone>>{


//    @Override
//    public void serialize(PhoneDTO phone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//        jsonGenerator.writeStartObject();
//
//        jsonGenerator.writeFieldName("id");
//        jsonGenerator.writeNumber(phone.getId());
//
//        jsonGenerator.writeFieldName("phone");
//        jsonGenerator.writeString(phone.getPhone());
//        jsonGenerator.writeEndObject();
//    }

    private static List<Phone> phones=new ArrayList<Phone>();

    protected PhoneSerializer(Class<List<Phone>> t) {
        super(t);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void serialize(List<Phone> phones,
                          JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            List<String> titles=new ArrayList<>(phones.size());
            for(Phone phone: phones){
                titles.add(phone.getPhone());
            }
            jsonGenerator.writeObjectField("phoneDTOS", titles);
            jsonGenerator.writeEndObject();
    }
}
