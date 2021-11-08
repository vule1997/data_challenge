package com.example.demo.goal;

import javax.persistence.*;

@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id_goal;
    private int id_match;
    private String side;
    private int time;

    public Goal() {
    }

    public Goal(int id_goal, int id_match, String side, int time) {
        this.id_goal = id_goal;
        this.id_match = id_match;
        this.side = side;
        this.time = time;
    }

    public Goal(int id_match, String side, int time) {
        this.id_match = id_match;
        this.side = side;
        this.time = time;
    }

    public int getId_goal() {
        return id_goal;
    }

    public void setId_goal(int id_goal) {
        this.id_goal = id_goal;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id_goal=" + id_goal +
                ", id_match=" + id_match +
                ", side=" + side +
                ", time=" + time +
                '}';
    }
}
