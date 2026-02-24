package com.ewelinabudziak.glowup_tracker.habit.repsitory;

import com.ewelinabudziak.glowup_tracker.habit.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findAllUsersById(Long id);
}
