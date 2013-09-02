package itspring.controllers;

import itspring.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrey Volkov
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String adminHome(User user, Model model) {
        return "adminHome";
    }
}
