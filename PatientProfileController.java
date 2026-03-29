package ai.healthcare.example.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ai.healthcare.example.healthcare.entity.PatientProfile;
import ai.healthcare.example.healthcare.repository.PatientProfileRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/patients_profile") 
public class PatientProfileController {

    @Autowired
    private PatientProfileRepository repository;

    @PostMapping("/save")
    public PatientProfile saveProfile(@RequestBody PatientProfile profile) {
        return repository.save(profile);
    }
}