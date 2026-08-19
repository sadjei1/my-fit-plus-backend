package com.example.my_fit_plus_backend.Repository;


import com.example.my_fit_plus_backend.Model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {

}