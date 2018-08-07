package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jcrm.pp.ua.crmsystem.json.serializers.CompanyDTOSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;



@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property ="id", scope = ContactDTO.class)
@JsonPropertyOrder({"id","fullname","company","position","phones","emails","addresses","leads","tasks"})
@JsonTypeName(value = "contact")
@EqualsAndHashCode(callSuper = true)
@Data
public class ContactDTO extends BaseClientDTO {//implements ClientDTOInt{

//    private Long id;

    //@JsonManagedReference(value = "contact-name")
    @JsonProperty("fullname")
    private NameDTO fullName;

    private String position;

    @JsonSerialize(using = CompanyDTOSerializer.class)
    //@JsonBackReference(value = "contact-company")
    private CompanyDTO company;

}
