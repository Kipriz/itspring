package itspring.controllers;

import com.google.common.collect.Lists;
import itspring.domain.Role;
import itspring.domain.User;
import itspring.model.ErrorModel;
import itspring.model.UserModel;
import itspring.repositories.RoleRepository;
import itspring.services.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Andrey Volkov
 */
@Controller
@RequestMapping("/admin/api/users")
@Secured("ROLE_ADMIN")
public class UserAdminApiController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        for (User user : userService.findAll()) {
            users.add(new UserModel(user));
        }
        return users;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public UserModel updateUser(@RequestBody @Valid UserModel userModel) {

        User updatedUser = userService.save(userModel);

        UserModel updatedUserModel = new UserModel(updatedUser);

        return updatedUserModel;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public UserModel getUser(@PathVariable Long userId) {
        return new UserModel(userService.findById(userId));
    }

    @RequestMapping(value = "/{userId}/changeAvatar", method = RequestMethod.POST)
    @ResponseBody
    public String changeAvatar(@PathVariable Long userId,
                               @RequestParam("file") MultipartFile file,
                               HttpSession session) {
        String extentsion = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString() + "." + extentsion;
        Path path = Paths.get(session.getServletContext().getRealPath("/resources/avatars/" + newFileName));
        try {
            Files.deleteIfExists(path);
            Files.copy(file.getInputStream(), path);
            User user = userService.findById(userId);
            user.setAvatar(path.getFileName().toString());
            user.setLastModifiedDate(new Date());
            userService.save(user);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public UserModel disableUser(@RequestBody UserModel userModel,
                                 HttpServletRequest request) {
        return new UserModel(userService.disableUser(userModel.getId()));
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public UserModel enableUser(@RequestBody UserModel userModel,
                                HttpServletRequest request) {
        return new UserModel(userService.enableUser(userModel.getId()));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@RequestParam("id") Long userId,
                           HttpServletRequest request) {
        userService.deleteUser(userId);
    }

    /* Validation exception handler */
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
