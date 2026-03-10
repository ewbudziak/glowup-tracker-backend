package com.ewelinabudziak.glowup_tracker.habit.repository;

import com.ewelinabudziak.glowup_tracker.habit.entity.HabitCheckin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitCheckinRepository extends JpaRepository<HabitCheckin, Long> {
    Optional<HabitCheckin> findByHabitIdAndDate(Long habitId, LocalDate date);

    boolean existsByHabitIdAndDate(Long habitId, LocalDate date);

    List<HabitCheckin> findAllByHabitIdOrderByDateDesc(Long habitId);
}
