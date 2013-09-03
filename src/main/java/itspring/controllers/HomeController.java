package itspring.controllers;

import java.security.Principal;
import java.util.Collection;

import itspring.domain.User;
import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
            return "home/index";
        } else {
            return "redirect:/chooseHome";
        }
    }

    @RequestMapping(value = "/chooseHome", method = RequestMethod.GET)
    public String home(User user, Model model) {
        /* user is injected because it is principal */
        if (user.hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/profile";
        }
    }
}
