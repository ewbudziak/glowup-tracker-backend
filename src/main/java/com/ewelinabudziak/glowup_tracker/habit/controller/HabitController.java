package com.ewelinabudziak.glowup_tracker.habit.controller;


import com.ewelinabudziak.glowup_tracker.habit.dto.HabitCreateRequest;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitResponse;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitUpdateRequest;
import com.ewelinabudziak.glowup_tracker.habit.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitResponse> createHabit(
            @Valid @RequestBody HabitCreateRequest habitCreateRequest,
            Authentication authentication
    ) {
        String email = authentication.getName();
        HabitResponse created = habitService.createHabit(email, habitCreateRequest);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> listHabits(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(habitService.listOfHabits(email));
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<HabitResponse> updateHabit(
            @PathVariable Long habitId,
            @Valid @RequestBody HabitUpdateRequest habitUpdateRequest,
            Authentication authentication) {
        String email = authentication.getName();
        HabitResponse updated = habitService.updateHabit(email, habitId, habitUpdateRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId, Authentication authentication) {
        String email = authentication.getName();
        habitService.deleteHabit(email, habitId);
        return ResponseEntity.noContent().build();
    }
}
