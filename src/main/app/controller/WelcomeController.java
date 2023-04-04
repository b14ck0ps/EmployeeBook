package main.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Azran Hossain");
        return "welcome";
    }

}