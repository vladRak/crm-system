package jcrm.pp.ua.crmsystem.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class BaseBusinessObjDTO extends BaseObjDTO implements Serializable {

    private Long id;
    //@JsonManagedReference(value = "client-tasks")
    private List<TaskDTO> tasks = new ArrayList<>();

}

