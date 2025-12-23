package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.*;
import java.util.*;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor getVisitor(Long id) {
        return visitorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
}
