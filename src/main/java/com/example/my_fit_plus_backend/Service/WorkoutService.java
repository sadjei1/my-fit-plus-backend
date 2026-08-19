package com.example.my_fit_plus_backend.Service;
import com.example.my_fit_plus_backend.Model.Exercise;
import com.example.my_fit_plus_backend.Model.Workout;
import com.example.my_fit_plus_backend.Model.WorkoutSet;
import com.example.my_fit_plus_backend.Repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout getWorkout(UUID workoutID) {
        return workoutRepository.findById(workoutID)
                .orElseThrow(() ->
                        new RuntimeException("Workout not found"));
    }

    public void deleteWorkout(UUID workoutID) {
         workoutRepository.deleteById(workoutID);

    }

    public Workout createWorkout(Workout workout) {
        if (workout.getExercises() != null) {
            for (Exercise exercise : workout.getExercises()) {
                exercise.setWorkout(workout);

                if (exercise.getSets() != null) {
                    for (WorkoutSet set : exercise.getSets()) {
                        set.setExercise(exercise);
                    }
                }
            }
        }
        return workoutRepository.save(workout);
    }



}
