package com.hirrao.health.controller;

import com.hirrao.health.common.request.HealthAdviceArticleCreateRequest;
import com.hirrao.health.common.response.HealthAdviceArticleListResponse;
import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import com.hirrao.health.common.response.Response;
import com.hirrao.health.common.utils.UserUtil;
import com.hirrao.health.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping
    @PreAuthorize("@permissionService.admin")
    public ResponseEntity<Response<String>> createArticle(
            @RequestBody HealthAdviceArticleCreateRequest request) {
        var user = UserUtil.get();
        return Response.ok(healthAdviceService.createArticle(request.title(), request.content(),
                                                             request.image(), user.uid()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@permissionService.admin")
    public ResponseEntity<Response<String>> updateArticle(@PathVariable Long id,
                                                          @RequestBody HealthAdviceArticleCreateRequest request) {
        healthAdviceService.updateArticle(id, request.title(),
                                          request.content(), request.image());
        return Response.ok();
    }

    @PostMapping("/image")
    @PreAuthorize("@permissionService.admin")
    public ResponseEntity<Response<String>> uploadImage(
            @RequestParam("file") MultipartFile file) {
        return Response.ok(healthAdviceService.uploadImage(file));
    }
}

