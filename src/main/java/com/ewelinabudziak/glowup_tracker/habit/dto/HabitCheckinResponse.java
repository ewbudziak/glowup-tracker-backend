package com.ewelinabudziak.glowup_tracker.habit.dto;

import java.time.Instant;
import java.time.LocalDate;

public record HabitCheckinResponse(
        Long id,
        Long habitId,
        LocalDate date
) {}
