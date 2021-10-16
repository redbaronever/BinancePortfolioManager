package hnt.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticatedController {
    @GetMapping("/portfolioLogin")
    public String index(Model model) {
        return "login";
    }
}
