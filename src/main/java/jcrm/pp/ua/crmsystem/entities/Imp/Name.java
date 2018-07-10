package jcrm.pp.ua.crmsystem.entities.Imp;

import lombok.Data;
import java.io.Serializable;

@Data
public class Name implements Serializable{

    private String firstname;
    private String surname;

    public Name(){
    }

    public Name(String firstname, String surname){
        this.firstname = firstname;
        this.surname = surname;
    }
}
