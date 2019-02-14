package ua.pp.jcrm.domain.entity.impl;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "privilege")
@ToString(exclude = "roles")
public class Privilege implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles = new ArrayList<>();

    @Override
    public String getAuthority() {
        return this.name;
    }
}
