package jcrm.pp.ua.crmsystem.entities.Imp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jcrm.pp.ua.crmsystem.entities.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
//@DiscriminatorValue("user")
@EqualsAndHashCode(callSuper = true)
@Audited
@Data
public class User extends Contact{

    private static final long serialVersionUID = 1L;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    @JsonIgnore
    private String password;

    private boolean enabled = true;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @NotAudited
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
