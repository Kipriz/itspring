package itspring.controllers;

import com.google.common.collect.Lists;
import itspring.controllers.model.UserModel;
import itspring.domain.Role;
import itspring.domain.User;
import itspring.repositories.RoleRepository;
import itspring.repositories.UserRepository;
import itspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey Volkov
 */
@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String adminHome(User user, Model model) {
        return "adminHome";
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        for (User user : userService.findAll()) {
            users.add(new UserModel(user));
        }
        return users;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    @ResponseBody
    public UserModel updateUser(@RequestBody UserModel userModel) {

        return userModel;
    }

    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public UserModel getUser(@PathVariable Long userId) {
        return new UserModel(userService.findById(userId));
    }

    @RequestMapping(value = "/api/roles", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }

}
