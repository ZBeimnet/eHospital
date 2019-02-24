package com.atech.yekatit_care.Controllers;


import com.atech.yekatit_care.Domains.LabTest;
import com.atech.yekatit_care.Domains.Patient;
import com.atech.yekatit_care.Domains.Test;
import com.atech.yekatit_care.Domains.User;
import com.atech.yekatit_care.Repositories.LabTestRepository;
import com.atech.yekatit_care.Repositories.PatientRepository;
import com.atech.yekatit_care.Repositories.TestRepository;
import com.atech.yekatit_care.Repositories.UserRepository;
import com.atech.yekatit_care.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private UserService userService;


    @GetMapping("/home")
    public String doctorHome(Model model) {

        Iterable<Patient> allPatients = patientRepository.findAll();

        model.addAttribute("patients", allPatients);

        return "Doctor/home";
    }

    @GetMapping("/sentrequests")
    public String sentRequests(Model model, Principal principal){

        Iterable<LabTest> allLabTests = labTestRepository.findAll();
        List<LabTest> labTests = new ArrayList<>();
        HashMap<Integer, String> patientNames = new HashMap<>();
        User doctor = userService.findUserByEmail(principal.getName());

        for (LabTest labTest:
             allLabTests) {

            if(labTest.getDoctor_email().equals(doctor.getEmail())) {
                Patient patient = patientRepository.findById(labTest.getPatient_id());
                patientNames.put(labTest.getPatient_id(), patient.getName());

                labTests.add(labTest);
            }
        }

        model.addAttribute("doctor", doctor);
        model.addAttribute("labTests", labTests);
        model.addAttribute("patientNames", patientNames);

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

    @GetMapping("/sentrequests/edit/{id}/{test_id}")
    public String editLabRequest(@PathVariable int id, @PathVariable int test_id, Model model) {

        Patient patient = patientRepository.findById(id);
        Iterable<Test> tests = testRepository.findAll();

        model.addAttribute("tests", tests);
        model.addAttribute("patient", patient);
        model.addAttribute("labTest", new LabTest());
        model.addAttribute("test_id", test_id);

        return "Doctor/editsentrequests";
    }

    @PostMapping("/sentrequests/edit/{test_id}")
    public String processEditedLapReqest(@Valid LabTest labTest, Errors errors, @PathVariable int test_id,Principal principal){
        if (errors.hasErrors()) {
            return "Doctor/editsentrequests";
        }

//        patientRepository.deleteById(id);
//        int testId = labTest.getTest_id();
        List<Test> lab_requests = labTest.getLab_request();

        LabTest labTestToBeSaved = labTestRepository.findByTest_id(test_id);

        labTestToBeSaved.setLab_request(lab_requests);
        labTestToBeSaved.setPatient_id(labTestToBeSaved.getPatient_id());
        labTestToBeSaved.setDoctor_email(principal.getName());

        labTestRepository.save(labTestToBeSaved);

        return "redirect:/doctor/sentrequests";
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
