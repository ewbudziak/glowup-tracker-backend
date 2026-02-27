package com.ewelinabudziak.glowup_tracker.habit.service;

import com.ewelinabudziak.glowup_tracker.exception.ConflictException;
import com.ewelinabudziak.glowup_tracker.exception.NotFoundException;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitCheckinResponse;
import com.ewelinabudziak.glowup_tracker.habit.entity.Habit;
import com.ewelinabudziak.glowup_tracker.habit.entity.HabitCheckin;
import com.ewelinabudziak.glowup_tracker.habit.repository.HabitCheckinRepository;
import com.ewelinabudziak.glowup_tracker.habit.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitCheckinService {
    private final HabitCheckinRepository habitCheckinRepository;
    private final HabitRepository habitRepository;

    public HabitCheckinService(HabitCheckinRepository habitCheckinRepository, HabitRepository habitRepository) {
        this.habitCheckinRepository = habitCheckinRepository;
        this.habitRepository = habitRepository;
    }

    public HabitCheckinResponse checkinHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new NotFoundException("Habit not found"));

        LocalDate today = LocalDate.now();

        if(habitCheckinRepository.existsByHabitIdAndDate(habitId, today)){
            throw new ConflictException("Habit already checked in today");
        }

        HabitCheckin habitCheckin = new HabitCheckin(habit, today);
        habitCheckin = habitCheckinRepository.save(habitCheckin);

        return toHabitCheckinResponse(habitCheckin);

    }

    public List<HabitCheckinResponse> listCheckins(Long habitId) {
        if(!habitRepository.existsById(habitId)) {
            throw new NotFoundException("Habit not found");
        }

        return habitCheckinRepository.findAllByHabitIdOrderByDateDesc(habitId).stream()
                .map(this::toHabitCheckinResponse)
                .toList();
    }

    private HabitCheckinResponse toHabitCheckinResponse(HabitCheckin checkin) {
        return new HabitCheckinResponse(
                checkin.getId(),
                checkin.getHabit().getId(),
                checkin.getDate()
        );
    }
}