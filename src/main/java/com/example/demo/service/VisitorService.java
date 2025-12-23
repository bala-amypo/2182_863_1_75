package com.example.demo.service;

import com.example.demo.model.*;
import java.util.*;

public interface VisitorService {
    Visitor createVisitor(Visitor visitor);
    Visitor getVisitor(Long id);
    List<Visitor> getAllVisitors();
}
