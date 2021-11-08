package com.example.demo.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void addTeam(int id_league,String name) {
        Team t = teamRepository.findByName(name);
        if (teamRepository.findByName(name) == null) {
            teamRepository.save(new Team(id_league, name));
        }
    }

    public int getIdByName(String name) {
        return teamRepository.getIdByName(name);
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    public String getName(int id) {
        return teamRepository.findById(id).get().getName();
    }

    public void updateTeamResult(int id, int points, int goal_diff) {
        teamRepository.updateTeamResult(id, points, goal_diff);
    }

    public List<Team>  getLeaderBoard(int id_league) {
        return teamRepository.getLeaderboard(id_league);
    }
}
