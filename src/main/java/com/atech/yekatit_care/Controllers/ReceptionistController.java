package com.atech.yekatit_care.Controllers;


import com.atech.yekatit_care.Domains.LabResult;
import com.atech.yekatit_care.Domains.LabTest;
import com.atech.yekatit_care.Domains.Patient;
import com.atech.yekatit_care.Repositories.LabResultRepository;
import com.atech.yekatit_care.Repositories.LabTestRepository;
import com.atech.yekatit_care.Repositories.PatientRepository;
import com.atech.yekatit_care.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    LabTestRepository labTestRepository;

    @Autowired
    LabResultRepository labResultRepository;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public ModelAndView Home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patient",new Patient());
        modelAndView.addObject("doctorList", userService.getDoctors());
        modelAndView.setViewName("Receptionist/home");
        return modelAndView;
    }
    @GetMapping("/patientlist")
    public String Profile(Map<String, Object>map) {
        map.put("patients", patientRepository.findPatientsByOrderByName());
        return "Receptionist/patientlist";
    }
    @PutMapping("/editPatient")
        public String EditPage(){
        return "redirect:/editPatient";
        }

    @PostMapping("/editPatient/{id}")
    public String UpdatePatient(@PathVariable int id){
        Patient patient = patientRepository.findById(id);
        patientRepository.save(patient);
        return "redirect:/editPatient";
    }
    @DeleteMapping("/patientlist/{id}")
    public String deletePatient(@PathVariable int id){
        patientRepository.deleteById(id);

        LabTest labTest = labTestRepository.findByPatient_id(id);
        if(labTest != null) {
            labTestRepository.delete(labTest);
        }

        LabResult labResult = labResultRepository.findByTestRequest_id(labTest.getTest_id());
        if(labResult != null) {
            labResultRepository.delete(labResult);
        }

        return "redirect:/receptionist/patientlist";
    }





}
