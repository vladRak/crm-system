package jcrm.pp.ua.crmsystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailDTO extends BaseObjDTO implements Serializable {

    private Long id;
    private String email;
//    @JsonBackReference(value = "client-emails")
//    private BaseClientDTO client;
}
