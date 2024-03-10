package com.ahmedhoussambouzine.ap3mvc;

import com.ahmedhoussambouzine.ap3mvc.entities.Patient;
import com.ahmedhoussambouzine.ap3mvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Ap3MvcApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Ap3MvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "Houssam", "Bouzine", new Date(), true, 10));
        patientRepository.save(new Patient(null, "Ahmed", "Bouzine",new Date(), false, 20));
        patientRepository.save(new Patient(null, "Bouzine","Bouzine", new Date(), true, 30));

    }
}
