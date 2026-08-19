package com.example.my_fit_plus_backend.Controller;

import com.example.my_fit_plus_backend.Model.Workout;
import com.example.my_fit_plus_backend.Service.WorkoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class WorkoutController {

    WorkoutService workoutService;

    private  WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/api/workout/allWorkouts")
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/api/workout/{id}")
    public Workout getWorkout(@PathVariable UUID workoutID) {
        return workoutService.getWorkout(workoutID);
    }

    @PostMapping("/api/workout/save")
    public Workout createWorkout(@RequestBody Workout workout) {
        return workoutService.createWorkout(workout);
    }

    @DeleteMapping("/api/workout/delete")
    public void deleteWorkout(UUID workoutId) {
        workoutService.deleteWorkout(workoutId);
    }

}
