package com.codeshuttle.uber.repositories;

import com.codeshuttle.uber.entities.Rider;
import com.codeshuttle.uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {
    Optional<Rider> findByUser(User user);
}
