package itspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Andrey Volkov
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String login;

    @JsonIgnore // keep it here just in case, but use UserModel for serialization
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginData;

    private boolean online;


    public User() {
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User(String login, String password, String name, String avatar) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastLoginData() {
        return lastLoginData;
    }

    public void setLastLoginData(Date lastLoginData) {
        this.lastLoginData = lastLoginData;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(final String roleCode) {
        for (Role role : roles) {
            if (role.getCode().equals(roleCode)) {
                return true;
            }
        }
        return false;
    }

    public String[] getRoleNames() {
        String[] names = new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            names[i] = role.getName();
        }
        return names;
    }
}
