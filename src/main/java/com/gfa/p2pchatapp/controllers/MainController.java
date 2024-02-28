package com.gfa.p2pchatapp.controllers;

import com.gfa.p2pchatapp.models.User;
import com.gfa.p2pchatapp.services.LogService;
import com.gfa.p2pchatapp.services.MessageService;
import com.gfa.p2pchatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

@Controller
public class MainController {

    private final UserService userService;
    private final MessageService messageService;
    private final LogService logService;

    @Autowired
    public MainController(UserService userService, MessageService messageService, LogService logService) {
        this.userService = userService;
        this.messageService = messageService;
        this.logService = logService;
    }

    @GetMapping({"/", ""})
    public String mainPage(Model model) throws Exception {
        if (userService.getAllUsers().size() == 1) {
            model.addAttribute("name", userService.getName());
            model.addAttribute("messages", messageService.getAllMessages());
            return "mainpage";
        } else return "register";
    }

    @PostMapping("/change-name")
    public String changeName(@RequestParam(required = false) String name, Model model) throws Exception {
        if (!StringUtils.isEmpty(name)) {
            userService.changeName(name);
            model.addAttribute("name", name);
            model.addAttribute("nameChanged", "Name changed!");
            logService.log("/change-name", "POST", "name=" + name);
        } else  {
            model.addAttribute("error", "The username field is empty.");
            logService.logError("/change-name", "POST", "name=" + name);
        }
        model.addAttribute("messages", messageService.getAllMessages());
        return "mainpage";
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam(required = false) String text, Model model) throws Exception {
        if (!StringUtils.isEmpty(text)) {
            messageService.addMessage(text);
            logService.log("/send-message", "POST", "text=" + text);
        } else {
            model.addAttribute("error", "Textfield is empty.");
            logService.logError("/send-message", "POST", "text=" + text);
        }
        model.addAttribute("messages", messageService.getAllMessages());
        return "mainpage";
    }

    @GetMapping("/register")
    public String getRegister(Model model) throws Exception {
        if (userService.getAllUsers().size() == 1) {
            model.addAttribute("name", userService.getName());
            model.addAttribute("messages", messageService.getAllMessages());
            return "mainpage";
        } else return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, Model model) {
        if (name != null && !name.isEmpty()) {
            userService.addUser(new User(name));
            logService.log("/register", "POST", "name=" + name);
            return "redirect:/";
        } else {
            model.addAttribute("error", "The username field is empty.");
            logService.logError("/register", "POST", "name=" + name);
            return "register";
        }
    }
}
