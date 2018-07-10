package jcrm.pp.ua.crmsystem.entities.Imp;

import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contact")
@DiscriminatorValue("contact")
@Data
@Audited
@EqualsAndHashCode(callSuper = true)
public class Contact extends BaseClientImp implements RootAware<Company>, Serializable{

    private static final long serialVersionUID = 1L;

    @Embedded private Name fullName;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date birthday;

    private String position;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

    public Contact() {
    }

    public void setCompany(Company company){
        setCompany(company,true);
    }

    void setCompany(Company company, boolean add){
        this.company = company;
        if (company != null && add){
            company.addContact(this,false);
        }
    }

    @Override
    public Company root() {
        return company;
    }
}

