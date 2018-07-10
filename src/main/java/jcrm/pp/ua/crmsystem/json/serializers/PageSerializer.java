package jcrm.pp.ua.crmsystem.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jcrm.pp.ua.crmsystem.customClasses.PageImplBean;

import java.io.IOException;

public class PageSerializer extends StdSerializer<PageImplBean> {

    public PageSerializer() {
        super(PageImplBean.class);
    }

    @Override
    public void serialize(PageImplBean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("number", value.getNumber());
        gen.writeNumberField("numberOfElements", value.getNumberOfElements());
        gen.writeNumberField("totalElements", value.getTotalElements());
        gen.writeNumberField("totalPages", value.getTotalPages());
        gen.writeNumberField("size", value.getSize());
        gen.writeFieldName("content");
        provider.defaultSerializeValue(value.getContent(), gen);
        gen.writeEndObject();
    }

}
