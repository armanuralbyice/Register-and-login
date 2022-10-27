package com.alby.registerandloginform.controller;

import com.alby.registerandloginform.model.User;
import com.alby.registerandloginform.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Signup Page");
        model.addAttribute("user", new User());
        return "signup";
    }
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, Model model){
        user.setEnable(true);
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        System.out.println("USER "+user);
        model.addAttribute("user", new User());
        return "signup";
    }
    @RequestMapping("/signin")
    public String login(Model model){
        model.addAttribute("title","Login Page");
        return "signin";
    }
}
