package com.roommate.matching.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.LifestylePreferences;
import com.roommate.matching.entity.User;

@Repository
public interface LifestylePreferencesRepository
        extends JpaRepository<LifestylePreferences, Long> {

    Optional<LifestylePreferences> findByUser(User user);
}