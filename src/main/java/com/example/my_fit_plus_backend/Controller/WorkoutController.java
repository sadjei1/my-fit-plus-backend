package com.example.my_fit_plus_backend.Controller;

import com.example.my_fit_plus_backend.Model.Workout;
import com.example.my_fit_plus_backend.Service.WorkoutService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://myfitplus.vercel.app"})
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
    public Workout createWorkout(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody Workout workout) {

        UUID supabaseUserId = UUID.fromString(jwt.getSubject());

        workout.setUserId(supabaseUserId);

        return workoutService.createWorkout(workout);
    }

    @DeleteMapping("/api/workout/delete")
    public void deleteWorkout(UUID workoutId) {
        workoutService.deleteWorkout(workoutId);
    }

}
