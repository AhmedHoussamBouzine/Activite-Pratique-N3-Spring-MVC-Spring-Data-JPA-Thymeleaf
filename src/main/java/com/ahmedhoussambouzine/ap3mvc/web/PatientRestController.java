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

import java.util.Date;
import java.util.List;

@Controller
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int  page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page,size));
        return "patients";
    }
    @GetMapping(path = "/deletePatient")
    public String delete(Long id,int page,String keyword ){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

}