package com.ewelinabudziak.glowup_tracker.habit.service;

import com.ewelinabudziak.glowup_tracker.exception.NotFoundException;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitCreateRequest;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitResponse;
import com.ewelinabudziak.glowup_tracker.habit.dto.HabitUpdateRequest;
import com.ewelinabudziak.glowup_tracker.habit.entity.Habit;
import com.ewelinabudziak.glowup_tracker.habit.repository.HabitRepository;
import com.ewelinabudziak.glowup_tracker.user.entity.User;
import com.ewelinabudziak.glowup_tracker.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HabitService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public HabitService(UserRepository userRepository, HabitRepository habitRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    public HabitResponse createHabit(Long userId, HabitCreateRequest habitCreateRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Habit habit = new Habit(user, habitCreateRequest.name(), habitCreateRequest.frequencyType(), habitCreateRequest.targetPerWeek());
        habit = habitRepository.save(habit);

        return toHabitResponse(habit);
    }

    @Transactional
    public void deleteHabit(Long userId, Long habitId){

        Habit habit = habitRepository.findByIdAndUserId(habitId, userId)
                        .orElseThrow(() -> new NotFoundException("Habit not found"));

        habitRepository.delete(habit);
    }

    @Transactional
    public HabitResponse updateHabit(Long userId, Long habitId, HabitUpdateRequest habitUpdateRequest){
        Habit habit = habitRepository.findByIdAndUserId(habitId, userId)
                .orElseThrow(() -> new NotFoundException("Habit not found"));

        habit.setName(habitUpdateRequest.name());
        habit.setFrequencyType(habitUpdateRequest.frequencyType());
        habit.setTargetPerWeek(habitUpdateRequest.targetPerWeek());

        return toHabitResponse(habit);

    }

    public List<HabitResponse> listOfHabits(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }

        return habitRepository.findAllByUserId(userId).stream()
                .map(this::toHabitResponse)
                .toList();
    }

    private HabitResponse toHabitResponse(Habit h){
        return new HabitResponse(
                h.getId(),
                h.getUser().getId(),
                h.getUser().getEmail(),
                h.getName(),
                h.getFrequencyType(),
                h.getTargetPerWeek(),
                h.getCreatedAt()
        );
    }
}