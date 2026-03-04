package com.ewelinabudziak.glowup_tracker.habit.controller;


import com.ewelinabudziak.glowup_tracker.habit.dto.HabitCreateRequest;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitResponse;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitUpdateRequest;
import com.ewelinabudziak.glowup_tracker.habit.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitResponse> createHabit(
            @PathVariable Long userId,
            @Valid @RequestBody HabitCreateRequest habitCreateRequest
            ) {
        HabitResponse created = habitService.createHabit(userId, habitCreateRequest);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long userId, @PathVariable Long habitId) {
        habitService.deleteHabit(userId, habitId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<HabitResponse> updateHabit(@PathVariable Long userId, @PathVariable Long habitId, @Valid @RequestBody HabitUpdateRequest habitUpdateRequest) {
        HabitResponse updated = habitService.updateHabit(userId, habitId, habitUpdateRequest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> listHabits(@PathVariable Long userId) {
        return ResponseEntity.ok(habitService.listOfHabits(userId));
    }
}
