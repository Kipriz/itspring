package itspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * @author Andrey Volkov
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String adminHome(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "adminHome";
    }
}
