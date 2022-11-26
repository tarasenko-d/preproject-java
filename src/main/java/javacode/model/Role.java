package javacode.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@ToString
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    public Role(Long id) {
        this.id = id;
    }

    public Role(RolesEnum name) {
        this.name = name;
    }

    public Role(Long id, RolesEnum name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName().toString();
    }

}