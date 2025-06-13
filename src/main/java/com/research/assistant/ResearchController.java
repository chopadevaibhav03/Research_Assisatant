package com.research.assistant;

import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/research")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ResearchController {
    private final ResearchService researchService;

    @PostMapping("/process")
    public RequestEntity<String> processContent(@RequestBody ResearchRequest request) {
        String result = researchService.processContent(request);

        return ResponseEntity.ok(result);
    }
}
