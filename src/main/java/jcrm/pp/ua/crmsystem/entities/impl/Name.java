package jcrm.pp.ua.crmsystem.entities.impl;

import lombok.Data;

import java.io.Serializable;

@Data
public class Name implements Serializable{

    private String firstName;
    private String surname;

    public Name(){
    }

    public Name(String firstName, String surname){
        this.firstName = firstName;
        this.surname = surname;
    }
}
