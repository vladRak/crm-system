package jcrm.pp.ua.crmsystem.entities.temp_test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
//@DiscriminatorValue("user")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class UserTemp extends AbstractTemp {

    private static final long serialVersionUID = 1L;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    @JsonIgnore
    private String password;

    private boolean enabled = true;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}
