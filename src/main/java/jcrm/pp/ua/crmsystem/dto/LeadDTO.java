package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class LeadDTO extends BaseBusinessObjDTO implements Serializable{

    @JsonProperty("leadname")
    private String leadName;

    @JsonProperty("leadstatus")
    private String leadStatus;

    private Long budget;

    //@JsonBackReference(value = "client-leads")
    //private BaseClientDTO client;
}
