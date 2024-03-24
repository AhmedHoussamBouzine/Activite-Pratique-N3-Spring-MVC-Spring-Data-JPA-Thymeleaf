package com.ahmedhoussambouzine.ap3mvc.web;

import com.ahmedhoussambouzine.ap3mvc.entities.Patient;
import com.ahmedhoussambouzine.ap3mvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String index(Model model,@RequestParam( name = "page", defaultValue = "0") int page , @RequestParam(name = "size" , defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
        model.addAttribute("patients", pagePatients);
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping(path = "/delete")
    public String delete(Long id,String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @GetMapping (path = "/editPatient")
    public String editPatient(Model model, Long id,String keyword, int page){
        Patient p = patientRepository.findById(id).get();
        if (p == null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient", p);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "editPatient";
    }
    @PostMapping(path = "/save")
    public String save(Model model, @Valid Patient patient
            , BindingResult bindingResult ,@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

}
