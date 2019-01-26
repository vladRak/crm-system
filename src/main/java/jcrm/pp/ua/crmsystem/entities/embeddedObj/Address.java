package jcrm.pp.ua.crmsystem.entities.embeddedObj;

import lombok.Data;

@Data
public class Address {
    private String country;
    private String city;
    private String street;
    private String building;
    private String room;

    public Address() {
    }

    public Address(String country, String city, String street,
                   String building, String room) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.room = room;
    }
}
