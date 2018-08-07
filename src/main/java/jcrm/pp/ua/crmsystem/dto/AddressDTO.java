package jcrm.pp.ua.crmsystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO extends BaseObjDTO implements Serializable{

    private Long id;
    private String city;
    private String address;
//    @JsonBackReference(value = "client-addresses")
//    private BaseClientDTO client;


}
