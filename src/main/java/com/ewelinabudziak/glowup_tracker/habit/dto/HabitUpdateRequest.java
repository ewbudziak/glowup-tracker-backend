package com.ewelinabudziak.glowup_tracker.habit.dto;

import com.ewelinabudziak.glowup_tracker.habit.entity.FrequencyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HabitUpdateRequest(
        @NotBlank @Size(max = 80) String name,
        @NotNull FrequencyType frequencyType,
        @Min(1) int targetPerWeek
) {
}