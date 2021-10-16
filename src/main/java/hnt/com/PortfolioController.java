package hnt.com;

import hnt.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {

    @Autowired
    UserService userService;

    @GetMapping(path = {"/index", "/"})
    public String index(Model model) {
        model.addAttribute("user", userService.getUserInformation("red", "12345"));
        return "index";
    }
}
