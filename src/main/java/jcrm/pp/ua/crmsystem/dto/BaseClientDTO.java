package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContactDTO.class, name = "contact"),
        @JsonSubTypes.Type(value = CompanyDTO.class, name = "company"),
        @JsonSubTypes.Type(value = UserDTO.class, name = "user")
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
//@JsonTypeName(value = "client")
public class BaseClientDTO extends BaseBusinessObjDTO implements Serializable{

    //private Long id;
    //@JsonManagedReference(value = "client-phones")
    private List<PhoneDTO> phones = new ArrayList<>();
    //@JsonManagedReference(value = "client-emails")
    private List<EmailDTO> emails = new ArrayList<>();
    //@JsonManagedReference(value = "client-addresses")
    private List<AddressDTO> addresses = new ArrayList<>();
    //@JsonManagedReference(value = "client-leads")
    private List<LeadDTO> leads = new ArrayList<>();

}
