package test.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import test.example.app.db.UsersDB;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UsersDB usersDB;
    public static final List<String> APP_PAGES = List.of("/app", "/users");

    @GetMapping("/app")
    public String getMainPage() {
        return "main";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", usersDB.findAll());

        return "users";
    }

}
