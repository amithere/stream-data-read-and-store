package com.coderinside.service;

import com.coderinside.entity.RecentChange;
import com.coderinside.repository.RecentChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecentChangeService {

    @Autowired
    private RecentChangeRepository repository;

    public List<RecentChange> getAllRecentChanges() {
        return repository.findAll();
    }
}
