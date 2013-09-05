package itspring.controllers;

import com.google.common.collect.Lists;
import itspring.model.ErrorModel;
import itspring.model.UserModel;
import itspring.domain.Role;
import itspring.domain.User;
import itspring.repositories.RoleRepository;
import itspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.validation.Valid;
import javax.validation.ValidationException;
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

    @Autowired
    MessageSource messageSource;

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
    public UserModel updateUser(@RequestBody @Valid UserModel userModel) {

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

    @RequestMapping(value = "/api/users/{userId}/changeAvatar", method = RequestMethod.POST)
    @ResponseBody
    public String changeAvatar(@PathVariable Long userId,
                               @RequestParam("file") MultipartFile file) {
        return file != null ? "ok" : "failed";
    }


    /* Validation expcetion handler */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public List<ErrorModel> failedValidation(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorModel> errors = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setMessage(messageSource.getMessage(objectError.getCodes()[0], null, null));
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                errorModel.setField(fieldError.getField());
            }
            errors.add(errorModel);
        }
        return errors;
    }

}
