package test.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test.example.app.db.UsersDB;
import test.example.app.model.User;
import test.example.app.provider.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UsersDB usersDB;
    @Autowired
    private JwtProvider jwtProvider;
    public static final List<String> AUTH_PAGES = List.of("/login", "/sign-up");

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/login")
    public String signIn(
            @RequestParam("login") String login,
            @RequestParam("pass") String pass,
            Model model,
            HttpServletRequest req
    ) {
        boolean isValid = true;

        if (login.isEmpty()) {
            model.addAttribute("signInLoginError", "Login can not be empty");
            isValid = false;
        }

        if (pass.isEmpty()) {
            model.addAttribute("signInPassError", "Password can not be empty");
            isValid = false;
        }

        if (!isValid) {
            return "login";
        }

        User existingUser = usersDB.findByLoginAndPass(login, pass);

        if (existingUser == null) {
            model.addAttribute("signInAuthError", "Wrong login or password");

            return "login";
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("token", jwtProvider.generateToken(existingUser.getLogin()));

        return "redirect:/";
    }

    @PostMapping("/sign-up")
    public String register(
            @RequestParam("name") String name,
            @RequestParam("login") String login,
            @RequestParam("pass") String pass,
            @RequestParam("repeatPass") String repeatPass,
            Model model
    ) {
        boolean isValid = true;

        if (name.isEmpty()) {
            model.addAttribute("signUpNameError", "Please enter your name");
            isValid = false;
        }

        if (login.isEmpty()) {
            model.addAttribute("signUpLoginError", "Please enter login");
            isValid = false;
        }

        if (pass.isEmpty()) {
            model.addAttribute("signUpPassError", "Please enter password");
            isValid = false;
        }

        if (!pass.equals(repeatPass)) {
            model.addAttribute("signUpRepeatPassError", "Passwords do not match");
            isValid = false;
        }

        if (!isValid) {
            return "signUp";
        }

        boolean isUserExist = usersDB.create(name, login, pass);

        if (!isUserExist) {
            model.addAttribute("signUpAuthError", "User already exist");
            return "signUp";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        session.removeAttribute("token");

        return "redirect:/login";
    }
}
