package nl.springboot.safar.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

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

    //letters and spaces only
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    //Letters numbers and spaces
    @Pattern(regexp = "^[A-Za-z0-9\\h@]*$")
    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;

    //Letters numbers upper and under-score
    @Pattern(regexp = "^[A-Za-z0-9_-]*$")
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String username;

    @Email
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

    public User(Integer id, String name, String address, String username, String email, String password, boolean isAdmin, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDeleted = isDeleted;
    }

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
