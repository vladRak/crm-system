package jcrm.pp.ua.crmsystem.dto;

import java.util.ArrayList;
import java.util.List;

public class ListOfConDTO extends ArrayList<BaseClientDTO>{
    private ListOfConDTO listOfConDTO;

    public ListOfConDTO(){}

    public ListOfConDTO(List<BaseClientDTO> var1) {
        this.listOfConDTO = new ListOfConDTO();
        this.listOfConDTO.addAll(var1);
    }
    public ListOfConDTO getContent() {
        return listOfConDTO;
    }
}
