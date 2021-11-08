package com.example.demo.team;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id_team;
    private int id_league;
    private String name;
    private int points;
    private int goal_diff;

    public Team() {
    }

    public Team(int id_team, int id_league, String name, int points, int goal_diff) {
        this.id_team = id_team;
        this.id_league = id_league;
        this.name = name;
        this.points = points;
        this.goal_diff = goal_diff;
    }

    public Team(int id_league, String name) {
        this.id_league = id_league;
        this.name = name;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public int getId_league() {
        return id_league;
    }

    public void setId_league(int id_league) {
        this.id_league = id_league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoal_diff() {
        return goal_diff;
    }

    public void setGoal_diff(int goal_diff) {
        this.goal_diff = goal_diff;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id_team=" + id_team +
                ", id_league=" + id_league +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", goal_diff=" + goal_diff +
                '}';
    }
}
