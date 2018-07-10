package jcrm.pp.ua.crmsystem.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskDTO extends BaseObjDTO implements Serializable{

    private Long id;
//    @JsonBackReference(value = "lead-tasks")
//    private LeadDTO lead;

    //private User createdBy;
    //private ClientInt createdFor;

    @JsonProperty("taskname")
    private String taskName;

    @JsonProperty("taskdate")

//    @JsonFormat(
//            shape = JsonFormat.Shape.NATURAL,
//            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
//            )
    private Date taskDate;
//    @JsonBackReference(value = "client-tasks")
//    private BaseBusinessObjDTO createdFor;

//    public String getTaskDate(){
//        DateFormat TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateTime = TIMESTAMP.format(taskDate);
//        return dateTime;
//    }

}
