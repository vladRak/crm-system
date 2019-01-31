package jcrm.pp.ua.crmsystem.domain.entity;

import jcrm.pp.ua.crmsystem.domain.BaseClientImplImpl;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contact")
//@DiscriminatorValue("contact")
@Data
//@Audited
@EqualsAndHashCode(callSuper = true)
public class Contact extends BaseClientImplImpl implements RootAware<Company> {

    private static final long serialVersionUID = 1L;

    @Embedded private Name fullName;

//    @Embedded private Set<EmbededPhone> phonesSet = new HashSet<>();

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date birthday;

    private String position;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "contact", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private User user;

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

