package itspring.controllers;

import java.security.Principal;

import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        if (principal == null) {
            return "homeNotSignedIn";
        } else {
            model.addAttribute("username", principal.getName());
            return "homeSignedIn";
        }
    }
}
