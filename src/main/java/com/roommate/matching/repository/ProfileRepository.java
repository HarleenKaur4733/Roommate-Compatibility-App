package com.roommate.matching.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.Profile;
import com.roommate.matching.entity.User;

@Repository
public interface ProfileRepository
        extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUser(User user);
}
