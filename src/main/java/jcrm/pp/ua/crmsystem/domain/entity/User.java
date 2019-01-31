package jcrm.pp.ua.crmsystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jcrm.pp.ua.crmsystem.domain.BaseTaskTargetImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.NotAudited;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
//@DiscriminatorValue("user")
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
    private Contact contact;

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


}
