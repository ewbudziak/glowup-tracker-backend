package com.ewelinabudziak.glowup_tracker.habit.service;

import com.ewelinabudziak.glowup_tracker.exception.NotFoundException;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitStatsResponse;
import com.ewelinabudziak.glowup_tracker.habit.entity.Habit;
import com.ewelinabudziak.glowup_tracker.habit.entity.HabitCheckin;
import com.ewelinabudziak.glowup_tracker.habit.repository.HabitCheckinRepository;
import com.ewelinabudziak.glowup_tracker.habit.repository.HabitRepository;
import com.ewelinabudziak.glowup_tracker.user.entity.User;
import com.ewelinabudziak.glowup_tracker.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitStatsService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final HabitCheckinRepository habitCheckinRepository;

    public HabitStatsService(HabitRepository habitRepository, HabitCheckinRepository habitCheckinRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.habitCheckinRepository = habitCheckinRepository;
        this.userRepository = userRepository;
    }

    public HabitStatsResponse getStats(String email, Long habitId) {
        LocalDate today = LocalDate.now();
        boolean doneToday = false;
        int currentStreak = 0;
        int longestStreak = 0;

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Habit habit = habitRepository.findByIdAndUserId(habitId, user.getId())
                .orElseThrow(() -> new NotFoundException("Habit not found"));

        List<HabitCheckin> habitCheckins = habitCheckinRepository.findAllByHabitIdOrderByDateDesc(habit.getId());

        if (habitCheckins.isEmpty()) {
            return new HabitStatsResponse(
                    habitId,
                    doneToday,
                    currentStreak,
                    longestStreak
            );
        }

        doneToday = habitCheckinRepository.existsByHabitIdAndDate(habit.getId(), today);

        if (doneToday) {
            LocalDate expectedDate = today;
            for (HabitCheckin habitCheckin : habitCheckins) {
                if (habitCheckin.getDate().equals(expectedDate)) {
                    currentStreak++;
                    expectedDate = expectedDate.minusDays(1);
                } else {
                    break;
                }
            }
        }

        longestStreak = calculateLongestStreak(habitCheckins);

        return new HabitStatsResponse(
                habitId,
                doneToday,
                currentStreak,
                longestStreak
        );
    }

    private int calculateLongestStreak(List<HabitCheckin> checkins) {
        if (checkins == null || checkins.isEmpty()) {
            return 0;
        }

        int currentRun = 1;
        int longestRun = 1;

        LocalDate prevDate = checkins.get(0).getDate();

        for (int i = 1; i < checkins.size(); i++) {
            LocalDate currentDate = checkins.get(i).getDate();

            if (currentDate.equals(prevDate.minusDays(1))) {
                currentRun++;
            } else {
                currentRun = 1;
            }

            if (currentRun > longestRun) {
                longestRun = currentRun;
            }

            prevDate = currentDate;
        }

        return longestRun;
    }
}