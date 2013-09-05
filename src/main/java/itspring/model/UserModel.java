package itspring.model;

import itspring.domain.Role;
import itspring.domain.User;
import itspring.validator.PasswordIfNotEmpty;
import itspring.validator.UniqueLogin;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Andrey Volkov
 */
@UniqueLogin //unique login annotated here because we need to know both id and new login to bypass when login is not changed
public class UserModel {
    private static final String DEFAULT_AVATAR = "default.png";

    private Long id;

    @NotEmpty
    private String name;

    @PasswordIfNotEmpty
    private String password;
    
    @NotEmpty
    private String login;

    private String avatar;

    private Date lastLogin;

    private boolean online;

    private Date createdDate;

    private Date lastModifiedDate;

    private boolean enabled;

    @NotEmpty
    private List<RoleModel> roles;

    public UserModel() {
    }

    public UserModel(User user) {
        id = user.getId();
        name = user.getName();
        login = user.getLogin();
        avatar = user.getAvatar() != null ? user.getAvatar() : DEFAULT_AVATAR;
        lastLogin = user.getLastLoginDate();
        online = user.isOnline();
        createdDate = user.getCreatedDate();
        lastModifiedDate = user.getLastModifiedDate();
        enabled = user.isEnabled();

        roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(new RoleModel(role));
        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }
}
