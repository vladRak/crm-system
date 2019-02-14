package ua.pp.jcrm.domain.entity;

import ua.pp.jcrm.domain.entity.impl.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static java.util.Objects.nonNull;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "base_client")
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
//@Audited
//@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseContactInfo extends AbstractAccountContent {

    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "account")
    private Account account;

    @Override
    public void setAccount(Account account, boolean add) {
        if (nonNull(account) && add) {
            account.addContactInfo(this, false);
        } else if (nonNull(this.account)) {
            this.account.getContactsInfo().remove(this);
        }

        this.account = account;
    }
}
