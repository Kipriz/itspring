package itspring.controllers;

import com.google.common.collect.Lists;
import itspring.model.ErrorModel;
import itspring.model.UserModel;
import itspring.domain.Role;
import itspring.domain.User;
import itspring.repositories.RoleRepository;
import itspring.services.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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

import javax.annotation.processing.FilerException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;
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
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String adminHome(User user, Model model) {
        return "adminHome";
    }

    @RequestMapping(value = "/api/roles", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }
}
