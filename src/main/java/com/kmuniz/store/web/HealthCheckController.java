package com.kmuniz.store.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        // Perform any necessary health check logic here
        // You can check the status of dependencies, databases, services, etc.

        // Return a response indicating the health status
        return ResponseEntity.ok("Application is running and healthy");
    }
}