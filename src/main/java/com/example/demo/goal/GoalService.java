package com.example.demo.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(int id_match, String side, int time) {
        if (goalRepository.findDuplicates(id_match, side, time) == null) {
            goalRepository.save(new Goal(id_match, side, time));
        }
    }

    public int getHomeGoals(int id, int start, int end) {
        return goalRepository.getHomeGoals(id, start, end);
    }

    public int getAwayGoals(int id, int start, int end) {
        return goalRepository.getAwayGoals(id, start, end);
    }

}
