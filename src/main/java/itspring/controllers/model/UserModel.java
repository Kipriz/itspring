package itspring.controllers.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import itspring.domain.Role;
import itspring.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Volkov
 */
public class UserModel {
    private Long id;
    private String name;
    private String password;
    private String login;

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
