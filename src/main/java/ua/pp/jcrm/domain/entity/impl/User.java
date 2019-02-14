package ua.pp.jcrm.domain.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.pp.jcrm.domain.entity.BaseTaskTargetImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.NotAudited;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "user")
@DiscriminatorValue("user")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class User extends BaseTaskTargetImpl {

    private static final long serialVersionUID = 1L;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    @JsonIgnore
    private String password;

    private boolean enabled = true;

    @NotAudited
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private ContactPerson contactPerson;

//    @Builder
//    public User(
//            List<Task> tasks, String username,
//            String password, boolean enabled,
//            List<Role> roles, Contact contact) {
//        super(tasks);
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.roles = roles;
//        this.contact = contact;
//    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }


    @Override
    protected void setAccount(Account account, boolean add) {
        if (nonNull(account) && add) {
            account.addUser(this, false);
        } else if (nonNull(this.account)) {
            this.account.getUsers().remove(this);
        }

        this.account = account;
    }
}
