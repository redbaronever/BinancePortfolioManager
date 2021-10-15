package hnt.com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioController {
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "sadas");
        return "index";
    }
}
