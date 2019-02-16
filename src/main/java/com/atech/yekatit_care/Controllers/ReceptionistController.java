package com.atech.yekatit_care.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {

    @GetMapping("/home")
    public String receptionistHome() {
        return "Receptionist/home";
    }


}
