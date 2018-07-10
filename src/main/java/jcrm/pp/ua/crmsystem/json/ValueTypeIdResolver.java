package jcrm.pp.ua.crmsystem.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import jcrm.pp.ua.crmsystem.dto.BaseClientDTO;
//import jcrm.pp.ua.crmsystem.dto.ClientDTOInt;

import java.io.IOException;

public class ValueTypeIdResolver extends TypeIdResolverBase {

    public ValueTypeIdResolver() {    }

    public String idFromValue(Object value) {  return "a";    }

    public String idFromValueAndType(Object value, Class<?> suggestedType) { return "a"; }

    public JsonTypeInfo.Id getMechanism() {  return JsonTypeInfo.Id.NAME;  }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        return context.getConfig().constructType(BaseClientDTO.class);
    }
}
