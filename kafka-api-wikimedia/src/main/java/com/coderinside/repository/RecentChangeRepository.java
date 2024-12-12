package com.coderinside.repository;

import com.coderinside.entity.RecentChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentChangeRepository extends JpaRepository<RecentChange, Long> {
}
