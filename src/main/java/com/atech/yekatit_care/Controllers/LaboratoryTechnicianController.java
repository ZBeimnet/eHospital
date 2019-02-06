package com.atech.yekatit_care.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/laboratory")
public class LaboratoryTechnicianController {

    @GetMapping("/home")
    public String lab() {
        return "LabTechnician/home";
    }
}
