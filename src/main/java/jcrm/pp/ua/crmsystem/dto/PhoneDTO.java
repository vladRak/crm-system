package jcrm.pp.ua.crmsystem.dto;

import lombok.Data;

import java.io.Serializable;



//@JsonSerialize(using = PhoneSerializer.class)
@Data
public class PhoneDTO extends BaseObjDTO implements Serializable{

    private Long id;
    private String phone;
//    @JsonBackReference(value = "client-phones")
//    private BaseClientDTO client;
}
