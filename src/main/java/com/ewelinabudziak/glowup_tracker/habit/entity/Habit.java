package com.ewelinabudziak.glowup_tracker.habit.entity;

import com.ewelinabudziak.glowup_tracker.user.entity.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 80)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FrequencyType frequencyType;

    @Column(nullable = false)
    private int targetPerWeek;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitCheckin> checkins = new ArrayList<>();

    public Habit() {
    }

    public Habit(User user, String name, FrequencyType frequencyType, int targetPerWeek) {
        this.user = user;
        this.name = name;
        this.frequencyType = frequencyType;
        this.targetPerWeek = targetPerWeek;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public int getTargetPerWeek() {
        return targetPerWeek;
    }

    public void setTargetPerWeek(int targetPerWeek) {
        this.targetPerWeek = targetPerWeek;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : null) +
                ", name='" + name + '\'' +
                ", frequencyType=" + frequencyType +
                ", targetPerWeek=" + targetPerWeek +
                ", createdAt=" + createdAt +
                '}';
    }
}
