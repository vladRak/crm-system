package jcrm.pp.ua.crmsystem.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class NameDTO {

//    @JsonBackReference(value = "contact-name")
//    private ContactDTO contact;
    //private Long id;
    private String firstname;
    private String surname;

}
