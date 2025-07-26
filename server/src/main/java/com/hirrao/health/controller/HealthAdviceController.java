package com.hirrao.health.controller;

import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import com.hirrao.health.common.response.Response;
import com.hirrao.health.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-advice")
public class HealthAdviceController {

    private final HealthAdviceService healthAdviceService;

    @Autowired
    public HealthAdviceController(HealthAdviceService healthAdviceService) {
        this.healthAdviceService = healthAdviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<HealthAdviceArticleResponse>> getById(
            @PathVariable Long id) {
        return Response.ok(healthAdviceService.getById(id));
    }
}
