package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@JsonTypeName(value = "user")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends ContactDTO implements Serializable{

    private String username;
    private String[] roles;
    private boolean isBlocked = false;

}
