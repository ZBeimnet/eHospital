package com.atech.yekatit_care.Controllers;


import com.atech.yekatit_care.Domains.LabTest;
import com.atech.yekatit_care.Domains.Patient;
import com.atech.yekatit_care.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/home")
    public String doctorHome(Model model) {

        Iterable<Patient> allPatients = patientRepository.findAll();

        model.addAttribute("patients", allPatients);

        return "Doctor/home";
    }

    @GetMapping("/sendlabr/{id}")
    public String sendLabR(@PathVariable int id, Model model) {

        Patient patient = patientRepository.findById(id);

        model.addAttribute("patient", patient);

        model.addAttribute("labTest", new LabTest());

        return "Doctor/sendlabr";
    }
}
