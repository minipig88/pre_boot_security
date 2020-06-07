package com.springboot.pre_boot.controller;

import com.springboot.pre_boot.model.User;
import com.springboot.pre_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userList")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/allUserList";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/userList";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", userService.getAllRoles());
        return "admin/editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUser(@Valid User user, String[] role, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editUser";
        }
        userService.editUser(user, role);
        return "redirect:/admin/userList";
    }

    @GetMapping(value = "/addUser")
    public String getAddUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@Valid User user, String[] role, BindingResult result) {
        if (result.hasErrors() || !userService.addUser(user, role)) {
            return "admin/addUser";
        }
        return "redirect:/admin/userList";
    }
}
