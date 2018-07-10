package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;



//@JsonSerialize(using = PhoneSerializer.class)
@Data
public class PhoneDTO extends BaseObjDTO implements Serializable{

    private Long id;
    private String phone;
//    @JsonBackReference(value = "client-phones")
//    private BaseClientDTO client;
}
