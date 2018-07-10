package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jcrm.pp.ua.crmsystem.json.serializers.ContactDTOSerializer;
import jcrm.pp.ua.crmsystem.json.serializers.ListOfContactDTO;
import jcrm.pp.ua.crmsystem.json.serializers.Views;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property ="id", scope = ContactDTO.class)
@JsonTypeName(value = "company")
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id","fullname","phones","emails","addresses","leads","tasks","contacts"})
@Data
public class CompanyDTO extends BaseClientDTO { //implements ClientDTOInt {

//    private Long id;

    private String fullname;

    //@JsonManagedReference(value = "contact-company")
//    @JsonSerialize(using = ListOfContactDTO.class)
    private List<ContactDTO> contacts = new ArrayList<>();


}
