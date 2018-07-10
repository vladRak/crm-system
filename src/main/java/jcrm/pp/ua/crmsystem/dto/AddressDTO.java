package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class AddressDTO extends BaseObjDTO implements Serializable{

    private Long id;
    private String city;
    private String address;
//    @JsonBackReference(value = "client-addresses")
//    private BaseClientDTO client;


}
