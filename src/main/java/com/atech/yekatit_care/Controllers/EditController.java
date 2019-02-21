package com.atech.yekatit_care.Controllers;

import com.atech.yekatit_care.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class EditController {

    @Autowired
    private UserService userService;

    @GetMapping("/edit")
    public String Edit() {
        return "edit";
    }
    @PostMapping("admin/edit/{id}")
    public ModelAndView createNewUser(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.updateUser(id);
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }
}
