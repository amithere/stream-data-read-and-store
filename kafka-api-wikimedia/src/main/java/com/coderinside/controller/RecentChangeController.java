package com.coderinside.controller;

import com.coderinside.entity.RecentChange;
import com.coderinside.service.RecentChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recent-changes")
public class RecentChangeController {

    @Autowired
    private RecentChangeService service;

    @GetMapping
    public List<RecentChange> getRecentChanges() {
        return service.getAllRecentChanges();
    }
}
