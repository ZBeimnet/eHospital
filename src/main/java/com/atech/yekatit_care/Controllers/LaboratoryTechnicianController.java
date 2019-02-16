package com.atech.yekatit_care.Controllers;

import com.atech.yekatit_care.Domains.LabTest;
import com.atech.yekatit_care.Domains.Patient;
import com.atech.yekatit_care.Domains.User;
import com.atech.yekatit_care.Repositories.LabTestRepository;
import com.atech.yekatit_care.Repositories.PatientRepository;
import com.atech.yekatit_care.Repositories.TestRepository;
import com.atech.yekatit_care.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/laboratory")
public class LaboratoryTechnicianController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private UserService userService;



    @GetMapping("/home")
    public String sentRequests(Model model){

        Iterable<LabTest> labTests = labTestRepository.findAll();
        HashMap<Integer, String> patientNames = new HashMap<>();
        HashMap<String, String> doctorNames = new HashMap<>();

        for (LabTest labTest:
                labTests) {
            Patient patient = patientRepository.findById(labTest.getPatient_id());
            patientNames.put(labTest.getPatient_id(), patient.getName());

            User doctor = userService.findUserByEmail(labTest.getDoctor_email());
            doctorNames.put(labTest.getDoctor_email(), doctor.getName());
        }

        model.addAttribute("doctorNames", doctorNames);
        model.addAttribute("labTests", labTests);
        model.addAttribute("patientNames", patientNames);


        return "LabTechnician/home";
    }
}
