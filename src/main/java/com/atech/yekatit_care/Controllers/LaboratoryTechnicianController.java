package com.atech.yekatit_care.Controllers;

import com.atech.yekatit_care.Domains.*;
import com.atech.yekatit_care.Repositories.*;
import com.atech.yekatit_care.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/laboratory")
public class LaboratoryTechnicianController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private LabResultRepository labResultRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private ResultRepository resultRepository;

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

    @GetMapping("/sendlabresult/{testRequestId}")
    public String sendResult(@PathVariable int testRequestId, Model model) {

        LabTest labTest = labTestRepository.findByTest_id(testRequestId);
        List<Test> tests = labTest.getLab_request();
        model.addAttribute("testRequests", tests);
        model.addAttribute("testRequestId", testRequestId);
        model.addAttribute("labResult", new LabResult());

        return "LabTechnician/sendlabresult";
    }

    @PostMapping("/sendlabresult/{testRequestId}")
    public String processLabRequest(@Valid LabResult labResult, Errors errors, @PathVariable int testRequestId, Principal principal) {

        if (errors.hasErrors()) {
            return "LabTechnician/sendlabresult";
        }

        List<Result> lab_results = labResult.getLab_result();
        for (Result lab_result:
             lab_results) {
            resultRepository.save(lab_result);
        }

        LabResult labResultToBeSaved = new LabResult();

        labResultToBeSaved.setLab_result(lab_results);
        labResultToBeSaved.setTestRequest_id(testRequestId);
        labResultToBeSaved.setLabTechnician_name(principal.getName());

        labResultRepository.save(labResultToBeSaved);

        return "redirect:/laboratory/home";
    }
}
