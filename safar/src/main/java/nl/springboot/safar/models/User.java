package nl.springboot.safar.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Student")
@Table(name = "user")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String username;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "is_admin",
            nullable = false
    )
    private boolean isAdmin;

    @Column(
            name = "is_deleted",
            nullable = false
    )
    private boolean isDeleted;

    @ManyToMany(
            targetEntity = Site.class,
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "user_site",
            joinColumns = {@JoinColumn(name="user_id")} ,
            inverseJoinColumns = {@JoinColumn(name="site_id")}
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Site> sites;

//    @Override
//    public Collection<SimpleGrantedAuthority> getAuthorities() {
//        String role = "User";
//        if(this.isAdmin){
//            role = "Admin";
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role));
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }

    //    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
