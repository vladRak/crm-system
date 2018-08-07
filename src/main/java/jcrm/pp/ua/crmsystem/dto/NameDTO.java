package jcrm.pp.ua.crmsystem.dto;


import lombok.Data;

@Data
public class NameDTO {

//    @JsonBackReference(value = "contact-name")
//    private ContactDTO contact;
    //private Long id;
    private String firstname;
    private String surname;

}
