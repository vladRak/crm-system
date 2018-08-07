package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jcrm.pp.ua.crmsystem.json.serializers.Views;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseObjDTO implements Serializable {

    @JsonView(Views.Admin.class)
    private boolean deleted = false;

    private UserDTO createdBy;
    public Date createdDate;
    private UserDTO lastModifiedBy;
    public Date lastModifiedDate;
}
