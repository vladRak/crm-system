package jcrm.pp.ua.crmsystem.entities.embeddedObj;

import lombok.Data;

@Data
public class Phone {

    private String phone;

    public Phone(){
    }

    public Phone(String phone){
        this.phone = phone;
    }
}
