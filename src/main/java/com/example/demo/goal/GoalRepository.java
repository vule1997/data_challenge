package com.example.demo.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {

    @Query("SELECT g FROM Goal g WHERE g.id_match=?1 AND g.side=?2 AND g.time=?3")
    Goal findDuplicates(int id, String side, int time);

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.id_match=?1 AND g.side='home' AND g.time>=?2 AND g.time<=?3")
    int getHomeGoals(int id, int start, int end);

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.id_match=?1 AND g.side='away' AND g.time>=?2 AND g.time<=?3")
    int getAwayGoals(int id, int start, int end);
}
