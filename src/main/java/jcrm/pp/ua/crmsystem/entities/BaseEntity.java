package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.entities.Imp.User;

import java.util.Date;

public interface BaseEntity {
    boolean deleted = false;
    User createdBy = new User();
    Date created = new Date();
    User updatedBy = new User();
    Date lastUpdate = new Date();
}
