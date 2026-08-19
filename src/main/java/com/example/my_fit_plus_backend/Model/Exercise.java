package com.example.my_fit_plus_backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    private UUID exerciseId = UUID.randomUUID();

    private String name;

    @Enumerated(EnumType.STRING)
    private WorkoutCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkoutSet> sets = new ArrayList<>();

    public void addSet(WorkoutSet set) {
        sets.add(set);
        set.setExercise(this);
    }

    public void removeSet(WorkoutSet set) {
        sets.remove(set);
        set.setExercise(null);
    }

}