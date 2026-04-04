package com.roommate.matching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.RoommateGroup;

@Repository
public interface RoommateGroupRepository extends JpaRepository<RoommateGroup, Long> {
}