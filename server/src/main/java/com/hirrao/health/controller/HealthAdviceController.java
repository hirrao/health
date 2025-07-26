package com.hirrao.health.controller;

import com.hirrao.health.common.response.HealthAdviceArticleListResponse;
import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import com.hirrao.health.common.response.Response;
import com.hirrao.health.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health-advice")
public class HealthAdviceController {

    private final HealthAdviceService healthAdviceService;

    @Autowired
    public HealthAdviceController(HealthAdviceService healthAdviceService) {
        this.healthAdviceService = healthAdviceService;
    }

    @GetMapping
    public ResponseEntity<Response<HealthAdviceArticleListResponse>> getByPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") long page) {
        return Response.ok(healthAdviceService.getByPage(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<HealthAdviceArticleResponse>> getById(
            @PathVariable Long id) {
        return Response.ok(healthAdviceService.getById(id));
    }
}
