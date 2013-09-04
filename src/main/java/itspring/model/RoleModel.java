package itspring.model;

import itspring.domain.Role;

/**
 * @author Andrey Volkov
 */
public class RoleModel {
    private Long id;

    private String code;

    private String name;

    public RoleModel() {
    }

    public RoleModel(Role role) {
        this.id = role.getId();
        this.code = role.getCode();
        this.name = role.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
