package itspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrey Volkov
 */
@Controller
public class AngularPartialsController {

    @RequestMapping("/partials/{folder}/{template}.html")
    public String partials(@PathVariable String folder,
                           @PathVariable String template) {

        return String.format("partials/%s/%s", folder, template);
    }
}
