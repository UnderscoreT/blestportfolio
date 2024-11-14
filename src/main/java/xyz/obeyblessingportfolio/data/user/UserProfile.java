package xyz.obeyblessingportfolio.data.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String encryptedPassword;
    @NaturalId(mutable = true)
    private String email;

    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    private String createdOn;
    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    private String lastUpdated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_profile_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
    )

    private Set<Role> roles = new HashSet<>();

    public UserProfile(String email, String encryptedPassword, String username, Set<Role> roles) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.username = username;
        this.roles = roles;
    }

    public void addRole(Role roleUser) {
                this.roles.add(roleUser);

    }
}
