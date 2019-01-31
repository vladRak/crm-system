package jcrm.pp.ua.crmsystem.domain.entity;

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
