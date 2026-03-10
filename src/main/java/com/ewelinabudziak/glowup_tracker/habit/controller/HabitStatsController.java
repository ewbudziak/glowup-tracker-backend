package com.ewelinabudziak.glowup_tracker.habit.controller;

import com.ewelinabudziak.glowup_tracker.habit.dto.HabitStatsResponse;
import com.ewelinabudziak.glowup_tracker.habit.service.HabitStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits/{habitId}/stats")
public class HabitStatsController {
    private final HabitStatsService habitStatsService;

    public HabitStatsController(HabitStatsService habitStatsService) {
        this.habitStatsService = habitStatsService;
    }

    @GetMapping
    public ResponseEntity<HabitStatsResponse> getStats(@PathVariable Long habitId, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(habitStatsService.getStats(email, habitId));
    }
}
