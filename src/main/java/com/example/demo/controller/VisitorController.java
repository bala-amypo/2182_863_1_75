package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<Visitor> create(@RequestBody Visitor visitor) {
        return ResponseEntity.ok(visitorService.createVisitor(visitor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> get(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.getVisitor(id));
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> all() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }
}
