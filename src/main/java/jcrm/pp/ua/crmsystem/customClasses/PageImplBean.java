package jcrm.pp.ua.crmsystem.customClasses;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jcrm.pp.ua.crmsystem.dto.BaseClientDTO;
//import jcrm.pp.ua.crmsystem.dto.ClientDTOInt;
import jcrm.pp.ua.crmsystem.dto.ListOfConDTO;
import jcrm.pp.ua.crmsystem.json.serializers.PageSerializer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
//@JsonSerialize(using = PageSerializer.class)
public class PageImplBean extends PageImpl<BaseClientDTO>{

    private static final long serialVersionUID = 1L;
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private boolean previousPage;
    private boolean first;
    private boolean nextPage;
    private boolean last;
    private List content;
    @JsonIgnore
    private Sort sort;

    public PageImplBean() {
        super(new ArrayList<BaseClientDTO>());
    }

    public PageImplBean(Page pagina){
        this();
        //super(new ListOfConDTO(pagina.getContent()));
        this.content = new ListOfConDTO(pagina.getContent()).getContent();
        this.number = pagina.getNumber();
        this.size = pagina.getSize();
        this.totalPages = pagina.getTotalPages();
        this.numberOfElements = pagina.getNumberOfElements();
        this.totalElements = pagina.getTotalElements();
        this.previousPage = pagina.hasPrevious();
        this.first = pagina.isFirst();
        this.nextPage = pagina.hasNext();
        this.last = pagina.isLast();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(boolean previousPage) {
        this.previousPage = previousPage;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List getContent() {
        return content;
    }

    public void setContent(ListOfConDTO content) {
        this.content = content;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public PageImpl<BaseClientDTO> pageImpl() {
        return new PageImpl<BaseClientDTO>(getContent(), new PageRequest(getNumber(),
                getSize(), getSort()), getTotalElements());
    }
}