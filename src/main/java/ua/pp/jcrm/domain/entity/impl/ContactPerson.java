package ua.pp.jcrm.domain.entity.impl;

import ua.pp.jcrm.domain.entity.BaseClientImpl;
import ua.pp.jcrm.domain.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

import static java.util.Objects.*;

@Entity
@Table(name = "contactPerson")
@DiscriminatorValue("contactPerson")
@Data
//@Audited
@EqualsAndHashCode(callSuper = true)
public class ContactPerson extends BaseClientImpl implements RootAware<Company> {

    private static final long serialVersionUID = 1L;

    @Embedded
    private Name fullName;

//    @Embedded private Set<EmbededPhone> phonesSet = new HashSet<>();

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date birthday;

    private String position;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "contactPerson", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private User user;

    public ContactPerson() {
    }

    public void setCompany(Company company) {
        setCompany(company, true);
    }

    void setCompany(Company company, boolean add) {
        if (nonNull(company) && add) {
            company.addContact(this, false);
        } else if (nonNull(this.company)) {
            this.company.getContactPersons().remove(this);
        }

        this.company = company;
    }

//    public void setAuthor(Author author) {
//        // update association on Author entity
//        if (author != null) {
//            author.getBooks().add(this);
//        } else if (this.author != null) {
//            this.author.getBooks().remove(this);
//        }
//
//        this.author = author;
//    }

    @Override
    public Company root() {
        return company;
    }
}

