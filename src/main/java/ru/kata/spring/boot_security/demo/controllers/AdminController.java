package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.configs.service.DaoService;
import ru.kata.spring.boot_security.demo.configs.service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import java.security.Principal;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final DaoService daoService;
    private final UserService userService;

    @Autowired
    public AdminController(DaoService daoService, UserService userService) {
        this.daoService = daoService;
        this.userService = userService;
    }

    @GetMapping
    public String showUser(Model model, Principal principal) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("users", daoService.getAllUsers());
        return "admin";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        daoService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer userId, @ModelAttribute("user") User user) {
        daoService.updateUser(userId, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer userId) {
        daoService.removeUser(userId);
        return "redirect:/admin";
    }
}
