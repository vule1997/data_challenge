package com.example.demo.match;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "match")
@DynamicUpdate
public class Match {

    @Id
    private int id_match;
    private int id_home;
    private int id_away;
    private int start;
    private int finish;

    public Match() {
    }

    public Match(int id_match, int id_home, int id_away, int start, int finish) {
        this.id_match = id_match;
        this.id_home = id_home;
        this.id_away = id_away;
        this.start = start;
        this.finish = finish;
    }

    public Match(int id_match, int id_home, int id_away, int start) {
        this.id_match = id_match;
        this.id_home = id_home;
        this.id_away = id_away;
        this.start = start;
    }

    public Match(int id_match, int finish) {
        this.id_match = id_match;
        this.finish = finish;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public int getId_home() {
        return id_home;
    }

    public void setId_home(int id_home) {
        this.id_home = id_home;
    }

    public int getId_away() {
        return id_away;
    }

    public void setId_away(int id_away) {
        this.id_away = id_away;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id_match=" + id_match +
                ", id_home=" + id_home +
                ", id_away=" + id_away +
                ", start=" + start +
                ", finish=" + finish +
                '}';
    }
}
