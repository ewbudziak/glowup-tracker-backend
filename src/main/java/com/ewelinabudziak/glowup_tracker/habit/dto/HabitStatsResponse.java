package com.ewelinabudziak.glowup_tracker.habit.dto;

public record HabitStatsResponse(
        Long habitId,
        boolean doneToday,
        int currentStreak,
        int longestStreak
) {
}
