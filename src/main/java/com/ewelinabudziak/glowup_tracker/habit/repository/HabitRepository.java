package com.ewelinabudziak.glowup_tracker.habit.repository;

import com.ewelinabudziak.glowup_tracker.habit.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findAllByUserId(Long id);

    Optional<Habit> findByIdAndUserId(Long id, Long userId);
}
