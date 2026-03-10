package com.ewelinabudziak.glowup_tracker.habit.dto;

import com.ewelinabudziak.glowup_tracker.habit.entity.FrequencyType;

import java.time.Instant;

public record HabitResponse(
        Long id,
        Long userId,
        String userEmail,
        String name,
        FrequencyType frequencyType,
        int targetPerWeek,
        Instant createdAt
) {
}
