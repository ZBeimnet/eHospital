package com.atech.yekatit_care.Controllers;


import com.atech.yekatit_care.Domains.LabTest;
import com.atech.yekatit_care.Domains.Patient;
import com.atech.yekatit_care.Domains.Test;
import com.atech.yekatit_care.Repositories.LabTestRepository;
import com.atech.yekatit_care.Repositories.PatientRepository;
import com.atech.yekatit_care.Repositories.TestRepository;
import com.atech.yekatit_care.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private LabTestRepository labTestRepository;


    @GetMapping("/home")
    public String doctorHome(Model model) {

        Iterable<Patient> allPatients = patientRepository.findAll();

        model.addAttribute("patients", allPatients);

        return "Doctor/home";
    }

    @GetMapping("/sentrequests")
    public String sentRequests(){
        return "Doctor/sentrequests";
    }

    @GetMapping("/labresults")
    public String labResults() {
        return "Doctor/labresults";
    }

    @GetMapping("/sendlabr/{id}")
    public String sendLabR(@PathVariable int id, Model model) {

        Patient patient = patientRepository.findById(id);
        Iterable<Test> tests = testRepository.findAll();

        model.addAttribute("tests", tests);
        model.addAttribute("patient", patient);
        model.addAttribute("labTest", new LabTest());

        return "Doctor/sendlabr";
    }

    @PostMapping("/sendlabr/{id}")
    public String processLabRequest(@Valid LabTest labTest, Errors errors, @PathVariable int id, Principal principal) {

        if (errors.hasErrors()) {
            return "Doctor/sendlabr";
        }

//        patientRepository.deleteById(id);
//        int testId = labTest.getTest_id();
        List<Test> lab_requests = labTest.getLab_request();

        LabTest labTestToBeSaved = new LabTest();

        labTestToBeSaved.setLab_request(lab_requests);
        labTestToBeSaved.setPatient_id(id);
        labTestToBeSaved.setDoctor_email(principal.getName());

        labTestRepository.save(labTestToBeSaved);


        return "redirect:/doctor/home";
    }
}
