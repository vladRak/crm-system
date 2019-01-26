package jcrm.pp.ua.crmsystem.entities;

import lombok.Data;

@Data
public class EmbededPhone {
    private String phone;


    public EmbededPhone(){
    }

    public EmbededPhone(String phone){
        this.phone = phone;
    }
}
