package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
