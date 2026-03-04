package com.ewelinabudziak.glowup_tracker.habit.controller;

import com.ewelinabudziak.glowup_tracker.habit.dto.HabitStatsResponse;
import com.ewelinabudziak.glowup_tracker.habit.service.HabitStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/habits/{habitId}/stats")
public class HabitStatsController {
    private final HabitStatsService habitStatsService;

    public HabitStatsController(HabitStatsService habitStatsService) {
        this.habitStatsService = habitStatsService;
    }

    @GetMapping
    public ResponseEntity<HabitStatsResponse> getStats(@PathVariable Long habitId) {
        HabitStatsResponse habitStatsResponse = habitStatsService.getStats(habitId);
        return ResponseEntity.ok(habitStatsResponse);
    }
}
