package com.ewelinabudziak.glowup_tracker;

import com.ewelinabudziak.glowup_tracker.habit.entity.FrequencyType;
import com.ewelinabudziak.glowup_tracker.habit.entity.Habit;
import com.ewelinabudziak.glowup_tracker.habit.entity.HabitCheckin;
import com.ewelinabudziak.glowup_tracker.habit.repsitory.HabitCheckinRepository;
import com.ewelinabudziak.glowup_tracker.habit.repsitory.HabitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ewelinabudziak.glowup_tracker.user.entity.User;
import com.ewelinabudziak.glowup_tracker.user.repsitory.UserRepository;

import java.time.LocalDate;

@SpringBootApplication
public class GlowupTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlowupTrackerApplication.class, args);
	}
}


