package com.ewelinabudziak.glowup_tracker.habit.controller;

import com.ewelinabudziak.glowup_tracker.habit.dto.HabitCheckinResponse;
import com.ewelinabudziak.glowup_tracker.habit.service.HabitCheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits/{habitId}/checkins")
public class HabitCheckinController {
    private final HabitCheckinService habitCheckinService;

    public HabitCheckinController(HabitCheckinService habitCheckinService) {
        this.habitCheckinService = habitCheckinService;
    }

    @PostMapping
    public ResponseEntity<HabitCheckinResponse> checkin(@PathVariable Long habitId, Authentication authentication) {
        String email = authentication.getName();
        HabitCheckinResponse habitCheckinResponse = habitCheckinService.checkinHabit(email, habitId);
        return ResponseEntity.status(201).body(habitCheckinResponse);
    }

    @GetMapping
    public ResponseEntity<List<HabitCheckinResponse>> listCheckins(@PathVariable Long habitId, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(habitCheckinService.listCheckins(email, habitId));
    }
}
