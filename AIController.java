package ai.healthcare.example.healthcare.controller;

import ai.healthcare.example.healthcare.service.AIService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@CrossOrigin
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/analyze")
public String analyze(@RequestBody Map<String, String> req) throws Exception {
    return aiService.getDiagnosis(req);
}

   
}