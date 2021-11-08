package com.example.demo.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query("SELECT t FROM Team t WHERE t.name=?1")
    Team findByName(String name);

    @Query("SELECT t.id_team FROM Team t WHERE t.name=?1")
    int getIdByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Team t SET t.points=t.points+?2, t.goal_diff=t.goal_diff+?3 WHERE t.id_team=?1")
    void updateTeamResult(int id, int points, int goal_diff);

    @Query("SELECT t FROM Team t\n" +
            "WHERE t.id_league=?1\n" +
            "ORDER BY t.points DESC, t.goal_diff DESC, t.name ASC")
    List<Team> getLeaderboard(int id_league);
}
