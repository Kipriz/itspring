package itspring.model;

import itspring.domain.Role;
import itspring.domain.User;
import itspring.validator.UniqueLogin;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Volkov
 */
@UniqueLogin //unique login annotated here because we need to know both id and new login to bypass when login is not changed
public class UserModel {
    private Long id;

    @NotEmpty
    private String name;

    private String password;
    
    @NotEmpty
    private String login;

    @NotEmpty
    private List<RoleModel> roles;

    public UserModel() {
    }

    public UserModel(User user) {
        id = user.getId();
        name = user.getName();
        login = user.getLogin();

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

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }
}
